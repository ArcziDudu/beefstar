package com.beefstar.beefstar.service;

import com.beefstar.beefstar.domain.ImageModelDto;
import com.beefstar.beefstar.infrastructure.entity.ImageModel;
import com.beefstar.beefstar.infrastructure.mapper.ImageModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageModelMapper imageModelMapper;

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModelDto> imageModels = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            ImageModelDto imageModel = new ImageModelDto(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels.stream()
                .map(image -> imageModelMapper.mapFromDto(image))
                .collect(Collectors.toSet());
    }
}
