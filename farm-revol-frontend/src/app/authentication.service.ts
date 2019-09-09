import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

<<<<<<< HEAD
const apiUrl = 'http://localhost:8080/api/farmer/';
const apiUrl1 = 'http://localhost:8080/api/consumer/';
const apiurl2='http://localhost:8091/authenticate';
=======
const apiUrl = 'http://localhost:8090/api/farmer/';
const apiUrl1 = 'http://localhost:8091/api/consumer/';
const apiurl2='http://localhost:7090/authenticate';
>>>>>>> 38deab7075b152fe1c0bbdde79c501d999097aa2


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  isLoggedIn = false;
  redirectUrl: string;

  constructor(private http: HttpClient) { }


  login(data: any): Observable<any> {
    return this.http.post<any>(apiurl2 , data)
      .pipe(
        tap(_ => this.isLoggedIn = true),
        catchError(this.handleError('login', []))
      );
  }

   logout(): Observable<any> {
     return this.http.get<any>(apiurl2 + 'signout')
       .pipe(
         tap(_ => this.isLoggedIn = false),
         catchError(this.handleError('logout', []))
       );
   }

  register(data: any): Observable<any> {
    return this.http.post<any>(apiUrl + 'register', data)
      .pipe(
        tap(_ => this.log('login')),
        catchError(this.handleError('login', []))
      );
  }

  registerConsumer(data: any): Observable<any> {
    return this.http.post<any>(apiUrl1 + 'register', data)
      .pipe(
        tap(_ => this.log('login')),
        catchError(this.handleError('login', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }
}

