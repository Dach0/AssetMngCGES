import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IServiceCompany } from 'app/shared/model/service-company.model';
import { Principal } from 'app/core';
import { ServiceCompanyService } from './service-company.service';

@Component({
    selector: 'jhi-service-company',
    templateUrl: './service-company.component.html'
})
export class ServiceCompanyComponent implements OnInit, OnDestroy {
    serviceCompanies: IServiceCompany[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private serviceCompanyService: ServiceCompanyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.serviceCompanyService.query().subscribe(
            (res: HttpResponse<IServiceCompany[]>) => {
                this.serviceCompanies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInServiceCompanies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IServiceCompany) {
        return item.id;
    }

    registerChangeInServiceCompanies() {
        this.eventSubscriber = this.eventManager.subscribe('serviceCompanyListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
