import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-navbar-consumer',
  templateUrl: './navbar-consumer.component.html',
  styleUrls: ['./navbar-consumer.component.scss']
})
export class NavbarConsumerComponent implements OnInit {

  consumerId: string
  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(data => {
      this.consumerId=data.get('email');
    })
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
 
  goToOrders() {
    var id1 = this.route.snapshot.params.id;
    var id2 = this.route.snapshot.params.email;
    var id;
    if(id1 == undefined) {
      id = id2;
    }
    else {
      id = id1;
    }
    this.router.navigate(['/ConsumerOrdersPage/'+id]);
  }

  clickSearch(searchString) {

    searchString = searchString.toLowerCase();
    this.router.navigate(['/'+this.consumerId+'/search/'+searchString]);
  }

  logOut() {
    this.router.navigate(['/home-page']);
  }
}
