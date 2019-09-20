import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

// const apiUrl = 'http://localhost:8092/api/consumer';
//const apiUrl = environment.consumerUrl + ':8092/api/consumer';
@Injectable({
  providedIn: 'root'
})
export class ConsumerEditProfileService {

  constructor(private http:HttpClient) { }

  getConsumerDetails(consumerId) {
    return this.http.get(environment.consumerUrl + '/register/' + consumerId);
  }

  updateProfile(details) {
    return this.http.put(environment.consumerUrl + '/update', details);

  }

  bookingLand(consumerId, cropName, land) {
    return this.http.put(environment.consumerUrl + '/booking/'+consumerId + '/'+ cropName, land);
  }

  getOrders(consumerId) {
    return this.http.get(environment.consumerUrl + '/orders/' + consumerId);
  }

} 
