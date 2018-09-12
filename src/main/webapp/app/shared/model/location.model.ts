export interface ILocation {
    id?: number;
    locationName?: string;
}

export class Location implements ILocation {
    constructor(public id?: number, public locationName?: string) {}
}
