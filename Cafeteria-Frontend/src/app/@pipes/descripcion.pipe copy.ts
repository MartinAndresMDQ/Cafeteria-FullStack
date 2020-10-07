import {Pipe, PipeTransform} from '@angular/core';
import { Combination } from '../@model/combination';

@Pipe({name: 'descripcionPipe',
pure: false})
export class descripcionPipe implements PipeTransform {
  transform(order: Combination): any {
    let cadena="";
    if(order.drink!=null && order.drink.id!=0)
    cadena += order.drink.name;
    for(let i in order.additionals){
      cadena += ((i=="0")?" con ":(i==(order.additionals.length-1).toString())?" y ": ", ")+order.additionals[i].name;
    }
    return cadena;
  }
}
