import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../_models';
import { UserService } from '../_services';

@Component({
  selector: 'app-userprofileedit',
  templateUrl: './userprofileedit.component.html',
  styleUrls: ['./userprofileedit.component.css']
})
export class UserprofileeditComponent implements OnInit {
  user: User;
    constructor(private route: ActivatedRoute,private userService: UserService) { }
      ngOnInit() {   
      this.userService.findByEmail(localStorage.getItem('email'))
      .subscribe(user => {
        this.user = user;
    });
  }
}

