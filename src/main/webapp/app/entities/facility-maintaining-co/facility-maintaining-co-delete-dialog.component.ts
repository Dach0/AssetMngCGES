import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';
import { FacilityMaintainingCoService } from './facility-maintaining-co.service';

@Component({
    selector: 'jhi-facility-maintaining-co-delete-dialog',
    templateUrl: './facility-maintaining-co-delete-dialog.component.html'
})
export class FacilityMaintainingCoDeleteDialogComponent {
    facilityMaintainingCo: IFacilityMaintainingCo;

    constructor(
        private facilityMaintainingCoService: FacilityMaintainingCoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.facilityMaintainingCoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'facilityMaintainingCoListModification',
                content: 'Deleted an facilityMaintainingCo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-facility-maintaining-co-delete-popup',
    template: ''
})
export class FacilityMaintainingCoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ facilityMaintainingCo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FacilityMaintainingCoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.facilityMaintainingCo = facilityMaintainingCo;
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
