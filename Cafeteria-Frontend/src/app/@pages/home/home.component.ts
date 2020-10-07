import {Component} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'home',
  styleUrls: ['./home.component.css'],
  templateUrl: './home.component.html'
})
export class HomeComponent {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }
}