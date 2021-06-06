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
import { PriceSortEnum } from '../_models/PriceSortEnum';

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
  allPages: number[] = [];
  pageSize: number = 9;
  currentPage: number = 0;
  keyword: string;
  isAdmin: Boolean = false;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  mergeTest: Observable<any>;
  public role: Observable<string>;
  priceSort = PriceSortEnum;
  priceSortEnumKeys: any[] = [];
  selectedPriceSort: any = PriceSortEnum.Rastuće;
  sort: String[] = ["price", "asc"];
  i : number = 0;

  constructor(private searchService: SearchService,
    private router: Router,
    private userService: UserService, private http: HttpClient) {
    this.role = new Observable<string>();
    this.priceSortEnumKeys = Object.values(this.priceSort).filter(f => isNaN(Number(f)));
  }

  user: User;

  ngOnInit() {
    this.userService.findByEmail(localStorage.getItem('email'))
      .subscribe(user => {
        this.user = user;
        localStorage.setItem('userId', user.userId.toString());
        localStorage.setItem('role', user.role.name);
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
    this.allPages = [];
    this.keyword = search_query;
    this.searchService.searchRealEstates(this.keyword, this.currentPage, this.pageSize, this.sort)
      .subscribe(data => {
        this.realEstateResponse = data;
        this.realEstates = data.realEstateList;
        this.totalPages = data.totalPages;
        for (this.i=0; this.i<this.totalPages; this.i++) {
            this.allPages.push(this.i+1);
        }
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
  }

  goToRealEstateDetails(realEstateId: number) {
    this.router.navigate(['/real-estate-details', realEstateId]);
  }

  onChangePage(item: any) {
    this.currentPage = item;
    this.currentPage--;
    this.searchService.searchRealEstates(this.keyword, this.currentPage, this.pageSize, this.sort)
    .subscribe(data => {
      this.realEstateResponse = data;
      this.realEstates = data.realEstateList;
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
  }

  onPriceSortChanged(item: any) {
    this.selectedPriceSort = item;
    this.sort = [];
    this.sort.push("price");
    if (this.selectedPriceSort == PriceSortEnum[0])
      this.sort.push("asc");
    else this.sort.push("desc");
  }
}



