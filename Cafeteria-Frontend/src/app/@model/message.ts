// import { Client } from './client';

// export interface Message {
//     message: string;
//     fromId: string;
//     toId: string;
//     date: Date;
//   }

  
export class Message {
    public message: string;
    public fromId: string;
    public toId: string;
    public date: Date;
    constructor(
        message?: string,
        fromId?: string,
        toId?: string,
        date?: Date) {
            this.message = message || "";
            this.fromId = fromId || "";
            this.toId = toId || "";
        this.date = date || new Date();
    }
}
