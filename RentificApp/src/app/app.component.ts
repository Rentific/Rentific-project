import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { User } from './_models';
import { AuthGuard } from './_helpers';
import { AuthenticationService } from './_services';


@Component({ selector: 'app-root', templateUrl: 'app.component.html', styleUrls: ['./app.component.css'] })
export class AppComponent {
    currentUser: User;
    isLoggedInProperty: Boolean;
   
   constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        this.authenticationService.currentUser.subscribe(x => {
            this.currentUser = x;
            this.isLoggedInProperty = this.currentUser?.access_token != null;
        });
        
        
    }
    

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['']);
    }

   
}