import { Routes, RouterModule } from '@angular/router';

import { CombinationsComponent } from './combinations.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: CombinationsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CombinationsRoutingModule { }
