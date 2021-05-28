import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { RequestOptions } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AlertService, AuthenticationService } from '.';
import { RealEstate } from '../_models/real-estate';

@Injectable({
  providedIn: 'root'
})
export class RealEstateService {
  baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8762/real-estate/real-estate';
  }

  addNewRealEstate(realEstate: RealEstate, imageFile: FormData) : Observable<any> {
    return this.http.post(this.baseUrl +`/add?realEstate=${realEstate}`, imageFile);
      
    
    // this.http.post(this.baseUrl + `/add`, realEstate, {imageFile: imageFile})
    //   .subscribe((response) => {
    //     if (response.status === 200) {
    //       console.log('Image uploaded successfully');
    //     } else {
    //       console.log('Image not uploaded successfully');
    //     }
    //   }
    //   );
  }


}
