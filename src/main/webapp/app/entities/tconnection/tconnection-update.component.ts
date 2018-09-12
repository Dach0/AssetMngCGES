import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITconnection } from 'app/shared/model/tconnection.model';
import { TconnectionService } from './tconnection.service';
import { ISubstation } from 'app/shared/model/substation.model';
import { SubstationService } from 'app/entities/substation';

@Component({
    selector: 'jhi-tconnection-update',
    templateUrl: './tconnection-update.component.html'
})
export class TconnectionUpdateComponent implements OnInit {
    private _tconnection: ITconnection;
    isSaving: boolean;

    substations: ISubstation[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tconnectionService: TconnectionService,
        private substationService: SubstationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tconnection }) => {
            this.tconnection = tconnection;
        });
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
        if (this.tconnection.id !== undefined) {
            this.subscribeToSaveResponse(this.tconnectionService.update(this.tconnection));
        } else {
            this.subscribeToSaveResponse(this.tconnectionService.create(this.tconnection));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITconnection>>) {
        result.subscribe((res: HttpResponse<ITconnection>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get tconnection() {
        return this._tconnection;
    }

    set tconnection(tconnection: ITconnection) {
        this._tconnection = tconnection;
    }
}
