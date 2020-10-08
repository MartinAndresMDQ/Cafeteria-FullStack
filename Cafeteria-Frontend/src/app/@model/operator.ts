import { Client } from './client';

export class Operator extends Client{
    
    public password:string;
    
    constructor(id?: number,
        name?: string,
        online?: boolean,
        password?:string) {
            super(id,name,online);
            this.password = password || "";
    }
}
