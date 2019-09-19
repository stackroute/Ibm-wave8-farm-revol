import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LandService } from '../land.service';

@Component({
  selector: 'app-farmer-orders',
  templateUrl: './farmer-orders.component.html',
  styleUrls: ['./farmer-orders.component.scss']
})
export class FarmerOrdersComponent implements OnInit {

  farmerId: string;
  farmerDetails: any;
  confirmedFarmerDetails: any;
  constructor(private service : LandService,private router:Router, private route: ActivatedRoute) { }

  ngOnInit() 
  {
    this.farmerId = this.route.snapshot.params.id;
    console.log("Id is "+this.farmerId);

    // this.data.getLands(this.farmerId).subscribe(data=> {
    //   console.log(data);
    //   this.lands=data;
    // });

    this.service.getFarmerDetails(this.farmerId).subscribe(data=> {
      this.farmerDetails = data;
      // let i;
      // let j = 0;

      //  for( i = 0; i < this.farmerDetails.land.length; i++) {
      //    console.log("Dt is + "+this.farmerDetails.land[i].landOrders.length);

      //    if(this.farmerDetails.land[i].landOrders.length != 0) {
      //      console.log("fhuew");
      //      this.confirmedFarmerDetails.push(this.farmerDetails.land[i]);
      //      console.log("hi 2 ");
      //      console.log("ConfDt is + "+this.confirmedFarmerDetails.land[j]);
      //      console.log("hfiew");
      //      j++;
      //    }
      //  }
    
      console.log("Data is" + this.farmerDetails.land[0].landOrders[0].price);
    });

  }


  viewOrdersOfLand(landId) {

    console.log(landId);
    this.router.navigate(['land-order/'+this.farmerId + '/'+landId]);
  }
}
