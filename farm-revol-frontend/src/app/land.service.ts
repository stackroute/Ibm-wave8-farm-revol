import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Land } from '../Land';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';






@Injectable({
  providedIn: 'root'
})
export class LandService {

  constructor(private http:HttpClient) { }
  getLands(farmerId)
  {
    return this.http.get<Land>(environment.farmerUrl+'/register/land?email=' + farmerId);
  }
  removeLand(id,farmerId) {
  return this.http.delete<Land>(environment.farmerUrl+`/land/delete/`+farmerId+`/`+id);

  }
 
  uploadLand(farmerId,land): Observable<Land> {
    console.log(farmerId);
    console.log(land);
    return this.http.put<Land>(environment.farmerUrl+`/land-details/`+farmerId,land);

 
  }
  updateLand(farmerId,landId,land){
    return this.http.put<Land>(environment.farmerUrl+`/land/update/`+farmerId+`/`+landId,land);
    //this.http.get<Land>(apiUrl+'/register/land?email=' + farmerId);
  }

  getParticularLand(farmerId, landId) {
    return this.http.get<Land>(environment.farmerUrl + '/land/farmer/'+farmerId+'/'+landId);
    
  }

  getFarmerDetails(farmerId) {
    return this.http.get(environment.farmerUrl + '/register/' + farmerId);
  }

  updateProfile(details) {
    return this.http.put(environment.farmerUrl + '/update', details);

  }

  getFarmerLandOrders(farmerId,landId) {
    return this.http.get(environment.farmerUrl + '/land/orders/'+farmerId+'/'+landId );
  }
  
}
