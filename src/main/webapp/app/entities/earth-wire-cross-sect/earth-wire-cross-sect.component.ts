import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';
import { Principal } from 'app/core';
import { EarthWireCrossSectService } from './earth-wire-cross-sect.service';

@Component({
    selector: 'jhi-earth-wire-cross-sect',
    templateUrl: './earth-wire-cross-sect.component.html'
})
export class EarthWireCrossSectComponent implements OnInit, OnDestroy {
    earthWireCrossSects: IEarthWireCrossSect[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private earthWireCrossSectService: EarthWireCrossSectService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.earthWireCrossSectService.query().subscribe(
            (res: HttpResponse<IEarthWireCrossSect[]>) => {
                this.earthWireCrossSects = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEarthWireCrossSects();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEarthWireCrossSect) {
        return item.id;
    }

    registerChangeInEarthWireCrossSects() {
        this.eventSubscriber = this.eventManager.subscribe('earthWireCrossSectListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
