import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { first } from 'rxjs/operators';
import { User } from '../_models';
import { UserService } from '../_services';
@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {
  user: User;

    constructor(private route: ActivatedRoute,private userService: UserService) { }
      ngOnInit() {   
      this.userService.findByEmail(localStorage.getItem('email'))
      .subscribe(user => {
        this.user = user;
    });

  }
 

}
  
