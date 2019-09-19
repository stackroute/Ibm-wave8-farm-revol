import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { ConsumerEditProfileService } from '../consumer-edit-profile.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-consumer-edit-profile',
  templateUrl: './consumer-edit-profile.component.html',
  styleUrls: ['./consumer-edit-profile.component.scss']
})
export class ConsumerEditProfileComponent implements OnInit {

  consumerId:string;
  errorMsg='';
  details:any;
  constructor(private service:ConsumerEditProfileService,private route:ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.consumerId=this.route.snapshot.params.email;
    console.log(this.consumerId);
    this.service.getConsumerDetails(this.consumerId).subscribe(data => {
      this.details = data;
      console.log("data in consumer is "+ data);
    });
  }

  updateProfileDetails() {
    this.service.updateProfile(this.details).subscribe(
      response =>console.log('success',response),
    error=>this.errorMsg=error.statusText);   
   // this.router.navigate(['/'+this.consumerId]);
    this.router.navigate(['/'+this.consumerId+ '/consumer']); 

 
  }

  enable_disable() { 
    $("input").prop('disabled', false); 
}

}
