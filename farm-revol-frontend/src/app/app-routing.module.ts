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


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home-page'},
  {  path: 'home-page', component: HomeBodyComponent },
  {  path: 'result/:value', component: SearchComponent },
  { path: "faq", component: FaqComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register-farmer', component: RegisterFarmerComponent},
  { path: 'register-consumer', component: RegisterConsumerComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: ':email/lands',
    component: FarmerLandingPageComponent,
    //canActivate: [AuthGuard],
    data: {
      title: 'List of Lands'
    }
  },
  { path: 'farmer-edit-profile', component: FarmereditprofileComponent },
  {path:'consumer-landing-page',component: ConsumerLandingPageComponent},
  {path: 'consumer-edit-profile', component: ConsumerEditProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
