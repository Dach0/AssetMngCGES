import { ICircuitBreakerDrive } from 'app/shared/model//circuit-breaker-drive.model';
import { ICircuitBreakerType } from 'app/shared/model//circuit-breaker-type.model';
import { IManufacturer } from 'app/shared/model//manufacturer.model';
import { ISubstation } from 'app/shared/model//substation.model';
import { IField } from 'app/shared/model//field.model';

export interface ICircuitBreaker {
    id?: number;
    productionYear?: number;
    current?: number;
    shortCircuitCurrent?: number;
    serial?: string;
    picture?: string;
    drive?: ICircuitBreakerDrive;
    type?: ICircuitBreakerType;
    manufactured?: IManufacturer;
    substation?: ISubstation;
    field?: IField;
}

export class CircuitBreaker implements ICircuitBreaker {
    constructor(
        public id?: number,
        public productionYear?: number,
        public current?: number,
        public shortCircuitCurrent?: number,
        public serial?: string,
        public picture?: string,
        public drive?: ICircuitBreakerDrive,
        public type?: ICircuitBreakerType,
        public manufactured?: IManufacturer,
        public substation?: ISubstation,
        public field?: IField
    ) {}
}
