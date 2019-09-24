import { Component, OnInit } from '@angular/core';
import { User, UserService } from 'src/app/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {

  users$: Observable<User[]>;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    this.users$ = this.userService.getUsers();
  }

  deleteUser(user:User){
    this.userService.deleteUser(user);
  }

}
