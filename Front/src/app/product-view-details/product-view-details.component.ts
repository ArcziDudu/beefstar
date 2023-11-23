import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product_model';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ProductService } from '../_services/product.service';


@Component({
  selector: 'app-product-view-details',
  templateUrl: './product-view-details.component.html',
  styleUrls: ['./product-view-details.component.css']
})
export class ProductViewDetailsComponent implements OnInit{
  selectedProductIndex = 0;

  product!: Product;

  constructor(  private activatedRoute: ActivatedRoute,
    private router: Router,
    private productService: ProductService){

  }

  ngOnInit(): void {

    this.activatedRoute.data.subscribe(data => {
      this.product = data['product'];

      if (!this.product) {
        console.error("Brak danych o produkcie");
      }
    });
  }

  addToCart(productId){
    console.log(productId)
    this.productService.addToCart(productId).subscribe(
      (resp) =>{
        console.log(resp)
      }, (err)=>{
        console.log(err)
      }
    );
  }

  public changeIndex(index){
    this.selectedProductIndex = index;
  }

  public buyProduct(productId){
    this.router.navigate(['/product/order',{
      isSingleProductCheckout: true, id: productId
    }]);
  }

}
