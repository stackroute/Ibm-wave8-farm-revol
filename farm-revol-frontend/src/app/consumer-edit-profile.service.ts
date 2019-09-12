import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const apiUrl = 'http://localhost:8092/api/consumer';

@Injectable({
  providedIn: 'root'
})
export class ConsumerEditProfileService {

  constructor(private http:HttpClient) { }

  getConsumerDetails(consumerId) {
    return this.http.get(apiUrl + '/register/' + consumerId);
  }

  updateProfile(details) {
    return this.http.put(apiUrl + '/update', details);

  }

}
