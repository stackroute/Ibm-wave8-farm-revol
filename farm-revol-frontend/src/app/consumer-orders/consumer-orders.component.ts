import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { ConsumerEditProfileService } from '../consumer-edit-profile.service';
@Component({
  selector: 'app-consumer-orders',
  templateUrl: './consumer-orders.component.html',
  styleUrls: ['./consumer-orders.component.scss']
})
export class ConsumerOrdersComponent implements OnInit {

  consumerId: string;
  constructor(private route:ActivatedRoute, private router: Router, private service: ConsumerEditProfileService) { }
  orderList: any;
  ngOnInit() {

    this.consumerId = this.route.snapshot.params.email;
    console.log(this.consumerId);

    this.service.getOrders(this.consumerId).subscribe(response => {
      console.log("Response in orders is "+ response[0].farmerId);
      this.orderList = response;
    });
  }

}
