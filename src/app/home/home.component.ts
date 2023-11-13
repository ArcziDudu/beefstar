import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product_model';
import { ProductService } from '../_services/product.service';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { ImageProcessingService } from '../image-processing.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  productDetails: Product[] = [];

  constructor(
    private productService: ProductService,
    private imageProcessingService: ImageProcessingService,
    private router: Router){}

  ngOnInit(): void {
    this.getAllProducts();
  }
  public getAllProducts(){
    this.productService.getAllProducts()
    .pipe(
      map((x: Product[], i) => x.map((product: Product)=> this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: Product[]) =>{
        console.log(resp);
        this.productDetails = resp;
      }, (error: HttpErrorResponse)=>{
        console.log(error);
      }

    );
  }

  public showProductDetails(productId){
    this.router.navigate(['/product/view/details/', {productId: productId}])
  }
}
