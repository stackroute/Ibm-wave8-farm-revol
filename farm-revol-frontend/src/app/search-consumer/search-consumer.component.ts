import { Component, OnInit } from '@angular/core';
import { HomeserviceService } from '../homeservice.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Location } from '@angular/common';
import { ConsumerEditProfileService } from '../consumer-edit-profile.service';

@Component({
  selector: 'app-search-consumer',
  templateUrl: './search-consumer.component.html',
  styleUrls: ['./search-consumer.component.scss']
})
export class SearchConsumerComponent implements OnInit {

  cropList: any;
  cropName: any;
  flag: boolean = false;
  consumerId: string;
  searchString: string;
  listOfCrops: string[] = ['Paddy', 'Rice', 'Wheat', 'Mirchi', 'Potato', 'Cotton', 'tomato', 'Maize', 'SugarCane'];
  constructor(private homeService: HomeserviceService, private consumerService: ConsumerEditProfileService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  // ngOnInit() {

  //   this.route.paramMap.subscribe((params: ParamMap) =>
  //    {
  //      let searchString = params.get('searchString');
  //      this.homeService.getSearch(searchString).subscribe((data)=> {
  //        this.cropList = data;
  //        console.log("Crop is" + this.cropList.lands.farmerId);
  //      }
  //      );
  //    }
  //   );
  // }
  ngOnInit() {


    this.route.paramMap.subscribe((params: ParamMap) => {
      
      
       this.searchString = params.get('searchString');



     
      for (var eachCrop of this.listOfCrops) {

        eachCrop = eachCrop.toLowerCase();
        console.log("Each crop is " + eachCrop);
       // console.log("Crop Name" + this.cropName);
        if (eachCrop == this.searchString) {

          this.flag = true;
        }
      }
      

      
     this.homeService.getSearch(this.searchString).subscribe((data) => {
          this.cropList = data;
          console.log("Crop is" + this.cropList.lands.farmerId);
        }
        );
      }
      
    );
  }

  viewParticularLand(farm) {

    let str = this.route.snapshot.params.searchString;
    this.consumerId = this.route.snapshot.params.email;
    // console.log(this.consumerId);
    // this.router.navigate(['/'+this.consumerId+'/view-land',farm.id,str]);


    console.log("Farm is " + farm.location);
    this.consumerService.bookingLand(this.consumerId, str,farm).subscribe(data => {
      console.log("Successfully added");
      this.router.navigate(['/'+this.consumerId+'/booked']);
    });
  
  }


}
