import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { dummyRealEstate1, dummyRealEstate2 } from '../testing-data/mocked-data';
import { RealEstate } from '../_models/real-estate';

import { SearchService } from './search.service';

describe('SearchService', () => {
  let service: SearchService;

  beforeEach(async() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientModule
      ],
      providers:[SearchService]
    }).compileComponents();
    service = TestBed.inject(SearchService);
  });

  const realEstateResponse : RealEstate[] = [dummyRealEstate1, dummyRealEstate2];

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getAllRealEstates', () => {
    it('should return a collection of real estates', () => {
      let response;
      spyOn(service, 'getAllRealEstates').and.returnValue(of(realEstateResponse));
      service.getAllRealEstates().subscribe(res => {
        response = res;
      })
      expect(response).toEqual(realEstateResponse);
    })
  });

  describe('findRealEstateById', () => {
    it('should return a specific real estate', () => {
      let response;
      spyOn(service, 'findRealEstateById').and.returnValue(of(dummyRealEstate1));
      service.findRealEstateById(1).subscribe(res => {
        response = res;
      })
      expect(response).toEqual(dummyRealEstate1);
    })
  });

});
