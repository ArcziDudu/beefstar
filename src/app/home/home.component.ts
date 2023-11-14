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
  pageNumber: number = 0;

  showLoadButton = false;

  constructor(
    private productService: ProductService,
    private imageProcessingService: ImageProcessingService,
    private router: Router){}

  ngOnInit(): void {
    this.getAllProducts();
  }

  searchByKeyword(searchKeyword){
    console.log(searchKeyword);
    this.pageNumber = 0;
    this.productDetails = [];
    this.getAllProducts(searchKeyword);
  }

  public getAllProducts(searchKey: string = ""){
    this.productService.getAllProducts(this.pageNumber, searchKey)
    .pipe(
      map((x: Product[], i) => x.map((product: Product)=> this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: Product[]) => {
        console.log(resp);
        if(resp.length == 6){
          this.showLoadButton =true;
        }else{
          this.showLoadButton = false;
        }
        resp.forEach(p=>this.productDetails.push(p));
      }, (error: HttpErrorResponse)=>{
        console.log(error);
      }

    );
  }

  loadMoreProducts(){
    this.pageNumber = this.pageNumber+1;
    this.getAllProducts();
  }

  public showProductDetails(productId){
    this.router.navigate(['/product/view/details/', {productId: productId}])
  }
}
