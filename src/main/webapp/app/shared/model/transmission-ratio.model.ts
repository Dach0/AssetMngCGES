export interface ITransmissionRatio {
    id?: number;
    transmissionRatio?: string;
}

export class TransmissionRatio implements ITransmissionRatio {
    constructor(public id?: number, public transmissionRatio?: string) {}
}
