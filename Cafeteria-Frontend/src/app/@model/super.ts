import { Operator } from './operator';

export class Supervisor extends Operator{
    constructor(id?: number,
        name?: string,
        online?: boolean,
        available?: boolean,
        password?:string) {
            super(id,name,online,available,password);
    }
}
