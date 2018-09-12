export interface IGroundSticksDrive {
    id?: number;
    gsDrive?: string;
}

export class GroundSticksDrive implements IGroundSticksDrive {
    constructor(public id?: number, public gsDrive?: string) {}
}
