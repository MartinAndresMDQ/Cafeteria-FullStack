import { Supervisor } from './super';

export class Manager extends Supervisor{
    constructor(id?: number,
        name?: string,
        online?: boolean,
        password?:string) {
            super(id,name,online,password);
    }
}
