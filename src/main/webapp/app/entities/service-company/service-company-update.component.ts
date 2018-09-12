import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IServiceCompany } from 'app/shared/model/service-company.model';
import { ServiceCompanyService } from './service-company.service';

@Component({
    selector: 'jhi-service-company-update',
    templateUrl: './service-company-update.component.html'
})
export class ServiceCompanyUpdateComponent implements OnInit {
    private _serviceCompany: IServiceCompany;
    isSaving: boolean;

    constructor(private serviceCompanyService: ServiceCompanyService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ serviceCompany }) => {
            this.serviceCompany = serviceCompany;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.serviceCompany.id !== undefined) {
            this.subscribeToSaveResponse(this.serviceCompanyService.update(this.serviceCompany));
        } else {
            this.subscribeToSaveResponse(this.serviceCompanyService.create(this.serviceCompany));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IServiceCompany>>) {
        result.subscribe((res: HttpResponse<IServiceCompany>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get serviceCompany() {
        return this._serviceCompany;
    }

    set serviceCompany(serviceCompany: IServiceCompany) {
        this._serviceCompany = serviceCompany;
    }
}
