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

@Component({
  selector: 'app-real-estate-details',
  templateUrl: './real-estate-details.component.html',
  styleUrls: ['./real-estate-details.component.css']
})
export class RealEstateDetailsComponent implements OnInit {
  realEstate2: RealEstate;
  closeResult = '';
  imagesList: String[];
  currentUserRole: Observable<Role>;
  currentUserEmail: string = localStorage.getItem("email");
  isAdmin: Boolean = false;

  constructor(private route: ActivatedRoute, private modalService: NgbModal, 
    private router: Router, private http: HttpClient, private realEstateService: RealEstateService) {
   }

  ngOnInit() {
    this.realEstate2 = JSON.parse(this.route.snapshot.params["realEstate"]);
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
    this.realEstateService.deleteRealEstate(this.realEstate2);
  }

  goToEditRealEstateDetails() {
    this.router.navigate(['/edit-real-estate-details', JSON.stringify(this.realEstate2)]);
  }
}
