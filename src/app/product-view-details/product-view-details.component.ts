import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product_model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-view-details',
  templateUrl: './product-view-details.component.html',
  styleUrls: ['./product-view-details.component.css']
})
export class ProductViewDetailsComponent implements OnInit{
  selectedProductIndex = 0;

  product!: Product;

  constructor(  private activatedRoute: ActivatedRoute){

  }

  ngOnInit(): void {
    
    this.activatedRoute.data.subscribe(data => {
      this.product = data['product'];
  
      if (!this.product) {
        console.error("Brak danych o produkcie");
      }
    });
  }
  public changeIndex(index){
    this.selectedProductIndex = index;
  }
}
