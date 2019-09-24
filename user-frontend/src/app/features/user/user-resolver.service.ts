import { Injectable } from '@angular/core';
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { User, UserService } from 'src/app/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class UserResolverService implements Resolve<User> {
  
  constructor(
    private userService: UserService,
    private router: Router,
  ) { }

  resolve(
    route: ActivatedRouteSnapshot, 
    state: RouterStateSnapshot
  ): Observable<User> | any  {
    return this.userService.getUserById(route.params['id'])
      .pipe(catchError((err)=> this.router.navigateByUrl('/')));
  }
}
