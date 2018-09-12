import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITransmissionRatio } from 'app/shared/model/transmission-ratio.model';
import { TransmissionRatioService } from './transmission-ratio.service';

@Component({
    selector: 'jhi-transmission-ratio-update',
    templateUrl: './transmission-ratio-update.component.html'
})
export class TransmissionRatioUpdateComponent implements OnInit {
    private _transmissionRatio: ITransmissionRatio;
    isSaving: boolean;

    constructor(private transmissionRatioService: TransmissionRatioService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transmissionRatio }) => {
            this.transmissionRatio = transmissionRatio;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.transmissionRatio.id !== undefined) {
            this.subscribeToSaveResponse(this.transmissionRatioService.update(this.transmissionRatio));
        } else {
            this.subscribeToSaveResponse(this.transmissionRatioService.create(this.transmissionRatio));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITransmissionRatio>>) {
        result.subscribe((res: HttpResponse<ITransmissionRatio>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get transmissionRatio() {
        return this._transmissionRatio;
    }

    set transmissionRatio(transmissionRatio: ITransmissionRatio) {
        this._transmissionRatio = transmissionRatio;
    }
}
