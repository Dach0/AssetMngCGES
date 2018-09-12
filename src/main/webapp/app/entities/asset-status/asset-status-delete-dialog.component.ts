import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAssetStatus } from 'app/shared/model/asset-status.model';
import { AssetStatusService } from './asset-status.service';

@Component({
    selector: 'jhi-asset-status-delete-dialog',
    templateUrl: './asset-status-delete-dialog.component.html'
})
export class AssetStatusDeleteDialogComponent {
    assetStatus: IAssetStatus;

    constructor(
        private assetStatusService: AssetStatusService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.assetStatusService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'assetStatusListModification',
                content: 'Deleted an assetStatus'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-asset-status-delete-popup',
    template: ''
})
export class AssetStatusDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ assetStatus }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AssetStatusDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.assetStatus = assetStatus;
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
