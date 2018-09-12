export interface IServiceCompany {
    id?: number;
    companyName?: string;
}

export class ServiceCompany implements IServiceCompany {
    constructor(public id?: number, public companyName?: string) {}
}
