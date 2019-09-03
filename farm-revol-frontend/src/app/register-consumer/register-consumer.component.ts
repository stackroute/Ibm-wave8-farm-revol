import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
export interface State {
 name: string;
}

@Component({
  selector: 'app-register-consumer',
  templateUrl: './register-consumer.component.html',
  styleUrls: ['./register-consumer.component.scss']
})
export class RegisterConsumerComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  stateControl = new FormControl('', [Validators.required]);
  selectFormControl = new FormControl('', Validators.required);
  states: State[] = [
    
    {name: 'Andhra Pradesh'},
    {name: 'Arunachal Pradesh'},
    {name: 'Assam'},
    {name: 'Bihar'},
    {name: 'Chattisgarh'},
    {name: 'Goa'},
    {name: 'Gujarat'},
    {name: 'Haryana'},
    {name: 'Himachal Pradesh'},
    {name: 'Jammu & Kashmir'},
    {name: 'Jharkhand'},
    {name: 'Karnataka'},
    {name: 'Kerela'},
    {name: 'Madhya Pradesh'},
    {name: 'Maharashtra'},
    {name: 'Manipur'},
    {name: 'Meghalaya'},
    {name: 'Mizoram'},
    {name: 'Nagaland'},
    {name: 'Odisha'},
    {name: 'Punjab'},
    {name: 'Rajasthan'},
    {name: 'Sikkim'},
    {name: 'Tamil Nadu'},
    {name: 'Telengana'},
    {name: 'Tripura'},
    {name: 'Uttarakhand'},
    {name: 'Uttar Pradesh'},
    {name: 'West Bengal'},
    {name: 'Delhi'},
    {name: 'Andaman and Nicobar Islands'},
    {name: 'Dadra and Nagar Haveli'},
    {name: 'Puducherry'},
    {name: 'Chandigarh'},
    {name: 'Daman & Diu'},
    {name: 'Lakshadweep'}

  ];
  
}