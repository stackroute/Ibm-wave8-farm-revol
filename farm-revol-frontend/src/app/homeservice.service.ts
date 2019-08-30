import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HomeserviceService {

  url;
  constructor(private httpclient: HttpClient) { }

  getSearch(val:String)
  {
    this.url = "http://localhost:8080/api/v1/cropname/"+val ;
    return this.httpclient.get(this.url);
  }
}
