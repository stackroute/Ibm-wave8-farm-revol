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

  viewProfile() {
    this.router.navigate(['/farmer-edit-profile']);
  }

  getLands(){
    console.log(this.farmerId);
    this.data.getLands(this.farmerId).subscribe(data=> {
      console.log(data);
      this.lands=data;
    });
    
  }

  uploadLands() {
    console.log(this.farmerId);
    localStorage.setItem('farmerId',JSON.stringify(this.farmerId));
    this.router.navigate(['/upload-farm', this.farmerId]);
  }


}
