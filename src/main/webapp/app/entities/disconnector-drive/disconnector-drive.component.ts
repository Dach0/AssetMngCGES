import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDisconnectorDrive } from 'app/shared/model/disconnector-drive.model';
import { Principal } from 'app/core';
import { DisconnectorDriveService } from './disconnector-drive.service';

@Component({
    selector: 'jhi-disconnector-drive',
    templateUrl: './disconnector-drive.component.html'
})
export class DisconnectorDriveComponent implements OnInit, OnDestroy {
    disconnectorDrives: IDisconnectorDrive[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private disconnectorDriveService: DisconnectorDriveService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.disconnectorDriveService.query().subscribe(
            (res: HttpResponse<IDisconnectorDrive[]>) => {
                this.disconnectorDrives = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDisconnectorDrives();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDisconnectorDrive) {
        return item.id;
    }

    registerChangeInDisconnectorDrives() {
        this.eventSubscriber = this.eventManager.subscribe('disconnectorDriveListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
