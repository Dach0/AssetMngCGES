import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITconnection } from 'app/shared/model/tconnection.model';
import { TconnectionService } from './tconnection.service';

@Component({
    selector: 'jhi-tconnection-delete-dialog',
    templateUrl: './tconnection-delete-dialog.component.html'
})
export class TconnectionDeleteDialogComponent {
    tconnection: ITconnection;

    constructor(
        private tconnectionService: TconnectionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tconnectionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tconnectionListModification',
                content: 'Deleted an tconnection'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tconnection-delete-popup',
    template: ''
})
export class TconnectionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tconnection }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TconnectionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tconnection = tconnection;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
