import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ImageModalComponent } from '../image-modal/image-modal.component';
import { RealEstate } from '../_models/real-estate';

@Component({
  selector: 'app-real-estate-details',
  templateUrl: './real-estate-details.component.html',
  styleUrls: ['./real-estate-details.component.css']
})
export class RealEstateDetailsComponent implements OnInit {
  realEstate2: RealEstate;
  closeResult = '';
  imagesList: String[];

  constructor(private route: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit() {
    // First get the product id from the current route.
    this.realEstate2 = JSON.parse(this.route.snapshot.params["realEstate"]);
    this.imagesList.push("https://picsum.photos/600/500?random=1");
    this.imagesList.push("https://picsum.photos/600/500?random=2");
    this.imagesList.push("https://picsum.photos/600/500?random=3");
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
