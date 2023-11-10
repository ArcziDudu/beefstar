import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product_model';
import { NgFor } from '@angular/common';
import { NgForm } from '@angular/forms';
import { ProductService } from '../_services/product.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-new-product',
  templateUrl: './add-new-product.component.html',
  styleUrls: ['./add-new-product.component.css']
})
export class AddNewProductComponent implements OnInit{

  product: Product  = {
    productName: "",
    productDescription: "",
    productDiscountedPrice: 0,
    productActualPrice:0
  }
  constructor(private productService: ProductService){

  }

  ngOnInit(): void {
    
  }

  addProductForm(productForm: NgForm){
   this.productService.addProduct(this.product).subscribe(
    (Response: Product) => {
     productForm.reset()
    },
    (error: HttpErrorResponse) => {
      console.log(error)
    }
   );
  }
}
