export interface IDisconnectorDrive {
    id?: number;
    discDrive?: string;
}

export class DisconnectorDrive implements IDisconnectorDrive {
    constructor(public id?: number, public discDrive?: string) {}
}
