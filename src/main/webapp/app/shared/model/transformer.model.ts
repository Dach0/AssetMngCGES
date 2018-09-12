import { IElementCondition } from 'app/shared/model//element-condition.model';
import { IElementStatus } from 'app/shared/model//element-status.model';
import { ICoupling } from 'app/shared/model//coupling.model';
import { IPower } from 'app/shared/model//power.model';
import { ITransmissionRatio } from 'app/shared/model//transmission-ratio.model';
import { IManufacturer } from 'app/shared/model//manufacturer.model';
import { ITransformerType } from 'app/shared/model//transformer-type.model';
import { IService } from 'app/shared/model//service.model';
import { ITransformatorNumber } from 'app/shared/model//transformator-number.model';
import { ISubstation } from 'app/shared/model//substation.model';

export interface ITransformer {
    id?: number;
    productionYear?: number;
    installationYear?: number;
    shortCircuitVoltage?: number;
    isEarthGrounding?: boolean;
    picture?: string;
    serialNumber?: string;
    condition?: IElementCondition;
    status?: IElementStatus;
    coupling?: ICoupling;
    power?: IPower;
    transRatio?: ITransmissionRatio;
    manufacturer?: IManufacturer;
    type?: ITransformerType;
    service?: IService;
    redniBroj?: ITransformatorNumber;
    substation?: ISubstation;
}

export class Transformer implements ITransformer {
    constructor(
        public id?: number,
        public productionYear?: number,
        public installationYear?: number,
        public shortCircuitVoltage?: number,
        public isEarthGrounding?: boolean,
        public picture?: string,
        public serialNumber?: string,
        public condition?: IElementCondition,
        public status?: IElementStatus,
        public coupling?: ICoupling,
        public power?: IPower,
        public transRatio?: ITransmissionRatio,
        public manufacturer?: IManufacturer,
        public type?: ITransformerType,
        public service?: IService,
        public redniBroj?: ITransformatorNumber,
        public substation?: ISubstation
    ) {
        this.isEarthGrounding = false;
    }
}
