import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { FobiddenComponent } from './fobidden/fobidden.component';
import { AuthGuard } from './_auth/auth.guard';
import { AddNewProductComponent } from './add-new-product/add-new-product.component';
import { ShowProductDetailsComponent } from './show-product-details/show-product-details.component';
import { ProductResolveService } from './product-resolve.service';
import { ProductViewDetailsComponent } from './product-view-details/product-view-details.component';

const routes: Routes = [
  {path:'home', component:HomeComponent},
  {path:'admin', component:AdminComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path:'user', component:UserComponent, canActivate:[AuthGuard], data:{roles:['User']}},
  {path:'login', component:LoginComponent},
  {path:'fobidden', component:FobiddenComponent},
  {path: 'product/add', component: AddNewProductComponent, canActivate:[AuthGuard], data:{roles:['Admin']}, 

  resolve: {
    product: ProductResolveService
  }
},
  {path: 'product/edit', component: AddNewProductComponent, canActivate:[AuthGuard], data:{roles:['Admin']},
  resolve: {
    product: ProductResolveService
  }},
  {path: 'product/all/details', component: ShowProductDetailsComponent},
  {path: 'product/view/details', component: ProductViewDetailsComponent,
   resolve: {product: ProductResolveService}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
