import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IThermalLimit } from 'app/shared/model/thermal-limit.model';
import { ThermalLimitService } from './thermal-limit.service';

@Component({
    selector: 'jhi-thermal-limit-delete-dialog',
    templateUrl: './thermal-limit-delete-dialog.component.html'
})
export class ThermalLimitDeleteDialogComponent {
    thermalLimit: IThermalLimit;

    constructor(
        private thermalLimitService: ThermalLimitService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.thermalLimitService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'thermalLimitListModification',
                content: 'Deleted an thermalLimit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-thermal-limit-delete-popup',
    template: ''
})
export class ThermalLimitDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ thermalLimit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ThermalLimitDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.thermalLimit = thermalLimit;
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
