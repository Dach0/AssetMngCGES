import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAssetCondition } from 'app/shared/model/asset-condition.model';
import { AssetConditionService } from './asset-condition.service';

@Component({
    selector: 'jhi-asset-condition-delete-dialog',
    templateUrl: './asset-condition-delete-dialog.component.html'
})
export class AssetConditionDeleteDialogComponent {
    assetCondition: IAssetCondition;

    constructor(
        private assetConditionService: AssetConditionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.assetConditionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'assetConditionListModification',
                content: 'Deleted an assetCondition'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-asset-condition-delete-popup',
    template: ''
})
export class AssetConditionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ assetCondition }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AssetConditionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.assetCondition = assetCondition;
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
