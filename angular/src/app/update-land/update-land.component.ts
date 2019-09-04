import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { LandService } from '../land.service';
import { Land } from 'src/land';
import { ActivatedRoute } from '@angular/router';
import { NgForm, FormControl } from '@angular/forms';

@Component({
  selector: 'app-update-land',
  templateUrl: './update-land.component.html',
  styleUrls: ['./update-land.component.scss']
})
export class UpdateLandComponent implements OnInit {
  submitted=false;
  farmerId: string;
  land=new Land();  
  errorMsg='';
  crops: FormControl;
  cropList: string[] = ['Paddy', 'Rice', 'Wheat', 'Mirchi', 'Potato', 'Tomato'];
  constructor(private service:LandService,private route:ActivatedRoute) { 
    this.crops = new FormControl();
    this.farmerId=this.route.snapshot.params.id;
    console.log(this.farmerId);
  }

  ngOnInit() {
    this.uploadLandDetails();
    console.log(this.farmerId);
  }
  uploadLandDetails() {
    this.submitted=true;
   
    console.log(this.land)
    console.log(this.land.crops);
    this.service.uploadLand(this.farmerId,this.land).subscribe(
      response =>console.log('success',response),
      error=>this.errorMsg=error.statusText
    );
  }
 
}
