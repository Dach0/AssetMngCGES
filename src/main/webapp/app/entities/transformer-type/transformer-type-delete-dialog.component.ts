import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransformerType } from 'app/shared/model/transformer-type.model';
import { TransformerTypeService } from './transformer-type.service';

@Component({
    selector: 'jhi-transformer-type-delete-dialog',
    templateUrl: './transformer-type-delete-dialog.component.html'
})
export class TransformerTypeDeleteDialogComponent {
    transformerType: ITransformerType;

    constructor(
        private transformerTypeService: TransformerTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transformerTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'transformerTypeListModification',
                content: 'Deleted an transformerType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transformer-type-delete-popup',
    template: ''
})
export class TransformerTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transformerType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TransformerTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.transformerType = transformerType;
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
