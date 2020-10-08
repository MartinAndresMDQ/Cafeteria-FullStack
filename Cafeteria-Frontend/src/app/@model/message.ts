import { Client } from './client';

export class Message {
    public id: number;
    public message: string;
    public fecha: Date;
    public envia: Client;
    public recibe: Client;
    public visto: boolean;
    constructor(id?: number,
        message?: string,
        fecha?: Date,
        envia?: Client,
        recibe?: Client,
        visto?: boolean) {
        this.id = id || 0;
        this.message = message || "";
        this.fecha = fecha || new Date();
        this.envia = envia || null;
        this.recibe = recibe || null;
        this.visto = visto || false;
    }
}
