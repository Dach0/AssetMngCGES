import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IServiceType } from 'app/shared/model/service-type.model';
import { ServiceTypeService } from './service-type.service';

@Component({
    selector: 'jhi-service-type-update',
    templateUrl: './service-type-update.component.html'
})
export class ServiceTypeUpdateComponent implements OnInit {
    private _serviceType: IServiceType;
    isSaving: boolean;

    constructor(private serviceTypeService: ServiceTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ serviceType }) => {
            this.serviceType = serviceType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.serviceType.id !== undefined) {
            this.subscribeToSaveResponse(this.serviceTypeService.update(this.serviceType));
        } else {
            this.subscribeToSaveResponse(this.serviceTypeService.create(this.serviceType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IServiceType>>) {
        result.subscribe((res: HttpResponse<IServiceType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get serviceType() {
        return this._serviceType;
    }

    set serviceType(serviceType: IServiceType) {
        this._serviceType = serviceType;
    }
}
