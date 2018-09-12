export interface IThermalLimit {
    id?: number;
    templateName?: string;
    iMaxSummer?: number;
    iMaxWinter?: number;
    pMaxSummer?: number;
    pMaxWinter?: number;
    thermalLimit?: number;
}

export class ThermalLimit implements IThermalLimit {
    constructor(
        public id?: number,
        public templateName?: string,
        public iMaxSummer?: number,
        public iMaxWinter?: number,
        public pMaxSummer?: number,
        public pMaxWinter?: number,
        public thermalLimit?: number
    ) {}
}
