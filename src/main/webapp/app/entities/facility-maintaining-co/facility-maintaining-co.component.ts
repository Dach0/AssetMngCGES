import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';
import { Principal } from 'app/core';
import { FacilityMaintainingCoService } from './facility-maintaining-co.service';

@Component({
    selector: 'jhi-facility-maintaining-co',
    templateUrl: './facility-maintaining-co.component.html'
})
export class FacilityMaintainingCoComponent implements OnInit, OnDestroy {
    facilityMaintainingCos: IFacilityMaintainingCo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private facilityMaintainingCoService: FacilityMaintainingCoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.facilityMaintainingCoService.query().subscribe(
            (res: HttpResponse<IFacilityMaintainingCo[]>) => {
                this.facilityMaintainingCos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFacilityMaintainingCos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFacilityMaintainingCo) {
        return item.id;
    }

    registerChangeInFacilityMaintainingCos() {
        this.eventSubscriber = this.eventManager.subscribe('facilityMaintainingCoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
