import { Component, OnInit } from '@angular/core';
import { DAOUser } from 'src/DAOUser';
import { AuthenticationService } from '../authentication.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  private user=new DAOUser();
  private email;

  constructor(private authenticateService:AuthenticationService,private formBuilder:FormBuilder,private router:Router,private http:HttpClient) { }

  ngOnInit() {
  }
reset(){
  console.log(this.email);
  this.user.email=this.email;
  this.authenticateService.ForgotPasswordComponent(this.user)
  .subscribe(data=>{
    console.log(data);
  });
}
}
