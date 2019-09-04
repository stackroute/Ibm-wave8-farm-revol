import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { AuthGuard } from './auth/auth.guard';
import { ConsumerRegisterComponent } from './consumer-register/consumer-register.component';
import { LandsComponent } from './lands/lands.component';
import { UpdateLandComponent } from './update-land/update-land.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'products',
    pathMatch: 'full'
  },
  {
    path: 'products',
    canActivate: [AuthGuard],
    component: ProductsComponent,
    data: { title: 'List of Products' }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: { title: 'Login' }
  },
  {
    path: 'registerFarmer',
    component: RegisterComponent,
    data: { title: 'RegisterFarmer' }
  },
  {
    path: 'registerConsumer',
    component: ConsumerRegisterComponent,
    data: { title: 'RegisterConsumer' }
  },
   {
    path: ':email/lands',
    canActivate: [AuthGuard],
    component: LandsComponent,
    data: { 
      title: 'List of Lands'
    }
  },
  {
    path: 'upload/:id',
    component: UpdateLandComponent,
    data: { title: 'List of updated lands' }
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
