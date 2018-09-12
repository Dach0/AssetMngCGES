import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';
import { VoltageMeasuringTransformerService } from './voltage-measuring-transformer.service';

@Component({
    selector: 'jhi-voltage-measuring-transformer-delete-dialog',
    templateUrl: './voltage-measuring-transformer-delete-dialog.component.html'
})
export class VoltageMeasuringTransformerDeleteDialogComponent {
    voltageMeasuringTransformer: IVoltageMeasuringTransformer;

    constructor(
        private voltageMeasuringTransformerService: VoltageMeasuringTransformerService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.voltageMeasuringTransformerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'voltageMeasuringTransformerListModification',
                content: 'Deleted an voltageMeasuringTransformer'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-voltage-measuring-transformer-delete-popup',
    template: ''
})
export class VoltageMeasuringTransformerDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ voltageMeasuringTransformer }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VoltageMeasuringTransformerDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.voltageMeasuringTransformer = voltageMeasuringTransformer;
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
