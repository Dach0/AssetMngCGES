import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICorrectionFactor } from 'app/shared/model/correction-factor.model';
import { CorrectionFactorService } from './correction-factor.service';

@Component({
    selector: 'jhi-correction-factor-delete-dialog',
    templateUrl: './correction-factor-delete-dialog.component.html'
})
export class CorrectionFactorDeleteDialogComponent {
    correctionFactor: ICorrectionFactor;

    constructor(
        private correctionFactorService: CorrectionFactorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.correctionFactorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'correctionFactorListModification',
                content: 'Deleted an correctionFactor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-correction-factor-delete-popup',
    template: ''
})
export class CorrectionFactorDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ correctionFactor }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CorrectionFactorDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.correctionFactor = correctionFactor;
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
