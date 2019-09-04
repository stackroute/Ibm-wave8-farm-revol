import { Component, OnInit } from '@angular/core';
import { LandService } from '../land.service';
import { Land } from 'src/land';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-lands',
  templateUrl: './lands.component.html',
  styleUrls: ['./lands.component.scss']
})
export class LandsComponent implements OnInit {
farmerId: string;
lands:Land;
up_size: number;
up_price: number;
up_crops: any;
  constructor(private data:LandService,private router:Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(data => {
      console.log(data);
    })
    this.getLands();
  }

  getLands(){
    this.data.getLands().subscribe(data=> {
      this.lands=data;
      console.log(this.lands);
    });
    
  }

  removeLand(land):any
  {
        var landValue=land.id;
        var farmerValue=land.farmerId;
        console.log(landValue);
        console.log(farmerValue);
        this.data.removeLand(landValue,farmerValue).subscribe();
        
             //this.ngOnInit();
       // this.router.navigate(['/remove',farmerValue,landValue]);
      }
 
  updateLands(land) {
    land.size = this.up_size;
    land.price = this.up_price;
    land.crops = this.up_crops;
    // window.location.reload();
    console.log("Farm Details Updated");
    this.router.navigate(['/updateLand']);
  }
 
  updateSize(up_size) {
    this.up_size = up_size;
  }
 
  updatePrice(up_price) {
    this.up_price = up_price;
  }
  updateCrops(up_crops) {
    this.up_crops = up_crops;
  }

  uploadLands(farmerId) {
    console.log(farmerId);
    localStorage.setItem('farmerId',JSON.stringify(this.farmerId));
    this.router.navigate(['/upload', farmerId]);
  }

}
