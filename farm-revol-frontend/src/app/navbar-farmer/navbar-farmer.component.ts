import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-navbar-farmer',
  templateUrl: './navbar-farmer.component.html',
  styleUrls: ['./navbar-farmer.component.scss']
})
export class NavbarFarmerComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  viewProfile() {
    this.router.navigate(['/farmer-edit-profile']);
  }

}
