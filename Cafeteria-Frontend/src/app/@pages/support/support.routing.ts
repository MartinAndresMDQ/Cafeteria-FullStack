import { Routes, RouterModule } from '@angular/router';

import { SupportComponent } from './support.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: SupportComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SupportRoutingModule { }
