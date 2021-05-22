import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, first, take } from 'rxjs/operators';
import { RealEstate } from '../_models/real-estate';
import { AlertService } from '../_services';
import { SearchService } from '../_services/search.service';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {

  realEstates$: Observable<any>;
  realEstates2: any[];
  realEstates: any[];
  realEstate: any;
  constructor(private searchService: SearchService,
    private alertService: AlertService,    
    private router: Router) { 
    }

  ngOnInit(): void {
    //this.onLoad();
    // this.realEstates$ = this.searchService.getAllRealEstates();
    // this.realEstates$.pipe(filter(data => data != null), take(1)).subscribe(
    //   data => {
    //     this.realEstates2 = data;
    //   }
    // );
   }

  onLoad() {
    this.searchService.getAllRealEstates()
      .subscribe(
          data => {
              this.realEstates = data;
          },
          error => {
              this.alertService.error(error);
          });
     }
         
     goToRealEstateDetails() {
      this.searchService.findRealEstateById(1)
      .subscribe(data => {
        this.realEstate = data.body;
        this.router.navigate(['/real-estate-details']);
      });

      console.log(this.realEstate);
    }
     

  }
  
    

