import { Client } from './client';

export class Operator extends Client{
    
    public password:string;
    public available:boolean;
    
    constructor(id?: number,
        name?: string,
        online?: boolean,
        available?: boolean,
        password?:string) {
            super(id,name,online);
            this.password = password || "";
            this.available = available || false;
    }
}
