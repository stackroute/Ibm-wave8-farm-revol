import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { ErrorStateMatcher } from '@angular/material/core';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  loginForm: FormGroup;
  email = '';
  password = '';
  matcher = new MyErrorStateMatcher();
  isLoadingResults = false;
 

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute , private router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'email' : [null, Validators.required],
      'password' : [null, Validators.required]
    });
  }

  onFormSubmit(form: NgForm) {
    this.authService.login(form)
      .subscribe(res => {
        console.log(res);
        console.log(res.role);
        if (res.token&&res.role=='farmer') {
          localStorage.setItem('token', res.token);
          console.log(res);
          console.log("a");
          console.log(this.loginForm.get('email').value);

          this.router.navigateByUrl('/home-page', {skipLocationChange: true}).then(()=>
            this.router.navigate([this.loginForm.get('email').value + '/lands'])); 
          //this.router.navigate([this.loginForm.get('email').value + '/lands']);
        }
        else if(res.token&&res.role=='consumer'){
          localStorage.setItem('token', res.token);
          console.log(res);
          console.log("a");
          console.log(this.loginForm.get('email').value);

          
          this.router.navigateByUrl('/home-page', {skipLocationChange: true}).then(()=>
          this.router.navigate([this.loginForm.get('email').value + '/consumer'])); 

        }
      } ,(err) => {
        console.log(err);
      });


  }


  
  

  registerFarmer() {
    this.router.navigate(['registerFarmer']);
  }
  registerConsumer(){
    this.router.navigate(['registerConsumer']);
  }


}
