export interface IManufacturer {
    id?: number;
    manufactName?: string;
}

export class Manufacturer implements IManufacturer {
    constructor(public id?: number, public manufactName?: string) {}
}
