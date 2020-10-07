import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    {
      path: 'drinks',
      loadChildren: () => import('./@pages/drinks/drinks.module').then(m => m.DrinksModule),
    },
    {
      path: 'additionals',
      loadChildren: () => import('./@pages/drinks/drinks.module').then(m => m.DrinksModule),
    },
    {
      path: 'orders',
      loadChildren: () => import('./@pages/combinations/combinations.module').then(m => m.CombinationsModule),
    },
    ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
