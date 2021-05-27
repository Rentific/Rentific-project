import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Observable } from 'rxjs';
import { filter, first, take } from 'rxjs/operators';
import { RealEstate } from '../_models/real-estate';
import { RealEstateResponse } from '../_models/real-estate-response';
import { AlertService } from '../_services';
import { SearchService } from '../_services/search.service';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {
  realEstateResponse: RealEstateResponse;
  realEstates: RealEstate[];
  totalPages: string;
  pageSize: string;
  currentPage: string;
  keyword: string;
  constructor(private searchService: SearchService,
    private alertService: AlertService,    
    private router: Router) { 
    }

  ngOnInit(): void {
   
  }

    onSearched(search_query: string) {      
      this.searchService.searchRealEstates(search_query)
        .subscribe(data => {
          this.realEstateResponse = data;
          this.realEstates = data.realEstateList;
          this.pageSize = data.totalItems.toString();
          this.totalPages = data.totalPages.toString();
          this.currentPage = data.currentPage.toString();
        });
    }

  goToRealEstateDetails(realEstate: RealEstate) {
    this.router.navigate(['/real-estate-details', JSON.stringify(realEstate)]); 
  }
     

  }
  
    

