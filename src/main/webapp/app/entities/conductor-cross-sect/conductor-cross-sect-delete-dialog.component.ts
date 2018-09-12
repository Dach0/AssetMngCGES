import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';
import { ConductorCrossSectService } from './conductor-cross-sect.service';

@Component({
    selector: 'jhi-conductor-cross-sect-delete-dialog',
    templateUrl: './conductor-cross-sect-delete-dialog.component.html'
})
export class ConductorCrossSectDeleteDialogComponent {
    conductorCrossSect: IConductorCrossSect;

    constructor(
        private conductorCrossSectService: ConductorCrossSectService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conductorCrossSectService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'conductorCrossSectListModification',
                content: 'Deleted an conductorCrossSect'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conductor-cross-sect-delete-popup',
    template: ''
})
export class ConductorCrossSectDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conductorCrossSect }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConductorCrossSectDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.conductorCrossSect = conductorCrossSect;
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
