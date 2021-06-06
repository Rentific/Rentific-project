import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { combineLatest, Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { AddNewRealEstateComponent } from '../add-new-real-estate/add-new-real-estate.component';
import { ModalComponent } from '../modal/modal.component';
import { ModalService2 } from '../modal/modal.service';
import { AlertService } from '../_alert';
import { ImageModel } from '../_models/ImageModel';
import { RealEstate } from '../_models/real-estate';
import { RequestCreateInvoice } from '../_models/RequestCreateInvoice.model';
import { SaveRealEstate } from '../_models/SaveRealEstate';


@Injectable({
  providedIn: 'root'
})
export class RealEstateService {
  baseUrl: string;
  imageModel: ImageModel;
  saveRealEstate: SaveRealEstate;
  deleteRealEstateObject: RealEstate;

  options = {
    autoClose: false,
    keepAfterRouteChange: false
  };

  imageObservable$: Observable<any>;
  realEstateObservable$: Observable<any>;

  constructor(private http: HttpClient, private router: Router,
    private alertService: AlertService, private modalService: NgbModal, private modalService2: ModalService2) {
    this.baseUrl = 'http://localhost:8762/real-estate/real-estate';
    this.modalService2.getModalContinueEmitter().subscribe(() => this.executeDeleteRealEstate());
  }

  addNewRealEstate(realEstate: RealEstate, imageFile: FormData) {   
    this.http.post<RealEstate>(this.baseUrl + '/add', realEstate)
      .subscribe((response) => {
        this.http.post(this.baseUrl + '/image/upload/' + `${response.realEstateId}`, imageFile, { observe: 'response' })
          .subscribe(
            data => {       
              this.router.navigate(['/search']);
              this.alertService.success('Uspješno ste dodali nekretninu!', this.options);
            },
            error => {
              this.alertService.error("Došlo je do greške. Molimo vas pokušajte ponovo!", this.options);
            }
          );
      });
  }

  deleteRealEstate(realEstate: RealEstate) {
    this.openModal("Obriši nekretninu", "Da li ste sigurni da želite obrisati nekretninu?");
    this.deleteRealEstateObject = realEstate;    
  }

  private executeDeleteRealEstate() {
    this.http.delete(this.baseUrl + `/delete/${this.deleteRealEstateObject.realEstateId}`, { observe: 'response' })
    .subscribe(
      data => {       
        this.modalService.dismissAll();  
        this.router.navigate(['/search']);
        this.alertService.success('Uspješno ste obrisali nekretninu!', false);
      },
      error => {
        this.alertService.error("Došlo je do greške. Molimo vas pokušajte ponovo!");
      }
    );
  }

  updateRealEstate(realEstate: RealEstate, imageFile: FormData) {
    this.realEstateObservable$ = this.updateRealEstateDetails(realEstate);
    this.imageObservable$ = this.updateImageForRealEstate(realEstate, imageFile);
    combineLatest([this.realEstateObservable$, this.imageObservable$]).pipe(take(1))
    .subscribe(
      data => {       
        this.router.navigate(['/search']);
        this.alertService.success('Uspješno ste ažurirali nekretninu!', false);
      },
      error => {
        this.alertService.error("Došlo je do greške. Molimo vas pokušajte ponovo!");
      }
    );
  }

  updateRealEstateDetails(realEstate: RealEstate) : Observable<RealEstate> {
    return this.http.put<RealEstate>(this.baseUrl + `/update/${realEstate.realEstateId}`, realEstate);
  }

  updateImageForRealEstate(realEstate: RealEstate, imageFile: FormData) : Observable<ImageModel> {
    return this.http.put<ImageModel>(this.baseUrl + `/image/update/${realEstate.realEstateId}`, imageFile);
  }

  reserveRealEstate(realEstateId: number) {
    this.executeReserveRealEstate(new RequestCreateInvoice(Number(localStorage.getItem('userId')), realEstateId))
    .subscribe(      
      data => {     
        console.log(data);  
        this.modalService.dismissAll();  
        this.router.navigate(['/search']);
        this.alertService.success('Uspješno ste rezervisali nekretninu!', false);
      },
      error => {
        this.alertService.error("Došlo je do greške. Molimo vas pokušajte ponovo!");
      }
    );
  }

  private executeReserveRealEstate(requestCreateInvoice: RequestCreateInvoice) : Observable<any> {
    return this.http.post<any>(`http://localhost:8762/invoice/invoice/add`, JSON.stringify(requestCreateInvoice), {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
  }

  openModal(title: string, text: string) {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.title = title;
    modalRef.componentInstance.text = text;
  }
}
