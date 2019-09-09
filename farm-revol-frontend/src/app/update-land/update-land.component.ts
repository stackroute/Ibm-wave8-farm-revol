import { Component, OnInit } from '@angular/core';
import { LandService } from '../land.service';
import { Land } from '../../Land';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm, FormControl } from '@angular/forms';


import * as $ from 'jquery';

@Component({
  selector: 'app-update-land',
  templateUrl: './update-land.component.html',
  styleUrls: ['./update-land.component.scss']
})
export class UpdateLandComponent implements OnInit {

  farmerId: string;
  landId:number;
  lands: Land;
  crops: FormControl;
  cropList: string[] = ['Paddy', 'Rice', 'Wheat', 'Mirchi', 'Potato','Cotton','tomato','Maize','SugarCane'];
  errorMsg='';

  constructor(private service:LandService,private route:ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.farmerId=this.route.snapshot.params.email;
    this.landId=this.route.snapshot.params.id;
    console.log(this.farmerId);
    console.log(this.landId);

    this.service.getParticularLand(this.farmerId,this.landId).subscribe(data => {
      console.log("Data is " + data.location);
      this.lands = data;
    });

  }

  enable_disable() { 
    $("input").prop('disabled', false); 
    //disableSelect = new FormControl(false);
    
}

 updateLandDetails() {
  this.service.updateLand(this.farmerId,this.landId,this.lands).subscribe(
    response =>console.log('success',response),
    error=>this.errorMsg=error.statusText
  );

  this.router.navigate(['/'+this.farmerId+'/lands']);

 }



}
