import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../_models';

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
}