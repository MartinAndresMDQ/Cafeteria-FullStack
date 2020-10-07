import {Pipe, PipeTransform} from '@angular/core';
import { Combination } from '../@model/combination';

@Pipe({name: 'calcularTotal',
pure: false})
export class calcularTotalPipe implements PipeTransform {
  transform(order: Combination): any {
    let num=0;
    if(order.drink!=null && order.drink.id!=0)
      num = order.drink.price;
    for(let dat of order.additionals){
      num += dat.price;
    }
    return num
  }
}
