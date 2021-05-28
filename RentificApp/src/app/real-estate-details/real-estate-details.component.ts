import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ImageModalComponent } from '../image-modal/image-modal.component';
import { RealEstate } from '../_models/real-estate';
import { Role } from '../_models/role';

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

  constructor(private route: ActivatedRoute, private modalService: NgbModal, private http: HttpClient) {
   }

  ngOnInit() {
    this.realEstate2 = JSON.parse(this.route.snapshot.params["realEstate"]);
    this.isAdmin = localStorage.getItem("role") == "1";
    // this.imagesList.push("https://picsum.photos/600/500?random=1");
    // this.imagesList.push("https://picsum.photos/600/500?random=2");
    // this.imagesList.push("https://picsum.photos/600/500?random=3");
  }

  openModal(imageUrl: string) {
    const modalRef = this.modalService.open(ImageModalComponent);
    modalRef.componentInstance.imageUrl = imageUrl;
    modalRef.result.then((result) => {
      if (result) {
        console.log(result);
      }
    });

}

}
