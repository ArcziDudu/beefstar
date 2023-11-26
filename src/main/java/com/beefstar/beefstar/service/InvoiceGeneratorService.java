package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.InvoiceDao;
import com.beefstar.beefstar.domain.InvoiceDTO;
import com.beefstar.beefstar.infrastructure.entity.InvoiceEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class InvoiceGeneratorService {
    private WebClient webClient;
    @Autowired
     InvoiceDao invoiceDao;
  public InvoiceGeneratorService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://yakpdf.p.rapidapi.com/pdf")
                .defaultHeader("X-RapidAPI-Key", "783a00e0d6msh9341f2f6ed2c9b4p10a743jsn66976e554e1e")
                .defaultHeader("X-RapidAPI-Host", "yakpdf.p.rapidapi.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.toString())
                .build();
    }

    public void generatePdfWithRetry(String htmlContent, String uuid) {
        String jsonBody = "{\"source\":{\"html\":\"" +
                htmlContent
                + "\"},\"pdf\":{\"format\":\"A4\",\"scale\":1,\"printBackground\":true},\"wait\":{\"for\":\"navigation\",\"waitUntil\":\"load\",\"timeout\":2500}}";

        webClient.post()
                .body(BodyInserters.fromValue(jsonBody))
                .retrieve()
                .bodyToMono(byte[].class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .maxBackoff(Duration.ofSeconds(10))
                        .filter(this::isRetryableError))
                .subscribe(
                        pdfBytes -> {
                            InvoiceEntity pdfDocument = new InvoiceEntity();
                            saveInvoice(uuid, pdfDocument, pdfBytes);
                        },
                        error -> {
                            log.error("Error during PDF file retrieval: " + error.getMessage());
                        }
                );
    }

    private boolean isRetryableError(Throwable throwable) {
        log.error("Error during retry attempt: " + throwable.getMessage());
        return true;
    }

    @Transactional
    public void saveInvoice(String uuid, InvoiceEntity pdfDocument, byte[] pdfBytes) {
        pdfDocument.setUuid(uuid);
        pdfDocument.setPdfData(pdfBytes);
        invoiceDao.save(pdfDocument);
    }
    public InvoiceDTO findByUuid(String uuid) {
        Optional<InvoiceDTO> invoiceByUuid = invoiceDao.findByUuid(uuid);
        if (invoiceByUuid.isEmpty()) {
            throw new RuntimeException("Could not find invoice by uuid: [%s]".formatted(uuid));
        }
        return invoiceByUuid.get();
    }

}
