import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LandRecommend } from 'src/Recommend';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const apiUrl = environment.recommendationUrl;

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  constructor(private http:HttpClient) { }

  getRecommendations(consumeremail):Observable<LandRecommend>{
    return this.http.get<LandRecommend>(apiUrl+'/recommend/'+consumeremail);
    
  }
}
