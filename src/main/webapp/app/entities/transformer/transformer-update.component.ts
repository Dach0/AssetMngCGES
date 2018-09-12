import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITransformer } from 'app/shared/model/transformer.model';
import { TransformerService } from './transformer.service';
import { IElementCondition } from 'app/shared/model/element-condition.model';
import { ElementConditionService } from 'app/entities/element-condition';
import { IElementStatus } from 'app/shared/model/element-status.model';
import { ElementStatusService } from 'app/entities/element-status';
import { ICoupling } from 'app/shared/model/coupling.model';
import { CouplingService } from 'app/entities/coupling';
import { IPower } from 'app/shared/model/power.model';
import { PowerService } from 'app/entities/power';
import { ITransmissionRatio } from 'app/shared/model/transmission-ratio.model';
import { TransmissionRatioService } from 'app/entities/transmission-ratio';
import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from 'app/entities/manufacturer';
import { ITransformerType } from 'app/shared/model/transformer-type.model';
import { TransformerTypeService } from 'app/entities/transformer-type';
import { IService } from 'app/shared/model/service.model';
import { ServiceService } from 'app/entities/service';
import { ITransformatorNumber } from 'app/shared/model/transformator-number.model';
import { TransformatorNumberService } from 'app/entities/transformator-number';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';

@Component({
    selector: 'jhi-transformer-update',
    templateUrl: './transformer-update.component.html'
})
export class TransformerUpdateComponent implements OnInit {
    private _transformer: ITransformer;
    isSaving: boolean;

    elementconditions: IElementCondition[];

    elementstatuses: IElementStatus[];

    couplings: ICoupling[];

    powers: IPower[];

    transmissionratios: ITransmissionRatio[];

    manufacturers: IManufacturer[];

    transformertypes: ITransformerType[];

    services: IService[];

    transformatornumbers: ITransformatorNumber[];

    substations: ISubstation[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private transformerService: TransformerService,
        private elementConditionService: ElementConditionService,
        private elementStatusService: ElementStatusService,
        private couplingService: CouplingService,
        private powerService: PowerService,
        private transmissionRatioService: TransmissionRatioService,
        private manufacturerService: ManufacturerService,
        private transformerTypeService: TransformerTypeService,
        private serviceService: ServiceService,
        private transformatorNumberService: TransformatorNumberService,
        private substationService: SubstationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transformer }) => {
            this.transformer = transformer;
        });
        this.elementConditionService.query().subscribe(
            (res: HttpResponse<IElementCondition[]>) => {
                this.elementconditions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.elementStatusService.query().subscribe(
            (res: HttpResponse<IElementStatus[]>) => {
                this.elementstatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.couplingService.query().subscribe(
            (res: HttpResponse<ICoupling[]>) => {
                this.couplings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.powerService.query().subscribe(
            (res: HttpResponse<IPower[]>) => {
                this.powers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.transmissionRatioService.query().subscribe(
            (res: HttpResponse<ITransmissionRatio[]>) => {
                this.transmissionratios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.manufacturerService.query().subscribe(
            (res: HttpResponse<IManufacturer[]>) => {
                this.manufacturers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.transformerTypeService.query().subscribe(
            (res: HttpResponse<ITransformerType[]>) => {
                this.transformertypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.serviceService.query().subscribe(
            (res: HttpResponse<IService[]>) => {
                this.services = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.transformatorNumberService.query().subscribe(
            (res: HttpResponse<ITransformatorNumber[]>) => {
                this.transformatornumbers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.substationService.query().subscribe(
            (res: HttpResponse<ISubstation[]>) => {
                this.substations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.transformer.id !== undefined) {
            this.subscribeToSaveResponse(this.transformerService.update(this.transformer));
        } else {
            this.subscribeToSaveResponse(this.transformerService.create(this.transformer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITransformer>>) {
        result.subscribe((res: HttpResponse<ITransformer>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackElementConditionById(index: number, item: IElementCondition) {
        return item.id;
    }

    trackElementStatusById(index: number, item: IElementStatus) {
        return item.id;
    }

    trackCouplingById(index: number, item: ICoupling) {
        return item.id;
    }

    trackPowerById(index: number, item: IPower) {
        return item.id;
    }

    trackTransmissionRatioById(index: number, item: ITransmissionRatio) {
        return item.id;
    }

    trackManufacturerById(index: number, item: IManufacturer) {
        return item.id;
    }

    trackTransformerTypeById(index: number, item: ITransformerType) {
        return item.id;
    }

    trackServiceById(index: number, item: IService) {
        return item.id;
    }

    trackTransformatorNumberById(index: number, item: ITransformatorNumber) {
        return item.id;
    }

    trackSubstationById(index: number, item: ISubstation) {
        return item.id;
    }
    get transformer() {
        return this._transformer;
    }

    set transformer(transformer: ITransformer) {
        this._transformer = transformer;
    }
}
