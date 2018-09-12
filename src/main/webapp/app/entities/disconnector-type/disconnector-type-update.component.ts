import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDisconnectorType } from 'app/shared/model/disconnector-type.model';
import { DisconnectorTypeService } from './disconnector-type.service';

@Component({
    selector: 'jhi-disconnector-type-update',
    templateUrl: './disconnector-type-update.component.html'
})
export class DisconnectorTypeUpdateComponent implements OnInit {
    private _disconnectorType: IDisconnectorType;
    isSaving: boolean;

    constructor(private disconnectorTypeService: DisconnectorTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ disconnectorType }) => {
            this.disconnectorType = disconnectorType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.disconnectorType.id !== undefined) {
            this.subscribeToSaveResponse(this.disconnectorTypeService.update(this.disconnectorType));
        } else {
            this.subscribeToSaveResponse(this.disconnectorTypeService.create(this.disconnectorType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDisconnectorType>>) {
        result.subscribe((res: HttpResponse<IDisconnectorType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get disconnectorType() {
        return this._disconnectorType;
    }

    set disconnectorType(disconnectorType: IDisconnectorType) {
        this._disconnectorType = disconnectorType;
    }
}
