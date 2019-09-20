import { Component, OnInit } from '@angular/core';
import { HomeserviceService } from '../homeservice.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Location } from '@angular/common';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  cropList: any;
  cropName: any;
  flag: boolean = false;
  listOfCrops: string[] = ['Paddy', 'Rice', 'Wheat', 'Mirchi', 'Potato', 'Cotton', 'tomato', 'Maize', 'SugarCane'];
  constructor(private homeService: HomeserviceService, private route: ActivatedRoute, private location: Location) { }

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
      
      
      let searchString = params.get('value');



     
      for (var eachCrop of this.listOfCrops) {

        eachCrop = eachCrop.toLowerCase();
        console.log("Each crop is " + eachCrop);
        console.log("Searched crop is "+searchString);
       // console.log("Crop Name" + this.cropName);
        if (eachCrop == searchString) {

          this.flag = true;
        }
      }
      

      
     this.homeService.getSearch(searchString).subscribe((data) => {
          this.cropList = data;
          console.log("Crop is" + this.cropList.lands.farmerId);
        }
        );
      }
      

    
    

    );
  }



}
