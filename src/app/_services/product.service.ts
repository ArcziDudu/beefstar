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

  public getAllProducts(pageNumber, searchKey: string = ""){
    return this.httpClient.get<Product[]>("http://localhost:8080/beefstar/product/all?pageNumber="+pageNumber+"&searchKey="+searchKey);
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

  public placeOrder(orderDetails: OrderDetails, isCartCheckout){
    return this.httpClient.post("http://localhost:8080/beefstar/order/new/"+isCartCheckout, orderDetails);
  }

  public addToCart(productId: number) {
    const url = `http://localhost:8080/beefstar/addToCart/${productId}`;
    return this.httpClient.post(url, null);
  }
  
  public getCartDetails(){
    return this.httpClient.get("http://localhost:8080/beefstar/cartDetails");
  }
  public deleteCart(cartId){
    return this.httpClient.delete("http://localhost:8080/beefstar/cartDelete/"+cartId)
  }

}
