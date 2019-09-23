import { Component, OnInit } from '@angular/core';
import {FormControl, Validators, FormGroupDirective, NgForm, FormGroup, FormBuilder} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
// export interface State {
//  name: string;
// }

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register-consumer',
  templateUrl: './register-consumer.component.html',
  styleUrls: ['./register-consumer.component.scss']
})
export class RegisterConsumerComponent implements OnInit {

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
      'phoneNumber': new FormControl('', [Validators.required,  Validators.pattern('[0-9]{10}')]),
      'password': new FormControl('', [Validators.required,Validators.minLength(8), Validators.maxLength(60)]),



      // 'phoneNumber' : [null, Validators.required],
      // 'email' : [null, Validators.required],
      email: ['', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')]],

      // 'password' : [null, Validators.required]
     // 'typeOfUser' : [null, Validators.required]
    });
    //   'fullname' : [null, Validators.required],
    //   'phoneNumber' : [null, Validators.required],
    //   'email' : [null, Validators.required],
    //   'password' : [null, Validators.required]
    //  // 'typeOfUser' : [null, Validators.required]
    // });
  }

  onFormSubmit(form: NgForm) {
    console.log(form);
    this.authenticationService.registerConsumer(form)
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



  // stateControl = new FormControl('', [Validators.required]);
  // selectFormControl = new FormControl('', Validators.required);
  // states: State[] = [
    
  //   {name: 'Andhra Pradesh'},
  //   {name: 'Arunachal Pradesh'},
  //   {name: 'Assam'},
  //   {name: 'Bihar'},
  //   {name: 'Chattisgarh'},
  //   {name: 'Goa'},
  //   {name: 'Gujarat'},
  //   {name: 'Haryana'},
  //   {name: 'Himachal Pradesh'},
  //   {name: 'Jammu & Kashmir'},
  //   {name: 'Jharkhand'},
  //   {name: 'Karnataka'},
  //   {name: 'Kerela'},
  //   {name: 'Madhya Pradesh'},
  //   {name: 'Maharashtra'},
  //   {name: 'Manipur'},
  //   {name: 'Meghalaya'},
  //   {name: 'Mizoram'},
  //   {name: 'Nagaland'},
  //   {name: 'Odisha'},
  //   {name: 'Punjab'},
  //   {name: 'Rajasthan'},
  //   {name: 'Sikkim'},
  //   {name: 'Tamil Nadu'},
  //   {name: 'Telengana'},
  //   {name: 'Tripura'},
  //   {name: 'Uttarakhand'},
  //   {name: 'Uttar Pradesh'},
  //   {name: 'West Bengal'},
  //   {name: 'Delhi'},
  //   {name: 'Andaman and Nicobar Islands'},
  //   {name: 'Dadra and Nagar Haveli'},
  //   {name: 'Puducherry'},
  //   {name: 'Chandigarh'},
  //   {name: 'Daman & Diu'},
  //   {name: 'Lakshadweep'}

  // ];
  
}