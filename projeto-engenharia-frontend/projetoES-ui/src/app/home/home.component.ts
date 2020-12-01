import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  cars: any =[];

  constructor(private http: HttpClient) {
    this.cars = [
      {name: 'assets/miniatura01.jpg'},
      {name: 'assets/miniatura02.jpg'},
      {name: 'assets/miniatura03.jpg'},
      {name: 'assets/miniatura04.jpg'}
    ]
  }

  ngOnInit(): void {
  }

}
