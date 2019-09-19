import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { HomeBodyComponent } from './home-body/home-body.component';
import { FaqComponent } from './faq/faq.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterFarmerComponent } from './register-farmer/register-farmer.component';
import { RegisterConsumerComponent } from './register-consumer/register-consumer.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { FarmerLandingPageComponent } from './farmer-landing-page/farmer-landing-page.component';
import { FarmereditprofileComponent } from './farmereditprofile/farmereditprofile.component';
import { ConsumerLandingPageComponent } from './consumer-landing-page/consumer-landing-page.component';
import { ConsumerEditProfileComponent } from './consumer-edit-profile/consumer-edit-profile.component';
import { UploadfarmComponent } from './uploadfarm/uploadfarm.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { UpdateLandComponent } from './update-land/update-land.component';
import { SearchConsumerComponent } from './search-consumer/search-consumer.component';
import { BookingPageComponent } from './booking-page/booking-page.component';
import { ConsumerOrdersComponent } from './consumer-orders/consumer-orders.component';
import { FarmerOrdersComponent } from './farmer-orders/farmer-orders.component';
import { ViewFarmerOrdersComponent } from './view-farmer-orders/view-farmer-orders.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home-page'},
  {  path: 'home-page', component: HomeBodyComponent },
  {  path: 'result/:value', component: SearchComponent },
  { path: "faq", component: FaqComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register-farmer', component: RegisterFarmerComponent},
  { path: 'register-consumer', component: RegisterConsumerComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  {path:'resetPassword',component:ResetPasswordComponent},
  { path: ':email/lands',
    component: FarmerLandingPageComponent,
    //canActivate: [AuthGuard],
    data: {
      title: 'List of Lands'
    }
  },
  {
    path: ':email/consumer',
    component: ConsumerLandingPageComponent
  },
  {
    path: 'land-order/:email/:landId', component: ViewFarmerOrdersComponent
  },
  // 'land-order/'+this.farmerId,landOrders])
  { path: 'updateProfile/:email', component: FarmereditprofileComponent },
  { path: ':email/updateConsumerProfile', component: ConsumerEditProfileComponent},
  { path: ':email/search/:searchString', component: SearchConsumerComponent},
  { path: 'ConsumerOrdersPage/:email',component:ConsumerOrdersComponent},
  //['/ConsumerOrdersPage/'+id]

 // '/'+this.consumerId+'/search/'+searchString
 // {path:'consumer-landing-page',component: ConsumerLandingPageComponent},
 // {path: 'email/updateProfile', component: ConsumerEditProfileComponent},
 //['/farmer-orders/'+id]
  {path:'upload-farm/:id',component:UploadfarmComponent, data: { title: 'List of uploaded lands' }},
  {
    path: 'farmer-orders/:id', component: FarmerOrdersComponent
  },
  {
    path: 'updateLand/:email/:id',
    component: UpdateLandComponent,
    data: { title: 'List of uploaded lands' }
  },
  {
    path: ':email/booked', component: BookingPageComponent
  }
  //this.router.navigate(['/'+this.consumerId+'/view-land',farm]);
  //'/'+this.consumerId+'booked'
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
