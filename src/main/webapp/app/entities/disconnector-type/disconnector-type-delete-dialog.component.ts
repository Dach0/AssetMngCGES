import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDisconnectorType } from 'app/shared/model/disconnector-type.model';
import { DisconnectorTypeService } from './disconnector-type.service';

@Component({
    selector: 'jhi-disconnector-type-delete-dialog',
    templateUrl: './disconnector-type-delete-dialog.component.html'
})
export class DisconnectorTypeDeleteDialogComponent {
    disconnectorType: IDisconnectorType;

    constructor(
        private disconnectorTypeService: DisconnectorTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.disconnectorTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'disconnectorTypeListModification',
                content: 'Deleted an disconnectorType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-disconnector-type-delete-popup',
    template: ''
})
export class DisconnectorTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ disconnectorType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DisconnectorTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.disconnectorType = disconnectorType;
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
