
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorStateMatcher } from '@angular/material/core';
import { AuthService } from '../auth.service';



/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}


@Component({
  selector: 'app-consumer-register',
  templateUrl: './consumer-register.component.html',
  styleUrls: ['./consumer-register.component.scss']
})
export class ConsumerRegisterComponent implements OnInit {



  
  registerForm: FormGroup;
  fullName = '';
  email = '';
  password = '';
  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      'fullName' : [null, Validators.required],
      'email' : [null, Validators.required],
      'password' : [null, Validators.required],
      'typeOfUser' : [null, Validators.required]
    });
  }

  onFormSubmit(form: NgForm) {
    console.log(form);
    this.authService.registerConsumer(form)
      .subscribe(res => {
        this.router.navigate(['products']);
      }, (err) => {
        console.log(err);
        alert(err.error);
      });
  }
}
