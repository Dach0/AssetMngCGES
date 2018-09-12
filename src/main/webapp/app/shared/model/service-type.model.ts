export interface IServiceType {
    id?: number;
    typeDescription?: string;
}

export class ServiceType implements IServiceType {
    constructor(public id?: number, public typeDescription?: string) {}
}
