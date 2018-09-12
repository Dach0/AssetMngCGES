import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IElementStatus } from 'app/shared/model/element-status.model';
import { ElementStatusService } from './element-status.service';

@Component({
    selector: 'jhi-element-status-update',
    templateUrl: './element-status-update.component.html'
})
export class ElementStatusUpdateComponent implements OnInit {
    private _elementStatus: IElementStatus;
    isSaving: boolean;

    constructor(private elementStatusService: ElementStatusService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ elementStatus }) => {
            this.elementStatus = elementStatus;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.elementStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.elementStatusService.update(this.elementStatus));
        } else {
            this.subscribeToSaveResponse(this.elementStatusService.create(this.elementStatus));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IElementStatus>>) {
        result.subscribe((res: HttpResponse<IElementStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get elementStatus() {
        return this._elementStatus;
    }

    set elementStatus(elementStatus: IElementStatus) {
        this._elementStatus = elementStatus;
    }
}
