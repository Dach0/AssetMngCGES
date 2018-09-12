import { ISurgeArresterType } from 'app/shared/model//surge-arrester-type.model';
import { IManufacturer } from 'app/shared/model//manufacturer.model';
import { ISubstation } from 'app/shared/model//substation.model';
import { IField } from 'app/shared/model//field.model';

export interface ISurgeArrester {
    id?: number;
    productionYear?: number;
    ucur?: string;
    drainageCurrent?: number;
    serial?: string;
    picture?: string;
    type?: ISurgeArresterType;
    manufactured?: IManufacturer;
    substation?: ISubstation;
    field?: IField;
}

export class SurgeArrester implements ISurgeArrester {
    constructor(
        public id?: number,
        public productionYear?: number,
        public ucur?: string,
        public drainageCurrent?: number,
        public serial?: string,
        public picture?: string,
        public type?: ISurgeArresterType,
        public manufactured?: IManufacturer,
        public substation?: ISubstation,
        public field?: IField
    ) {}
}
