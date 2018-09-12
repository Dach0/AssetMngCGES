import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISurgeArresterType } from 'app/shared/model/surge-arrester-type.model';
import { SurgeArresterTypeService } from './surge-arrester-type.service';

@Component({
    selector: 'jhi-surge-arrester-type-update',
    templateUrl: './surge-arrester-type-update.component.html'
})
export class SurgeArresterTypeUpdateComponent implements OnInit {
    private _surgeArresterType: ISurgeArresterType;
    isSaving: boolean;

    constructor(private surgeArresterTypeService: SurgeArresterTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ surgeArresterType }) => {
            this.surgeArresterType = surgeArresterType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.surgeArresterType.id !== undefined) {
            this.subscribeToSaveResponse(this.surgeArresterTypeService.update(this.surgeArresterType));
        } else {
            this.subscribeToSaveResponse(this.surgeArresterTypeService.create(this.surgeArresterType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISurgeArresterType>>) {
        result.subscribe((res: HttpResponse<ISurgeArresterType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get surgeArresterType() {
        return this._surgeArresterType;
    }

    set surgeArresterType(surgeArresterType: ISurgeArresterType) {
        this._surgeArresterType = surgeArresterType;
    }
}
