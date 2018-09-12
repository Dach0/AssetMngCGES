import { IVmtType } from 'app/shared/model//vmt-type.model';
import { IManufacturer } from 'app/shared/model//manufacturer.model';
import { ISubstation } from 'app/shared/model//substation.model';
import { IField } from 'app/shared/model//field.model';

export interface IVoltageMeasuringTransformer {
    id?: number;
    productionYear?: number;
    transmissionRatio?: string;
    accuracyClass?: number;
    nominalPower?: number;
    serial?: string;
    picture?: string;
    type?: IVmtType;
    manufactured?: IManufacturer;
    substation?: ISubstation;
    field?: IField;
}

export class VoltageMeasuringTransformer implements IVoltageMeasuringTransformer {
    constructor(
        public id?: number,
        public productionYear?: number,
        public transmissionRatio?: string,
        public accuracyClass?: number,
        public nominalPower?: number,
        public serial?: string,
        public picture?: string,
        public type?: IVmtType,
        public manufactured?: IManufacturer,
        public substation?: ISubstation,
        public field?: IField
    ) {}
}
