import { ISubstation } from 'app/shared/model//substation.model';

export interface ITconnection {
    id?: number;
    tConnSubStationName?: string;
    substation1?: ISubstation;
    substation2?: ISubstation;
}

export class Tconnection implements ITconnection {
    constructor(
        public id?: number,
        public tConnSubStationName?: string,
        public substation1?: ISubstation,
        public substation2?: ISubstation
    ) {}
}
