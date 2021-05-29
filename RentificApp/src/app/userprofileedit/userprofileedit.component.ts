import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { User } from '../_models';
import { Role } from '../_models/role';
import { AlertService, UserService } from '../_services';

@Component({
  selector: 'app-userprofileedit',
  templateUrl: './userprofileedit.component.html',
  styleUrls: ['./userprofileedit.component.css']
})
export class UserprofileeditComponent implements OnInit {
  
  addingForm: FormGroup;
  baseUrl: string;
  selectedFile: File;
  retrievedImage: any;
  loading = false;
  submitted = false;
  role:Role;
  retrieveResonse: any;
  message: string;
  user: User;
  constructor(private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private alertService: AlertService) { 
  }

   ngOnInit() {   
        this.addingForm = this.formBuilder.group({
          firstName: ['', Validators.required],
          lastName: ['', Validators.required],
          email: ['', Validators.required],
          password: ['', [Validators.required, Validators.minLength(8)]],
          address: ['', Validators.required],
          city: ['', Validators.required],
          country: ['', Validators.required],
          phone: ['', Validators.required],
          dateOfBirth: ['', Validators.required],
          //role: new Role(
      });
      this.userService.findByEmail(localStorage.getItem('email'))
      .subscribe(user => {
        this.user = user;
    });
  }

  get form() { return this.addingForm.controls; }
  goToSearch(){
    this.router.navigate(['/search']);
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.addingForm.invalid) {
        return;
    }

    this.loading = true;
    this.userService.update(this.user.userId, this.user)
        .pipe(first())
        .subscribe(
            data => {
                //this.alertService.success('Successful', true);
               this.router.navigate(['/viewprofile']);
            },
            error => {
                this.alertService.error(error);
                this.loading = false;
            });
  }
}

