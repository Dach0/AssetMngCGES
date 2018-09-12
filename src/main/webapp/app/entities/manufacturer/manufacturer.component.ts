import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { Principal } from 'app/core';
import { ManufacturerService } from './manufacturer.service';

@Component({
    selector: 'jhi-manufacturer',
    templateUrl: './manufacturer.component.html'
})
export class ManufacturerComponent implements OnInit, OnDestroy {
    manufacturers: IManufacturer[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private manufacturerService: ManufacturerService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.manufacturerService.query().subscribe(
            (res: HttpResponse<IManufacturer[]>) => {
                this.manufacturers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInManufacturers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IManufacturer) {
        return item.id;
    }

    registerChangeInManufacturers() {
        this.eventSubscriber = this.eventManager.subscribe('manufacturerListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
