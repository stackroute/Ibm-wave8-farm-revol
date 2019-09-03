import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-consumer-edit-profile',
  templateUrl: './consumer-edit-profile.component.html',
  styleUrls: ['./consumer-edit-profile.component.scss']
})
export class ConsumerEditProfileComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  enable_disable() { 
    $("input").prop('disabled', false); 
}

}
