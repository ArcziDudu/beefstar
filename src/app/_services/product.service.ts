import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_model/product_model';
import { OrderDetails } from '../_model/order-details.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  public addProduct(product: FormData){
    return this.httpClient.post<Product>("http://localhost:8080/beefstar/product/add", product);
  }

  public getAllProducts(){
    return this.httpClient.get<Product[]>("http://localhost:8080/beefstar/product/all");
  }

  public getProductDetailsById(productId){
    return this.httpClient.get<Product>("http://localhost:8080/beefstar/product/details/"+productId);
  }

  public deleteProduct(productId: number){
   return this.httpClient.delete<Product[]>("http://localhost:8080/beefstar/product/delete/"+productId);
  }

  public getProductDetails(isSingleProductCheckout, productId){
    return this.httpClient.get<Product[]>("http://localhost:8080/beefstar/product/order/"+isSingleProductCheckout+"/"+productId);
  }

  public placeOrder(orderDetails: OrderDetails){
    return this.httpClient.post("http://localhost:8080/beefstar/order/new", orderDetails);
  }
}
