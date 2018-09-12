import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransmissionRatio } from 'app/shared/model/transmission-ratio.model';
import { TransmissionRatioService } from './transmission-ratio.service';

@Component({
    selector: 'jhi-transmission-ratio-delete-dialog',
    templateUrl: './transmission-ratio-delete-dialog.component.html'
})
export class TransmissionRatioDeleteDialogComponent {
    transmissionRatio: ITransmissionRatio;

    constructor(
        private transmissionRatioService: TransmissionRatioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transmissionRatioService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'transmissionRatioListModification',
                content: 'Deleted an transmissionRatio'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transmission-ratio-delete-popup',
    template: ''
})
export class TransmissionRatioDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transmissionRatio }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TransmissionRatioDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.transmissionRatio = transmissionRatio;
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
