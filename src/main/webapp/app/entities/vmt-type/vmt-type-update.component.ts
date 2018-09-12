import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IVmtType } from 'app/shared/model/vmt-type.model';
import { VmtTypeService } from './vmt-type.service';

@Component({
    selector: 'jhi-vmt-type-update',
    templateUrl: './vmt-type-update.component.html'
})
export class VmtTypeUpdateComponent implements OnInit {
    private _vmtType: IVmtType;
    isSaving: boolean;

    constructor(private vmtTypeService: VmtTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vmtType }) => {
            this.vmtType = vmtType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.vmtType.id !== undefined) {
            this.subscribeToSaveResponse(this.vmtTypeService.update(this.vmtType));
        } else {
            this.subscribeToSaveResponse(this.vmtTypeService.create(this.vmtType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVmtType>>) {
        result.subscribe((res: HttpResponse<IVmtType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get vmtType() {
        return this._vmtType;
    }

    set vmtType(vmtType: IVmtType) {
        this._vmtType = vmtType;
    }
}
