export class Drink {
    public id: number;
    public name: string;
    public price: number;
    public hidden: boolean;
    constructor(id?: number,
        name?: string,
        price?: number,
        hidden?: boolean) {
        this.id = id || 0;
        this.name = name || "";
        this.price = price || 0;
        this.hidden = hidden || false;
    }
}
