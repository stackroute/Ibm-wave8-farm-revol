import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';

import { Land } from 'src/land';
import { Observable } from 'rxjs';


const apiUrl = 'http://localhost:8080/api/farmer';


@Injectable({
  providedIn: 'root'
})
export class LandService {

  httpOptions = {
    headers: new HttpHeaders(
      {
        'Content-Type': 'application/json'
      }
    )
  }

  constructor(private http:HttpClient) { }

  getLands()
  {
    return this.http.get<Land>(apiUrl+'/register/land?email=pallavi@gmail.com',this.httpOptions);
  }
  removeLand(id,farmerId) {
    
  return this.http.delete<Land>(apiUrl+'/land/delete/'+farmerId+'/'+id, this.httpOptions);
  }
 
  uploadLand(farmerId,land): Observable<Land> {
 
    return this.http.put<Land>(apiUrl+'/land-details/'+farmerId,land);
 
  }
}
