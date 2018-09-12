import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOhl } from 'app/shared/model/ohl.model';
import { OhlService } from './ohl.service';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';
import { ITconnection } from 'app/shared/model/tconnection.model';
import { TconnectionService } from 'app/entities/tconnection';
import { IVoltageLevel } from 'app/shared/model/voltage-level.model';
import { VoltageLevelService } from 'app/entities/voltage-level';
import { IPylonType } from 'app/shared/model/pylon-type.model';
import { PylonTypeService } from 'app/entities/pylon-type';
import { IThermalLimit } from 'app/shared/model/thermal-limit.model';
import { ThermalLimitService } from 'app/entities/thermal-limit';
import { IConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';
import { ConductorCrossSectService } from 'app/entities/conductor-cross-sect';
import { IEarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';
import { EarthWireCrossSectService } from 'app/entities/earth-wire-cross-sect';

@Component({
    selector: 'jhi-ohl-update',
    templateUrl: './ohl-update.component.html'
})
export class OhlUpdateComponent implements OnInit {
    private _ohl: IOhl;
    isSaving: boolean;

    substations: ISubstation[];

    tconnections: ITconnection[];

    voltagelevels: IVoltageLevel[];

    pylontypes: IPylonType[];

    thermallimits: IThermalLimit[];

    conductorcrosssects: IConductorCrossSect[];

    earthwirecrosssects: IEarthWireCrossSect[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private ohlService: OhlService,
        private substationService: SubstationService,
        private tconnectionService: TconnectionService,
        private voltageLevelService: VoltageLevelService,
        private pylonTypeService: PylonTypeService,
        private thermalLimitService: ThermalLimitService,
        private conductorCrossSectService: ConductorCrossSectService,
        private earthWireCrossSectService: EarthWireCrossSectService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ohl }) => {
            this.ohl = ohl;
        });
        this.substationService.query().subscribe(
            (res: HttpResponse<ISubstation[]>) => {
                this.substations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tconnectionService.query().subscribe(
            (res: HttpResponse<ITconnection[]>) => {
                this.tconnections = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.voltageLevelService.query().subscribe(
            (res: HttpResponse<IVoltageLevel[]>) => {
                this.voltagelevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pylonTypeService.query().subscribe(
            (res: HttpResponse<IPylonType[]>) => {
                this.pylontypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.thermalLimitService.query().subscribe(
            (res: HttpResponse<IThermalLimit[]>) => {
                this.thermallimits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.conductorCrossSectService.query().subscribe(
            (res: HttpResponse<IConductorCrossSect[]>) => {
                this.conductorcrosssects = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.earthWireCrossSectService.query().subscribe(
            (res: HttpResponse<IEarthWireCrossSect[]>) => {
                this.earthwirecrosssects = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.ohl.id !== undefined) {
            this.subscribeToSaveResponse(this.ohlService.update(this.ohl));
        } else {
            this.subscribeToSaveResponse(this.ohlService.create(this.ohl));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOhl>>) {
        result.subscribe((res: HttpResponse<IOhl>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSubstationById(index: number, item: ISubstation) {
        return item.id;
    }

    trackTconnectionById(index: number, item: ITconnection) {
        return item.id;
    }

    trackVoltageLevelById(index: number, item: IVoltageLevel) {
        return item.id;
    }

    trackPylonTypeById(index: number, item: IPylonType) {
        return item.id;
    }

    trackThermalLimitById(index: number, item: IThermalLimit) {
        return item.id;
    }

    trackConductorCrossSectById(index: number, item: IConductorCrossSect) {
        return item.id;
    }

    trackEarthWireCrossSectById(index: number, item: IEarthWireCrossSect) {
        return item.id;
    }
    get ohl() {
        return this._ohl;
    }

    set ohl(ohl: IOhl) {
        this._ohl = ohl;
    }
}
