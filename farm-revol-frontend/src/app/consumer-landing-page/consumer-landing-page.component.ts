import { Component, OnInit } from '@angular/core';
import { RecommendationService } from '../recommendation.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Land } from 'src/Land';
import { LandRecommend } from 'src/Recommend';
import { ConsumerEditProfileService } from '../consumer-edit-profile.service';

@Component({
  selector: 'app-consumer-landing-page',
  templateUrl: './consumer-landing-page.component.html',
  styleUrls: ['./consumer-landing-page.component.scss']
})
export class ConsumerLandingPageComponent implements OnInit {

  consumeremail:string;
  land:LandRecommend;
  consumerId: string;
  /*landreco : LandRecommend = {
    id:undefined,
    farmerId:undefined,
    landSize:undefined,
    landPrice:undefined,
    location:undefined,
    crops:undefined,
    image:undefined,
    landOrders:undefined
  }*/

  constructor(private service:RecommendationService,private router:Router, private consumerService: ConsumerEditProfileService,private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(data => {
      this.consumeremail=data.get('email');
    })
    this.getRecommendationLands(this.consumeremail);
  }

  getRecommendationLands(consumeremail){
    console.log(this.consumeremail);
    this.service.getRecommendations(this.consumeremail).subscribe(recom=> {
      console.log(recom);
 this.land = recom;
    });
    
  }


  viewParticularLand(farm) {

    let str = this.route.snapshot.params.searchString;
    this.consumerId = this.route.snapshot.params.email;
    // console.log(this.consumerId);
    // this.router.navigate(['/'+this.consumerId+'/view-land',farm.id,str]);


    console.log("Farm is " + farm);
    this.consumerService.bookingLand(this.consumerId, str,farm).subscribe(data => {
      console.log("Successfully added");
      this.router.navigate(['/'+this.consumerId+'/booked']);
    });
  
  }

}
