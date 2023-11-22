import { Product } from "./product_model";

export interface MyOrderDetails{
    orderId: number,
    orderFullName: string,
    orderFullAddress: string,
    orderContactNumber: string,
    orderDate: Date;
    orderStatus:string,
    orderAmount: number,
    user: any;

}