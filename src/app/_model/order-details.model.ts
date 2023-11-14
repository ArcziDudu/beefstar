import { OrderQuantity } from "./order-quantity.model";

export interface OrderDetails{
     userFullName: string,
     userFullAddress: string,
     userContactNumber: string,
     orderProductQuantityList: OrderQuantity[];
}