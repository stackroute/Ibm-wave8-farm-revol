import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { ErrorStateMatcher } from '@angular/material/core';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register-farmer',
  templateUrl: './register-farmer.component.html',
  styleUrls: ['./register-farmer.component.scss']
})
export class RegisterFarmerComponent implements OnInit {

  registerForm: FormGroup;
  fullName = '';
  email = '';
  password = '';
  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();

  constructor(private formBuilder: FormBuilder, private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      // 'fullname' : [null, Validators.required],
      'fullname': new FormControl('', [Validators.required, Validators.maxLength(60)]),

      // 'phoneNumber' : [ Validators.required,Validators.maxLength(10)  ],
      'phoneNumber': new FormControl('', [Validators.required, Validators.pattern('[0-9]{10}')]),

      email: ['', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')]],
      'password': new FormControl('', [Validators.required,Validators.minLength(8), Validators.maxLength(60)]),
    //  'typeOfUser' : [null, Validators.required]
    });
  }
  onFormSubmit(form: NgForm) {
    console.log(form);
    this.authenticationService.register(form)
      .subscribe(res => {
        
      }, (err) => {
        console.log(err);
        alert(err.error);
      });

      this.router.navigate(["/login"]);
  }
  public hasError = (controlName: string, errorName: string) =>{
    return this.registerForm.controls[controlName].hasError(errorName);
  }

}
