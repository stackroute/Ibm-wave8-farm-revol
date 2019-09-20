import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from "@angular/flex-layout";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarSearchComponent } from './navbar-search/navbar-search.component';

import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import { HttpClientModule } from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';

import {MatMenuModule} from '@angular/material/menu';

import {MatSelectModule} from '@angular/material/select';
import { HomeBodyComponent } from './home-body/home-body.component';
import { FooterHomeComponent } from './footer-home/footer-home.component';
import { SearchComponent } from './search/search.component';
import { FaqComponent } from './faq/faq.component';
import { MatExpansionModule} from '@angular/material/expansion';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterFarmerComponent } from './register-farmer/register-farmer.component';
import { RegisterConsumerComponent } from './register-consumer/register-consumer.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { FarmerLandingPageComponent } from './farmer-landing-page/farmer-landing-page.component';
import { UploadfarmComponent } from './uploadfarm/uploadfarm.component';
import { FarmereditprofileComponent } from './farmereditprofile/farmereditprofile.component';
import { NavbarFarmerComponent } from './navbar-farmer/navbar-farmer.component';
import { ConsumerLandingPageComponent } from './consumer-landing-page/consumer-landing-page.component';
import { NavbarConsumerComponent } from './navbar-consumer/navbar-consumer.component';
import { ConsumerEditProfileComponent } from './consumer-edit-profile/consumer-edit-profile.component';
import {MatRadioModule} from '@angular/material/radio';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import{UpdateLandComponent} from './update-land/update-land.component';
import { SearchConsumerComponent } from './search-consumer/search-consumer.component';
import { BookingPageComponent } from './booking-page/booking-page.component';
import { ConsumerOrdersComponent } from './consumer-orders/consumer-orders.component';
import { FarmerOrdersComponent } from './farmer-orders/farmer-orders.component';
import { ViewFarmerOrdersComponent } from './view-farmer-orders/view-farmer-orders.component';




@NgModule({
  declarations: [
    AppComponent,
    NavbarSearchComponent,
    HomeBodyComponent,
    FooterHomeComponent,
    SearchComponent,
    FaqComponent,
    LoginPageComponent,
    RegisterFarmerComponent,
    RegisterConsumerComponent,
    ForgotPasswordComponent,
    FarmerLandingPageComponent,
    UploadfarmComponent,
    FarmereditprofileComponent,
    NavbarFarmerComponent,
    ConsumerLandingPageComponent,
    NavbarConsumerComponent,
    ConsumerEditProfileComponent,
    ResetPasswordComponent,
    UpdateLandComponent,
    SearchConsumerComponent,
    BookingPageComponent,
    ConsumerOrdersComponent,
    FarmerOrdersComponent,
    ViewFarmerOrdersComponent,
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatToolbarModule,
    HttpClientModule,
    MatCardModule,
    MatMenuModule,
    MatSelectModule,
    MatExpansionModule,
    MatRadioModule,
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
