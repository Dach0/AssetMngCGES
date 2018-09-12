import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElementStatus } from 'app/shared/model/element-status.model';
import { ElementStatusService } from './element-status.service';

@Component({
    selector: 'jhi-element-status-delete-dialog',
    templateUrl: './element-status-delete-dialog.component.html'
})
export class ElementStatusDeleteDialogComponent {
    elementStatus: IElementStatus;

    constructor(
        private elementStatusService: ElementStatusService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.elementStatusService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'elementStatusListModification',
                content: 'Deleted an elementStatus'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-element-status-delete-popup',
    template: ''
})
export class ElementStatusDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ elementStatus }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ElementStatusDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.elementStatus = elementStatus;
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
