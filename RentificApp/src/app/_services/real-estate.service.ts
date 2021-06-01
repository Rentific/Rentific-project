import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService, AuthenticationService } from '.';
import { ImageModel } from '../_models/ImageModel';
import { RealEstate } from '../_models/real-estate';
import { SaveRealEstate } from '../_models/SaveRealEstate';


@Injectable({
  providedIn: 'root'
})
export class RealEstateService {
  baseUrl: string;
  imageModel: ImageModel;
  saveRealEstate: SaveRealEstate;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8762/real-estate/real-estate';
  }

  addNewRealEstate(realEstate: RealEstate, imageFile: FormData) {
    this.http.post<RealEstate>(this.baseUrl + '/add', realEstate)
      .subscribe((response) => {
        this.http.post(this.baseUrl + '/image/upload/' + `${response.realEstateId}`, imageFile, { observe: 'response' })
          .subscribe((res2) => {
            console.log(res2);
          });
      });
  }

  deleteRealEstate(realEstate: RealEstate) {
    this.http.delete(this.baseUrl + `/delete/${realEstate.realEstateId}`);
  }

  updateRealEstate(realEstate: RealEstate, imageFile: FormData) {
    this.http.put<RealEstate>(this.baseUrl + `/update/${realEstate.realEstateId}`, realEstate, { observe: 'response' })
      .subscribe((res2) => {
        console.log(res2);
      });
    this.http.put<ImageModel>(this.baseUrl + `/image/update/${realEstate.realEstateId}`, imageFile, { observe: 'response' })
      .subscribe((res2) => {
        console.log(res2);
      });
  }
}
