import { FileHandle } from "./file-handle.model";

export interface Product{
    productId: any,
    productName: string,
    productDescription: string,
    productCategory: string,
    productActualPrice: number,
    productImages: FileHandle[]
}