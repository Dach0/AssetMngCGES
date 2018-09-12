import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPylonType } from 'app/shared/model/pylon-type.model';
import { PylonTypeService } from './pylon-type.service';

@Component({
    selector: 'jhi-pylon-type-update',
    templateUrl: './pylon-type-update.component.html'
})
export class PylonTypeUpdateComponent implements OnInit {
    private _pylonType: IPylonType;
    isSaving: boolean;

    constructor(private pylonTypeService: PylonTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pylonType }) => {
            this.pylonType = pylonType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pylonType.id !== undefined) {
            this.subscribeToSaveResponse(this.pylonTypeService.update(this.pylonType));
        } else {
            this.subscribeToSaveResponse(this.pylonTypeService.create(this.pylonType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPylonType>>) {
        result.subscribe((res: HttpResponse<IPylonType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get pylonType() {
        return this._pylonType;
    }

    set pylonType(pylonType: IPylonType) {
        this._pylonType = pylonType;
    }
}
