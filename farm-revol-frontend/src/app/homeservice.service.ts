import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeserviceService {

  url;
  constructor(private httpclient: HttpClient) { }

  getSearch(val:String)
  {
    this.url = environment.searchUrl+val ;
    return this.httpclient.get(this.url);
  }
}
