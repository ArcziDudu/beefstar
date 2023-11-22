import { Component, OnInit } from '@angular/core';
import { ProductService } from '../_services/product.service';
import { MyOrderDetails } from '../_model/order.model';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {

  displayedColumns = ["Name","ProductName","Address", "Amount", "Date", "Status"];

  myOrderDetails: MyOrderDetails[] = [];
  constructor(private productServicve: ProductService){
    
  }
  ngOnInit(): void {
   this.getOrderDetails();
  }

  getOrderDetails(){
    this.productServicve.getMyOrdersDetails().subscribe(
      (resp: MyOrderDetails[])=>{
      this.myOrderDetails = resp;
      console.log(resp);
      },
      (err)=>{
        console.log(err);
      }
    );
  }
}
