import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Observable } from 'rxjs';
import { filter, first, map, take } from 'rxjs/operators';
import { ImageModel } from '../_models/ImageModel';
import { RealEstate } from '../_models/real-estate';
import { RealEstateResponse } from '../_models/real-estate-response';
import { Role } from '../_models/role';
import { AlertService, UserService } from '../_services';
import { SearchService } from '../_services/search.service';
import { mergeMap } from 'rxjs/operators';
import { User } from '../_models/user';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {
  realEstateResponse: RealEstateResponse;
  realEstates: RealEstate[];
  images: ImageModel[] = [];
  imagesToDisplay: ImageModel[];
  totalPages: number;
  pageSize: number = 10;
  currentPage: number = 1;
  keyword: string;
  isAdmin: Boolean = false;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  mergeTest: Observable<any>;
  public role: Observable<string>;

  constructor(private searchService: SearchService,
    private alertService: AlertService,
    private router: Router,
    private userService: UserService, private http: HttpClient) {
    this.role = new Observable<string>();
  }

  user: User;

  ngOnInit() {
    this.userService.findByEmail(localStorage.getItem('email'))
      .subscribe(user => {
        this.user = user;
        localStorage.setItem('userId', user.userId.toString());
        localStorage.setItem('role', user.role.name);
        console.log(localStorage.getItem('userId'));
        console.log(localStorage.getItem('role'));
        this.isAdmin = localStorage.getItem('role').toLowerCase() == "admin";
      });
  }

  addNewRealEstate() {
    this.router.navigate(['/add-real-estate']);
  }

  saveCurrentUserRole() {
    this.userService.getUserRole(localStorage.getItem("email")).
      subscribe(data => localStorage.setItem('role', data.name));
  }

  onSearched(search_query: string) {
    // this.searchService.searchRealEstates(search_query)
    //   .pipe(mergeMap(res => {
    //     this.realEstateResponse = res;
    //     this.realEstates = res.realEstateList;
    //     this.pageSize = res.totalItems.toString();
    //     this.totalPages = res.totalPages.toString();
    //     this.currentPage = res.currentPage.toString();
    //     this.realEstates.forEach(x => {
    //       x.imageModel.forEach(y => {
    //         this.http.get(`http://localhost:8762/real-estate/real-estate/get/${y.id}`)
    //           .subscribe(image => {
    //             this.retrieveResonse = image;
    //             this.base64Data = this.retrieveResonse.picByte;
    //             this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
    //             this.images.push(this.retrievedImage);
    //           });
    //       });
    //       x.imageModel = this.images;
    //       this.images = [];
    //     });

    //   }));

    this.searchService.searchRealEstates(search_query)
      .subscribe(data => {
        this.realEstateResponse = data;
        this.realEstates = data.realEstateList;
        this.pageSize = data.totalItems;
        this.totalPages = data.totalPages;
        this.currentPage = data.currentPage;
        this.realEstates.forEach(x => {
          x.imageModel.forEach(y => {
            this.retrieveResonse = y;
            this.base64Data = this.retrieveResonse.picByte;
            this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
            this.images.push(this.retrievedImage);
          });
          x.imageModel = this.images;
          this.images = [];
        });
      });


    console.log(this.realEstates);
  }

  goToRealEstateDetails(realEstate: RealEstate) {
    this.router.navigate(['/real-estate-details', JSON.stringify(realEstate)]);
  }

  // onChangePage(search_query: string = '') {
  //   // update current page of items
  //   return this.http.get<RealEstateResponse>(`http://localhost:8762/real-estate/real-estate/?size=${this.pageSize}&page=${this.currentPage}&keyword=${search_query}`)
  //   .pipe(
  //     map(res => res)
  //   );
  // }
}



