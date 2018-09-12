import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICmtType } from 'app/shared/model/cmt-type.model';
import { CmtTypeService } from './cmt-type.service';

@Component({
    selector: 'jhi-cmt-type-update',
    templateUrl: './cmt-type-update.component.html'
})
export class CmtTypeUpdateComponent implements OnInit {
    private _cmtType: ICmtType;
    isSaving: boolean;

    constructor(private cmtTypeService: CmtTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cmtType }) => {
            this.cmtType = cmtType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cmtType.id !== undefined) {
            this.subscribeToSaveResponse(this.cmtTypeService.update(this.cmtType));
        } else {
            this.subscribeToSaveResponse(this.cmtTypeService.create(this.cmtType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICmtType>>) {
        result.subscribe((res: HttpResponse<ICmtType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get cmtType() {
        return this._cmtType;
    }

    set cmtType(cmtType: ICmtType) {
        this._cmtType = cmtType;
    }
}
