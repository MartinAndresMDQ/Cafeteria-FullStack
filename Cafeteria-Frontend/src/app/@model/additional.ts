import { Drink } from './drink';

export class Additional extends Drink{
    public id: number;
    public name: string;
    public price: number;
    constructor(id?: number,
        name?: string,
        price?: number) {
            super(id,name,price);
    }
}
