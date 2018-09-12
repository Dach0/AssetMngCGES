import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVoltageLevel } from 'app/shared/model/voltage-level.model';
import { VoltageLevelService } from './voltage-level.service';

@Component({
    selector: 'jhi-voltage-level-delete-dialog',
    templateUrl: './voltage-level-delete-dialog.component.html'
})
export class VoltageLevelDeleteDialogComponent {
    voltageLevel: IVoltageLevel;

    constructor(
        private voltageLevelService: VoltageLevelService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.voltageLevelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'voltageLevelListModification',
                content: 'Deleted an voltageLevel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-voltage-level-delete-popup',
    template: ''
})
export class VoltageLevelDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ voltageLevel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VoltageLevelDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.voltageLevel = voltageLevel;
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
