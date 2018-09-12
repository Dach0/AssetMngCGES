import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITransformatorNumber } from 'app/shared/model/transformator-number.model';
import { TransformatorNumberService } from './transformator-number.service';

@Component({
    selector: 'jhi-transformator-number-update',
    templateUrl: './transformator-number-update.component.html'
})
export class TransformatorNumberUpdateComponent implements OnInit {
    private _transformatorNumber: ITransformatorNumber;
    isSaving: boolean;

    constructor(private transformatorNumberService: TransformatorNumberService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transformatorNumber }) => {
            this.transformatorNumber = transformatorNumber;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.transformatorNumber.id !== undefined) {
            this.subscribeToSaveResponse(this.transformatorNumberService.update(this.transformatorNumber));
        } else {
            this.subscribeToSaveResponse(this.transformatorNumberService.create(this.transformatorNumber));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITransformatorNumber>>) {
        result.subscribe((res: HttpResponse<ITransformatorNumber>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get transformatorNumber() {
        return this._transformatorNumber;
    }

    set transformatorNumber(transformatorNumber: ITransformatorNumber) {
        this._transformatorNumber = transformatorNumber;
    }
}
