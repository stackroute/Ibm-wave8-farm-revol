import { Component, OnInit } from '@angular/core';
import { LoginUser } from 'src/Login';
import { AuthenticationService } from '../authentication.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  private newPassword : string;
 private confirmNewPassword : string;
 constructor(private authenticateService: AuthenticationService, private formBuilder: FormBuilder,private router:Router) { }
 login = new LoginUser();

 ngOnInit() {
 }
 reset() {
   console.log("megan")
   this.login.email = "meghanaraj1016@gmail.com"
   this.login.password = this.confirmNewPassword;
   this.authenticateService.resetPasswordComponent(this.login)
   .subscribe(data => {
     console.log(data);
   });
}

}
