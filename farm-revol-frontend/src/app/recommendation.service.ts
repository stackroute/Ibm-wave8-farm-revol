import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LandRecommend } from 'src/Recommend';
import { Observable } from 'rxjs';

const apiUrl = 'http://localhost:8090/rest/neo4j';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  constructor(private http:HttpClient) { }

  getRecommendations(consumeremail):Observable<LandRecommend>{
    return this.http.get<LandRecommend>(apiUrl+'/recommend/'+consumeremail);
    
  }
}
