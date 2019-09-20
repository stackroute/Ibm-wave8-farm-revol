import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-navbar-search',
  templateUrl: './navbar-search.component.html',
  styleUrls: ['./navbar-search.component.scss']
})
export class NavbarSearchComponent implements OnInit {

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }

  clickSearch(searchString) {

    searchString = searchString.toLowerCase();
  //  console.log(value);
    // this.searchService.getSearch(value).subscribe(data => {
    //   console.log("Location is " + data[0].id);
    //   console.log("Location firs
  //  t is " + data[0].farms[0].location);
    //   this.cropList = data;
    // }

    // )
    console.log("Searched is "+searchString);

    this.router.navigateByUrl("/result/" + searchString);

  }


}
