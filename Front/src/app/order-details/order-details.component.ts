import { Component, OnInit } from '@angular/core';
import { ProductService } from '../_services/product.service';
import { MyOrderDetails } from '../_model/order.model';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit{
  displayedColumns:string[] = ["User","userFullName","ProductName","Address","Amount","Date","Status","Actions"];
  dataSource: MyOrderDetails[] = [];
  status: string= 'All';
constructor(private productService: ProductService){

}
  ngOnInit(): void {
    this.getAllOrderDetailsForAdmin(this.status);
  }

  getAllOrderDetailsForAdmin(status: string){
    this.productService.getAllOrdersDetailsForAdmin(status).subscribe(
      (resp)=>{
        this.dataSource= resp;
      },
      (err)=>{
        console.log(err);
      }
    );
  }

  markAsDelivered(orderId){
    this.productService.markAsDelivered(orderId).subscribe(
      (resp)=>{
       this.getAllOrderDetailsForAdmin(this.status);
      },
      (err)=>{
        console.log(err)
      }
    );
  }
}
