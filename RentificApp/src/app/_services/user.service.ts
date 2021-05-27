import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../_models';
import { map } from 'rxjs/operators';
import { Role } from '../_models/role';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {
    private usersUrl: string;
    constructor(private http: HttpClient) {  
        this.usersUrl = 'http://localhost:8762/users/user'
    }

    getAll() {
        return this.http.get<User[]>(this.usersUrl + '/all');
    }

    register(user: User) {
        return this.http.post(this.usersUrl +'/add', user);
    }

    delete(id: number) {
        return this.http.delete(this.usersUrl +'/delete/${id}');
    }

    getUserRole(email: String) : Observable<Role> {
        return this.http.get<Role>(this.usersUrl + `/role?email=${email}`).pipe(map(res => res));
    }
}