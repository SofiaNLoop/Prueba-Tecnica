import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/model/User';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  url_api = 'http://pruebatecnicabackend-env.eba-tu4mk2vm.us-east-1.elasticbeanstalk.com/api/user';


  constructor( private http: HttpClient ) { }


  // CRUD Consume API
  // Get All Users
  listUsers(): Observable<any>{
    return this.http.get(`${this.url_api}/list-user`)
  }

  // Create New User
  createUser( user: User ): Observable<any> {
    return this.http.post(`${this.url_api}/new-user`, user);
  }

  // Update User
  updateUser( user: User ): Observable<any> {
    return this.http.put(`${this.url_api}/update-user`, user);
  }

  // Delete User
  deleteUser( id: Number ): Observable<any>{
    return this.http.delete(`${this.url_api}/delete-user/${id}`);
  }

}
 