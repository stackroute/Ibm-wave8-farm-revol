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

  cropList:any = [];

  constructor(private homeService: HomeserviceService, private route: ActivatedRoute, private location: Location,
    private router:Router ) { }

  ngOnInit() {

    this.route.paramMap.subscribe((params: ParamMap) => 
      {
        let searchString = params.get('value');
        this.homeService.getSearch(searchString).subscribe((data)=> {
          this.cropList = data;
          console.log(this.cropList[0].id);
        }

        );
      }

    );
  }

}
