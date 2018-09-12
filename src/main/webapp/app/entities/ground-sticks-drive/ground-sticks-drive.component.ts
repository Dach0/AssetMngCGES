import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';
import { Principal } from 'app/core';
import { GroundSticksDriveService } from './ground-sticks-drive.service';

@Component({
    selector: 'jhi-ground-sticks-drive',
    templateUrl: './ground-sticks-drive.component.html'
})
export class GroundSticksDriveComponent implements OnInit, OnDestroy {
    groundSticksDrives: IGroundSticksDrive[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private groundSticksDriveService: GroundSticksDriveService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.groundSticksDriveService.query().subscribe(
            (res: HttpResponse<IGroundSticksDrive[]>) => {
                this.groundSticksDrives = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGroundSticksDrives();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGroundSticksDrive) {
        return item.id;
    }

    registerChangeInGroundSticksDrives() {
        this.eventSubscriber = this.eventManager.subscribe('groundSticksDriveListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
