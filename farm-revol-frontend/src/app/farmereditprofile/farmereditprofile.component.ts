import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-farmereditprofile',
  templateUrl: './farmereditprofile.component.html',
  styleUrls: ['./farmereditprofile.component.scss']
})
export class FarmereditprofileComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  enable_disable() { 
    $("input").prop('disabled', false); 
}

}
