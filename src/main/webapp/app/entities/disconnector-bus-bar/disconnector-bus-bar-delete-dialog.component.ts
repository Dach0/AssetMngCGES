import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';
import { DisconnectorBusBarService } from './disconnector-bus-bar.service';

@Component({
    selector: 'jhi-disconnector-bus-bar-delete-dialog',
    templateUrl: './disconnector-bus-bar-delete-dialog.component.html'
})
export class DisconnectorBusBarDeleteDialogComponent {
    disconnectorBusBar: IDisconnectorBusBar;

    constructor(
        private disconnectorBusBarService: DisconnectorBusBarService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.disconnectorBusBarService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'disconnectorBusBarListModification',
                content: 'Deleted an disconnectorBusBar'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-disconnector-bus-bar-delete-popup',
    template: ''
})
export class DisconnectorBusBarDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ disconnectorBusBar }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DisconnectorBusBarDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.disconnectorBusBar = disconnectorBusBar;
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
