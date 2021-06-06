import { HttpClient, HttpResponse } from '@angular/common/http';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RealEstate } from '../_models/real-estate';
import { AuthenticationService } from './authentication.service';
import { map } from 'rxjs/operators';
import { RealEstateResponse } from '../_models/real-estate-response';

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
    return this.http.get<any>(`http://localhost:8762/real-estate/real-estate/${id}`);
  }

  searchRealEstates(search_query: string, page: number = 0, size: number = 10, sort: String[]): Observable<RealEstateResponse> {
    return this.http.get<RealEstateResponse>(`http://localhost:8762/real-estate/real-estate/?keyword=${search_query}&page=${page}&size=${size}&sort=${sort}`)
    .pipe(
      map(res => res)
    );
  }
}
