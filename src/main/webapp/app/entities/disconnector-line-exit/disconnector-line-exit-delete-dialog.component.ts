import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';
import { DisconnectorLineExitService } from './disconnector-line-exit.service';

@Component({
    selector: 'jhi-disconnector-line-exit-delete-dialog',
    templateUrl: './disconnector-line-exit-delete-dialog.component.html'
})
export class DisconnectorLineExitDeleteDialogComponent {
    disconnectorLineExit: IDisconnectorLineExit;

    constructor(
        private disconnectorLineExitService: DisconnectorLineExitService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.disconnectorLineExitService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'disconnectorLineExitListModification',
                content: 'Deleted an disconnectorLineExit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-disconnector-line-exit-delete-popup',
    template: ''
})
export class DisconnectorLineExitDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ disconnectorLineExit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DisconnectorLineExitDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.disconnectorLineExit = disconnectorLineExit;
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
