import { Routes, RouterModule } from '@angular/router';

import { DrinksComponent } from './drinks.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: DrinksComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DrinksRoutingModule { }
