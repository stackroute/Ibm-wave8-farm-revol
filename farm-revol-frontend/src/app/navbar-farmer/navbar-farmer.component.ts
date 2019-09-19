import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { LandService } from '../land.service';
import { Land } from "../../Land";

@Component({
  selector: 'app-navbar-farmer',
  templateUrl: './navbar-farmer.component.html',
  styleUrls: ['./navbar-farmer.component.scss']
})
export class NavbarFarmerComponent implements OnInit {

  farmerId: string;
  lands:Land;
  up_size: number;
  up_price: number;
  up_crops: any;



  constructor(private router: Router, private route: ActivatedRoute, private data: LandService ) { }

  ngOnInit() {

    this.route.paramMap.subscribe(data => {
      this.farmerId=data.get('email');
    })
    this.getLands()
  }

  goToHome() {
    var id1 = this.route.snapshot.params.id;
    var id2 = this.route.snapshot.params.email;
    var id;
    if(id1 == undefined) {
      id = id2;
    }
    else {
      id = id1;
    }
    this.router.navigate(['/'+id+'/lands']);
  }

  viewFarmerOrders() {

    var id1 = this.route.snapshot.params.id;
    var id2 = this.route.snapshot.params.email;
    var id;
    if(id1 == undefined) {
      id = id2;
    }
    else {
      id = id1;
    }
    this.router.navigate(['/farmer-orders/'+id]);


  }

  viewProfile() {
    var id1 = this.route.snapshot.params.id;
    var id2 = this.route.snapshot.params.email;
    var id;
    if(id1 == undefined) {
      id = id2;
    }
    else {
      id = id1;
    }
    this.router.navigate(['/updateProfile/'+id]);
  }
 

  getLands(){
    console.log(this.farmerId);
    this.data.getLands(this.farmerId).subscribe(data=> {
      console.log(data);
      this.lands=data;
    });
    
  }

  uploadLands() {

    var id1 = this.route.snapshot.params.id;
    var id2 = this.route.snapshot.params.email;
    var id;
    if(id1 == undefined) {
      id = id2;
    }
    else {
      id = id1;
    }
    console.log(this.farmerId);
    localStorage.setItem('farmerId',JSON.stringify(id));
    this.router.navigate(['/upload-farm', id]);
  }

  logOut() {
    this.router.navigate(['/home-page']);
  }


}
