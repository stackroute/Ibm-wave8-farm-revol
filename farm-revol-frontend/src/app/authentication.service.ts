import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { DAOUser } from 'src/DAOUser';
import { LoginUser } from 'src/Login';


const apiUrl = 'http://localhost:8090/api/farmer/';
const apiUrl1 = 'http://localhost:8092/api/consumer/';
const apiurl2='http://172.23.238.208:8091/authenticate';


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
  private apiUrl3="http://172.23.238.208:8091/forgot-password"
  ForgotPasswordComponent(data:DAOUser):Observable<any>{
    return this.http.post<any>(this.apiUrl3,data);
  }
  private apiUrl4="http://172.23.238.208:8091/reset-password"
  resetPasswordComponent(data:LoginUser):Observable<any>{
    console.log("hello");
    return this.http.put<any>(this.apiUrl4,data);
  }
}

