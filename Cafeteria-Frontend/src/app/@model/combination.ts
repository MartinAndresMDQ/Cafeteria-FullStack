import { Additional } from './additional';
import { Drink } from './drink';

export class Combination {
    public id: number;
    public name: string;
    public fecha: Date;
    public drink: Drink;
    public additionals: Additional[];
    constructor(id?: number,
        name?: string,
        fecha?: Date,
        drink?: Drink,
        additionals?: Additional[]) {
        this.id = id || 0;
        this.name = name || "";
        this.fecha = fecha || new Date();
        this.drink = drink || new Drink();
        this.additionals = additionals || [];
    }
}
