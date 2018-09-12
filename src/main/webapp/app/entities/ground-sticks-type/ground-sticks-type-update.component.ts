import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IGroundSticksType } from 'app/shared/model/ground-sticks-type.model';
import { GroundSticksTypeService } from './ground-sticks-type.service';

@Component({
    selector: 'jhi-ground-sticks-type-update',
    templateUrl: './ground-sticks-type-update.component.html'
})
export class GroundSticksTypeUpdateComponent implements OnInit {
    private _groundSticksType: IGroundSticksType;
    isSaving: boolean;

    constructor(private groundSticksTypeService: GroundSticksTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ groundSticksType }) => {
            this.groundSticksType = groundSticksType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.groundSticksType.id !== undefined) {
            this.subscribeToSaveResponse(this.groundSticksTypeService.update(this.groundSticksType));
        } else {
            this.subscribeToSaveResponse(this.groundSticksTypeService.create(this.groundSticksType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGroundSticksType>>) {
        result.subscribe((res: HttpResponse<IGroundSticksType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get groundSticksType() {
        return this._groundSticksType;
    }

    set groundSticksType(groundSticksType: IGroundSticksType) {
        this._groundSticksType = groundSticksType;
    }
}
