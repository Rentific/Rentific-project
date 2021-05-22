import { HttpClient, HttpResponse } from '@angular/common/http';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RealEstate } from '../_models/real-estate';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private getRealEstates: string;

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) 
  { 
    this.getRealEstates = 'http://localhost:8762/real-estate/';
  }

  
  // headers: {
  //   'Authorization': `Bearer ${localStorage.getItem('access_token')}`;
  // }

  getAllRealEstates() : Observable<any> {
    var variable = this.http.get<any>(this.getRealEstates);
    return variable;
  }

  findRealEstateById(id: number): Observable<any>  {
    //var headers = new Headers();
    //let currentUser = this.authenticationService.currentUserValue;
    // ako ne vrati token
    //if (currentUser && currentUser.token) {
      
    //}
    //headers.append('Authorization', 'Bearer ' + currentUser.token);
    //headers.append('Content-Type', 'application/json');
    //let options = new RequestOptions({ headers: headers });

    //const headerDict = {
    //  'Content-Type': 'application/json',
    //  'Authorization': 'Bearer ' + currentUser.token
    //}

    //const requestOptions = {
    //  headers: new Headers(headerDict),
    //};

    return this.http.get<any>(`http://localhost:8762/real-estate/${id}`);
  }


}
