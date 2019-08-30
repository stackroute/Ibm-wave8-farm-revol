import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

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
import { RegisterPageComponent } from './register-page/register-page.component';
import { RegisterFarmerComponent } from './register-farmer/register-farmer.component';
import { RegisterConsumerComponent } from './register-consumer/register-consumer.component';




@NgModule({
  declarations: [
    AppComponent,
    NavbarSearchComponent,
    HomeBodyComponent,
    FooterHomeComponent,
    SearchComponent,
    FaqComponent,
    LoginPageComponent,
    RegisterPageComponent,
    RegisterFarmerComponent,
    RegisterConsumerComponent
    
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
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
