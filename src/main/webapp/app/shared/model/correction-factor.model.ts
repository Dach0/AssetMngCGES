export interface ICorrectionFactor {
    id?: number;
    templateName?: string;
    degrees30?: number;
    degrees20?: number;
    degrees10?: number;
    degrees0?: number;
}

export class CorrectionFactor implements ICorrectionFactor {
    constructor(
        public id?: number,
        public templateName?: string,
        public degrees30?: number,
        public degrees20?: number,
        public degrees10?: number,
        public degrees0?: number
    ) {}
}
