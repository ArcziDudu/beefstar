
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { OrderDetails } from '../_model/order-details.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../_model/product_model';
import { ProductService } from '../_services/product.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { OrderConfirmationComponent } from '../order-confirmation/order-confirmation.component';


@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent implements OnInit{

  isSingleProductCheckout:string | null='';
  productDetails: Product[] = [];

  orderDetails: OrderDetails = {
    userFullName: '',
    userFullAddress: '',
    userContactNumber: '',
    orderProductQuantityList: []
  }


  constructor(private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router,
    public dialog: MatDialog){

    }
    
    ngOnInit(): void {
      const data = this.activatedRoute.snapshot.data['productDetails'];
      this.isSingleProductCheckout = this.activatedRoute.snapshot.paramMap.get("isSingleProductCheckout");
      
      if (data && data.length > 0) {
        this.productDetails = data;
    
        this.productDetails.forEach(
          x => this.orderDetails.orderProductQuantityList.push({ productId: x.productId, quantity: 1 })
        );
    
        console.log(this.productDetails);
        console.log(this.orderDetails);
      } else {
        console.error('Product details are undefined or empty.');
      }
    }
    
    isFieldEmpty(fieldName: string): boolean {
      return !this.orderDetails[fieldName].trim(); 
    }

  placeOrder(orderForm: NgForm){
    this.productService.placeOrder(this.orderDetails, this.isSingleProductCheckout).subscribe(
      (resp)=>{
        console.log(resp);
        orderForm.reset();
        this.openOrderSuccessDialog();
      },
      (err)=>{
        console.log(err);
      }
    );
  }

  openOrderSuccessDialog(): void {
    const dialogRef = this.dialog.open(OrderConfirmationComponent, {
      width: '50%',
      height: '300px'
    });
  
  }

  getQuantityForProduct(productId){
    const filteredProduct = this.orderDetails.orderProductQuantityList.filter(
       (productQuantity) => productQuantity.productId === productId
     );
     return filteredProduct[0].quantity;
   }
   getCalculatedTotal(productId, productActualPrice){
   const filteredProduct =  this.orderDetails.orderProductQuantityList.filter(
      (productQuantity)=>productQuantity.productId === productId
    );
    return (filteredProduct[0].quantity * productActualPrice).toFixed(2);
   }

   onQuantityChanged(value, productId){
    this.orderDetails.orderProductQuantityList.filter(
      (orderProduct) => orderProduct.productId === productId
    )[0].quantity = value;
   }

   getCalculatedGrandTotal(){
    let grandTotal = 0;

    this.orderDetails.orderProductQuantityList.forEach(
      (productQuantity)=>{
        const price = this.productDetails.filter(product => product.productId === productQuantity.productId)[0].productActualPrice
        grandTotal = grandTotal + price * productQuantity.quantity;
      }
    );
    return grandTotal.toFixed(2);
   }
}
