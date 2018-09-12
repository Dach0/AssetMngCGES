import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransformatorNumber } from 'app/shared/model/transformator-number.model';
import { TransformatorNumberService } from './transformator-number.service';

@Component({
    selector: 'jhi-transformator-number-delete-dialog',
    templateUrl: './transformator-number-delete-dialog.component.html'
})
export class TransformatorNumberDeleteDialogComponent {
    transformatorNumber: ITransformatorNumber;

    constructor(
        private transformatorNumberService: TransformatorNumberService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transformatorNumberService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'transformatorNumberListModification',
                content: 'Deleted an transformatorNumber'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transformator-number-delete-popup',
    template: ''
})
export class TransformatorNumberDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transformatorNumber }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TransformatorNumberDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.transformatorNumber = transformatorNumber;
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
