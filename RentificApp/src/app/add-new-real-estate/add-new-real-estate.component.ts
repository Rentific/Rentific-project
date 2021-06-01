import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RealEstate } from '../_models/real-estate';
import { StateEnum } from '../_models/stateEnum';
import { RealEstateService } from '../_services/real-estate.service';

@Component({
  selector: 'app-add-new-real-estate',
  templateUrl: './add-new-real-estate.component.html',
  styleUrls: ['./add-new-real-estate.component.css']
})

export class AddNewRealEstateComponent implements OnInit {
  addingForm: FormGroup;
  realEstate: RealEstate;

  // Image properties
  selectedFile: File;
  uploadImageData = new FormData();

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private realEstateService: RealEstateService) {
  }

  ngOnInit() {
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

  addNewRealEstate() {
    this.realEstate = new RealEstate(
      0,
      this.form.name.value,
      this.form.price.value,
      this.form.address.value,
      this.form.country.value,
      this.form.city.value,
      this.form.description.value,
      false,
      1,
      StateEnum.Namjesten, null);
    this.realEstateService.addNewRealEstate(this.realEstate, this.uploadImageData);
    this.addingForm.reset();
  }

  get form() { return this.addingForm.controls; }
  
  goToSearch() {
    this.router.navigate(['/search']);
  }

  // Gets called when the user selects an image
  public onFileChanged(event: any) {
    this.selectedFile = event.target.files[0];
    this.uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  }
}
