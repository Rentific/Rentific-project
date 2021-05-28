import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewRealEstateComponent } from './add-new-real-estate.component';

describe('AddNewRealEstateComponent', () => {
  let component: AddNewRealEstateComponent;
  let fixture: ComponentFixture<AddNewRealEstateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewRealEstateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewRealEstateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
