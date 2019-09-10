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

  viewProfile() {
    this.router.navigate(['/'+this.consumerId+'/updateConsumerProfile']);
  }
}
