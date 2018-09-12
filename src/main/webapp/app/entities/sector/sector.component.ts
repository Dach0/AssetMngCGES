import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISector } from 'app/shared/model/sector.model';
import { Principal } from 'app/core';
import { SectorService } from './sector.service';

@Component({
    selector: 'jhi-sector',
    templateUrl: './sector.component.html'
})
export class SectorComponent implements OnInit, OnDestroy {
    sectors: ISector[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sectorService: SectorService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sectorService.query().subscribe(
            (res: HttpResponse<ISector[]>) => {
                this.sectors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSectors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISector) {
        return item.id;
    }

    registerChangeInSectors() {
        this.eventSubscriber = this.eventManager.subscribe('sectorListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
