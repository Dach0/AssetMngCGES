export interface IPylonType {
    id?: number;
    pylonType?: string;
}

export class PylonType implements IPylonType {
    constructor(public id?: number, public pylonType?: string) {}
}
