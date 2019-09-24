import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserFormComponent } from './user-form/user-form.component';
import { UserTableComponent } from './user-table/user-table.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserResolverService } from './user-resolver.service';


const routes: Routes = [
  {
    path: "table",
    component: UserTableComponent
  },
  {
    path: ":id",
    component: UserDetailsComponent,
    resolve: {
      user: UserResolverService
    }
  },
  {
    path: "",
    component: UserFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
