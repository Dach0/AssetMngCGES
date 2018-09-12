export interface IFacilityMaintainingCo {
    id?: number;
    name?: string;
}

export class FacilityMaintainingCo implements IFacilityMaintainingCo {
    constructor(public id?: number, public name?: string) {}
}
