import { Component, OnInit } from '@angular/core';
import { LandService } from '../land.service';
import { Land } from '../../Land';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-farmer-landing-page',
  templateUrl: './farmer-landing-page.component.html',
  styleUrls: ['./farmer-landing-page.component.scss']
})
export class FarmerLandingPageComponent implements OnInit {

  farmerId: string;
  lands:Land;
  up_size: number;
  up_price: number;
  up_crops: any;


  constructor(private data:LandService,private router:Router, private route: ActivatedRoute) { }

  ngOnInit() {

    this.route.paramMap.subscribe(data => {
      this.farmerId=data.get('email');
    })
    this.getLands();
  }


  getLands(){
    console.log(this.farmerId);
    this.data.getLands(this.farmerId).subscribe(data=> {
      console.log(data);
      this.lands=data;
    });
    
  }

  updateLands(land) {
    var landValue=land.id;
    var farmerValue=this.farmerId;
    console.log(landValue);
    console.log(farmerValue);
    this.router.navigate(['/updateLand',farmerValue,landValue]);
  }

  removeLand(land):any
  {
        var landValue=land.id;
        var farmerValue=this.farmerId;
        console.log(landValue);
        console.log(farmerValue);
        this.data.removeLand(landValue,farmerValue).subscribe();
             //this.ngOnInit();
       // this.router.navigate(['/remove',farmerValue,landValue]);
  }

}
