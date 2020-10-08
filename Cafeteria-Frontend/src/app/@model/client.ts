export class Client {
    public id: number;
    public name: string;
    public online: boolean;
    constructor(id?: number,
        name?: string,
        online?: boolean) {
        this.id = id || 0;
        this.name = name || "";
        this.online = online || false;
    }
}
