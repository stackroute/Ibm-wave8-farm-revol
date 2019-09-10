import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Land } from '../Land';
import { Observable } from 'rxjs';

const apiUrl = 'http://localhost:8090/api/farmer';


@Injectable({
  providedIn: 'root'
})
export class LandService {

  constructor(private http:HttpClient) { }
  getLands(farmerId)
  {
    return this.http.get<Land>(apiUrl+'/register/land?email=' + farmerId);
  }
  removeLand(id,farmerId) {
  return this.http.delete<Land>(apiUrl+`/land/delete/`+farmerId+`/`+id);

  }
 
  uploadLand(farmerId,land): Observable<Land> {
    console.log(farmerId);
    console.log(land);
    return this.http.put<Land>(apiUrl+`/land-details/`+farmerId,land);

 
  }
  updateLand(farmerId,landId,land){
    return this.http.put<Land>(apiUrl+`/land/update/`+farmerId+`/`+landId,land);
    //this.http.get<Land>(apiUrl+'/register/land?email=' + farmerId);
  }

  getParticularLand(farmerId, landId) {
    return this.http.get<Land>(apiUrl + '/land/farmer/'+farmerId+'/'+landId);
    
  }

  
}
