import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPylonType } from 'app/shared/model/pylon-type.model';
import { PylonTypeService } from './pylon-type.service';

@Component({
    selector: 'jhi-pylon-type-delete-dialog',
    templateUrl: './pylon-type-delete-dialog.component.html'
})
export class PylonTypeDeleteDialogComponent {
    pylonType: IPylonType;

    constructor(private pylonTypeService: PylonTypeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pylonTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pylonTypeListModification',
                content: 'Deleted an pylonType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pylon-type-delete-popup',
    template: ''
})
export class PylonTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pylonType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PylonTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.pylonType = pylonType;
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
