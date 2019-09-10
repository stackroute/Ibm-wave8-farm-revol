import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm, FormControl } from '@angular/forms';
import * as $ from 'jquery';
import { LandService } from '../land.service';

@Component({
  selector: 'app-farmereditprofile',
  templateUrl: './farmereditprofile.component.html',
  styleUrls: ['./farmereditprofile.component.scss']
})
export class FarmereditprofileComponent implements OnInit {
  farmerId:string;
  errorMsg='';
  details:any;
  constructor(private service:LandService,private route:ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.farmerId=this.route.snapshot.params.email;
    console.log(this.farmerId);
    this.service.getFarmerDetails(this.farmerId).subscribe(data => {
      this.details = data;
      console.log("Data is " + this.details.phoneNumber); 
    });

  }

  updateProfileDetails() {
    this.service.updateProfile(this.details).subscribe(
      response =>console.log('success',response),
    error=>this.errorMsg=error.statusText);
      
    this.router.navigate(['/'+this.farmerId+'/lands']);
 
  }

  enable_disable() { 
    $("input").prop('disabled', false); 
}

}
