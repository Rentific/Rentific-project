import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { dummyRole } from '../testing-data/role-data';
import { dummyUser1, dummyUser2 } from '../testing-data/user-data';
import { User } from '../_models';
import { Role } from '../_models/role';

import { UserService } from './user.service';

describe('UserService', () => {
    let service: UserService;
  
    beforeEach(async() => {
      TestBed.configureTestingModule({
        imports:[
          HttpClientModule
        ],
        providers:[UserService]
      }).compileComponents();
      service = TestBed.inject(UserService);
    });
  
    const userResponse : User[] = [dummyUser1, dummyUser2];
    const roleResponse : Role = dummyRole;
    
    it('should be created', () => {
      expect(service).toBeTruthy();
    });
  
    describe('getAll', () => {
      it('should return a collection of users', () => {
        let response;
        spyOn(service, 'getAll').and.returnValue(of(userResponse));
        service.getAll().subscribe(res => {
          response = res;
        })
        expect(response).toEqual(userResponse);
      })
    });
  
    describe('getUserRole', () => {
      it('should return a specific role', () => {
        let response;
        spyOn(service, 'getUserRole').and.returnValue(of(roleResponse));
        service.getUserRole("email").subscribe(res => {
          response = res;
        })
        expect(response).toEqual(roleResponse);
      })
    });

    describe('findByEmail', () => {
        it('should return a specific user by email', () => {
          let response;
          spyOn(service, 'findByEmail').and.returnValue(of(dummyUser1));
          service.findByEmail("email").subscribe(res => {
            response = res;
          })
          expect(response).toEqual(dummyUser1);
        })
      });

      describe('update', () => {
        it('should return an updated user', () => {
          let response;
          spyOn(service, 'update').and.returnValue(of(dummyUser1));
          service.update(1, dummyUser1).subscribe(res => {
            response = res;
          })
          expect(response).toEqual(dummyUser1);
        })
      });

      describe('delete', () => {
        it('should delete a user', () => {
          let response;
          spyOn(service, 'delete').and.returnValue(of(dummyUser1));
          service.delete(1).subscribe(res => {
            response = res;
          })
          expect(response).toEqual(dummyUser1);
        })
      });
  
  });