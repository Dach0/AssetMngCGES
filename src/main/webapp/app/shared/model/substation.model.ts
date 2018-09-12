import { IFacility } from 'app/shared/model//facility.model';

export interface ISubstation {
    id?: number;
    name?: string;
    operationYear?: number;
    facility?: IFacility;
}

export class Substation implements ISubstation {
    constructor(public id?: number, public name?: string, public operationYear?: number, public facility?: IFacility) {}
}
