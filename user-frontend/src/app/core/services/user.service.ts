import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiService } from './api.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/user.model';
import { take } from 'rxjs/operators';

@Injectable()
export class UserService {

  private endpoint: string = "users"

  private usersSubject:BehaviorSubject<User[]>; 
  public users$:Observable<User[]>; 

  constructor(
    private http: HttpClient,
    private apiSerivce: ApiService
  ) {
    this.usersSubject = new BehaviorSubject<User[]>([]);
    this.users$ = this.usersSubject.asObservable();

    this.apiSerivce.get(this.endpoint)
      .subscribe(users => {
        this.usersSubject.next(users);
      });
  }

  getUsers(): Observable<User[]>{
    return this.users$;
  }

  getUserById(id: String): Observable<User>{
    return this.apiSerivce.get(`${this.endpoint}/${id}`);
  }

  save(user: User): Observable<User>{
    const response = new BehaviorSubject<User>({} as User);
    this.apiSerivce.post(this.endpoint, user).pipe(take(1))
      .subscribe( (resUser:User)=>{
      this.usersSubject.next([...this.usersSubject.value, resUser]);
      response.next(resUser);
    });
    return response.asObservable();
  }

  deleteUser(user: User){
    const response = new BehaviorSubject<User>({} as User);
    const obs = this.apiSerivce.delete(`${this.endpoint}/${user.id}`).pipe(take(1))
      obs.subscribe((resUser:User) => {
        const ind = this.usersSubject.value.findIndex((u)=>u.id === user.id);
        const users = [...this.usersSubject.value];
        users.splice(ind, 1);
        this.usersSubject.next(users);
        response.next(resUser);
      });
    return response.asObservable();
  }

}
