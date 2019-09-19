import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'
@Component({
  selector: 'app-booking-page',
  templateUrl: './booking-page.component.html',
  styleUrls: ['./booking-page.component.scss']
})
export class BookingPageComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute) { }
  consumerId: string;
  ngOnInit() {

    this.consumerId = this.route.snapshot.params.email;
  }

  goToHome() {
   
    this.router.navigate(['/'+this.consumerId+'/consumer']);

  }

}
