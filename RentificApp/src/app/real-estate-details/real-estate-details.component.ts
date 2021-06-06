import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ImageModalComponent } from '../image-modal/image-modal.component';
import { ImageModel } from '../_models/ImageModel';
import { RealEstate } from '../_models/real-estate';
import { Role } from '../_models/role';
import { RealEstateService } from '../_services/real-estate.service';
import { SearchService } from '../_services/search.service';

@Component({
  selector: 'app-real-estate-details',
  templateUrl: './real-estate-details.component.html',
  styleUrls: ['./real-estate-details.component.css']
})
export class RealEstateDetailsComponent implements OnInit {
  realEstate: RealEstate;
  images: ImageModel[] = [];
  closeResult = '';
  imagesList: String[];
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  currentUserRole: Observable<Role>;
  currentUserEmail: string = localStorage.getItem("email");
  isAdmin: Boolean = false;

  constructor(private route: ActivatedRoute, private modalService: NgbModal, 
    private router: Router, private http: HttpClient, private realEstateService: RealEstateService, private searchService: SearchService) {
   }

  ngOnInit() {
    this.searchService.findRealEstateById(this.route.snapshot.params["realEstateId"])
    .subscribe(data => {
      this.realEstate = data;
      this.realEstate.imageModel.forEach(y => {
          this.retrieveResonse = y;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
          this.images.push(this.retrievedImage);
        });
        this.realEstate.imageModel = this.images;
      });
    this.isAdmin = localStorage.getItem('role').toLowerCase() == "admin";
  }

  openModal(imageUrl: ImageModel) {
    const modalRef = this.modalService.open(ImageModalComponent);
    modalRef.componentInstance.imageUrl = imageUrl;
    modalRef.result.then((result) => {
      if (result) {
        console.log(result);
      }
    });
  }

  deleteRealEstate() {
    this.realEstateService.deleteRealEstate(this.realEstate);
  }

  goToEditRealEstateDetails() {
    this.router.navigate(['/edit-real-estate-details', JSON.stringify(this.realEstate)]);
  }

  reserveRealEstate() {
    this.realEstateService.reserveRealEstate(this.realEstate.realEstateId);
  }
}
