import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RealEstate } from '../_models/real-estate';
import { StateEnum } from '../_models/stateEnum';
import { AlertService, AuthenticationService } from '../_services';
import { RealEstateService } from '../_services/real-estate.service';


@Component({
  selector: 'app-add-new-real-estate',
  templateUrl: './add-new-real-estate.component.html',
  styleUrls: ['./add-new-real-estate.component.css']
})
export class AddNewRealEstateComponent implements OnInit {

  addingForm: FormGroup;
  realEstate : RealEstate;
  baseUrl: string;

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
    private alertService: AlertService) { 
  }

  ngOnInit(){
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

  addNewRealEstate(){
    this.realEstate = new RealEstate(
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
      // .subscribe((response) => {
      //   if (response.status === 200) {
      //     console.log('Image uploaded successfully');
      //   } else {
      //     console.log('Image not uploaded successfully');
      //   }
      //  } );
  }

  get form() { return this.addingForm.controls; }
  goToSearch(){
    this.router.navigate(['/search']);
  }

  //IMAGE METHODS
   //Gets called when the user selects an image
   public onFileChanged(event : any) {
    //Select File
    this.selectedFile = event.target.files[0];
    this.uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);

  }


  //Gets called when the user clicks on submit to upload the image
  onUpload() {
    console.log(this.selectedFile);
    
    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    
  
    //Make a call to the Spring Boot Application to save the image
    this.httpClient.post('http://localhost:8762/real-estate/real-estate/image/upload', this.uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );


  }

    //Gets called when the user clicks on retieve image button to get the image from back end
    getImage() {
    //Make a call to Sprinf Boot to get the Image Bytes.
    this.httpClient.get('http://localhost:8762/real-estate/real-estate/image/get/' + this.imageName)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
  }

}
