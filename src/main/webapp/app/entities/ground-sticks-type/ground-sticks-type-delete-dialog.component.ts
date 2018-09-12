import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroundSticksType } from 'app/shared/model/ground-sticks-type.model';
import { GroundSticksTypeService } from './ground-sticks-type.service';

@Component({
    selector: 'jhi-ground-sticks-type-delete-dialog',
    templateUrl: './ground-sticks-type-delete-dialog.component.html'
})
export class GroundSticksTypeDeleteDialogComponent {
    groundSticksType: IGroundSticksType;

    constructor(
        private groundSticksTypeService: GroundSticksTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.groundSticksTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'groundSticksTypeListModification',
                content: 'Deleted an groundSticksType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ground-sticks-type-delete-popup',
    template: ''
})
export class GroundSticksTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ groundSticksType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GroundSticksTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.groundSticksType = groundSticksType;
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
