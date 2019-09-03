import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-navbar-consumer',
  templateUrl: './navbar-consumer.component.html',
  styleUrls: ['./navbar-consumer.component.scss']
})
export class NavbarConsumerComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  viewProfile() {
    this.router.navigate(['/consumer-edit-profile']);
  }
}
