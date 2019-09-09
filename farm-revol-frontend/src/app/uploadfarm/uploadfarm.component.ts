import { Component, OnInit } from '@angular/core';
import { LandService } from '../land.service';
import { Land } from '../../Land';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm, FormControl } from '@angular/forms';

@Component({
  selector: 'app-uploadfarm',
  templateUrl: './uploadfarm.component.html',
  styleUrls: ['./uploadfarm.component.scss']
})
export class UploadfarmComponent implements OnInit {
  submitted=false;
  farmerId: string;
  land=new Land();  
  errorMsg='';
  crops: FormControl;
  cropList: string[] = ['Paddy', 'Rice', 'Wheat', 'Mirchi', 'Potato','Cotton','tomato','Maize','SugarCane'];

  constructor(private service:LandService,private route:ActivatedRoute, private router: Router) { 
    this.crops = new FormControl();
    this.farmerId=this.route.snapshot.params.id;
    console.log(this.farmerId);
  }

  ngOnInit() {
   // this.uploadLandDetails();
    console.log("Farmer " + this.farmerId);
  }
  uploadLandDetails() {
    this.submitted=true;
   console.log(this.farmerId);
    console.log(this.land)
    console.log(this.land.crops);
    this.service.uploadLand(this.farmerId,this.land).subscribe(
    
      response =>console.log('success',response),
      error=>this.errorMsg=error.statusText
    );
    
   this.router.navigate(['/'+this.farmerId+'/lands']);
  }

}
//this.loginForm.get('email').value + '/lands'