import { Component, OnInit } from '@angular/core';
type Link = {name: string, url: string}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  

  features: Link[] = [
    {name: "Create a user", url: "users"},
    {name: "Delete a user", url: "users"},
    {name: "Show users", url: "users"},
    {name: "Show a specific user by clicking on it", url: "users"},
    
  ]

  constructor() { }

  ngOnInit() {
  }

}
