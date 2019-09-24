import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from 'src/app/core';
import { UserService } from '../../../core/services/user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  user: User = {} as User; 
  userForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService
  ) { 
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      groupId: ['', Validators.required]
    });
  }

  ngOnInit() {
  }

  submitForm(){
    const user: User = this.userForm.value;
    this.userService.save(user)
      .subscribe(user => {
        this.userForm.reset();
      });
  }

  updateUser(values: Object){
    Object.assign(this.user, values);
  }

}
