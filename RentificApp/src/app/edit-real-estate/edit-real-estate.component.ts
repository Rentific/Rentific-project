import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RealEstate } from '../_models/real-estate';
import { StateEnum } from '../_models/stateEnum';
import { AlertService } from '../_services';
import { RealEstateService } from '../_services/real-estate.service';

@Component({
  selector: 'app-edit-real-estate',
  templateUrl: './edit-real-estate.component.html',
  styleUrls: ['./edit-real-estate.component.css']
})
export class EditRealEstateComponent implements OnInit {
  realEstate: RealEstate;
  realEstate2: RealEstate;
  addingForm: FormGroup;

  //Image properties
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  uploadImageData = new FormData();

  constructor(private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
    private realEstateService: RealEstateService,
    private alertService: AlertService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.realEstate = JSON.parse(this.route.snapshot.params["realEstate"]);

    this.addingForm = this.formBuilder.group({
      name: ['', Validators.required],
      price: ['', Validators.required],
      size: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      country: ['', Validators.required],
      rooms: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  get form() { return this.addingForm.controls; }

  goToSearch() {
    this.router.navigate(['/search']);
  }

  public onFileChanged(event: any) {
    this.selectedFile = event.target.files[0];
    this.uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  }

  updateRealEstate() {
    this.realEstate2 = new RealEstate(
      this.realEstate.realEstateId,
      this.form.name.value,
      this.form.price.value,
      this.form.address.value,
      this.form.country.value,
      this.form.city.value,
      this.form.description.value,
      false,
      1,
      StateEnum.Namjesten, null);
    this.realEstateService.updateRealEstate(this.realEstate2, this.uploadImageData);
    this.addingForm.reset();
  }

}
