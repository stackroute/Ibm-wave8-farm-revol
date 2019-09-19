import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LandService } from '../land.service';


@Component({
  selector: 'app-view-farmer-orders',
  templateUrl: './view-farmer-orders.component.html',
  styleUrls: ['./view-farmer-orders.component.scss']
})
export class ViewFarmerOrdersComponent implements OnInit {

  farmerId: string;
  landId: any;
  landOrderDetails: any;
  constructor(private service:LandService,private router:Router, private route: ActivatedRoute) { }

  ngOnInit() {

    this.farmerId = this.route.snapshot.params.email;
    this.landId = this.route.snapshot.params.landId;

    this.service.getFarmerLandOrders(this.farmerId,this.landId).subscribe(
      response => {
        this.landOrderDetails = response;
        console.log("Response is "+response[0].consumerId);
      }
    );


  }

}
