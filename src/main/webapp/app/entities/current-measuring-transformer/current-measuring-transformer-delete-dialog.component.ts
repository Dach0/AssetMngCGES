import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';
import { CurrentMeasuringTransformerService } from './current-measuring-transformer.service';

@Component({
    selector: 'jhi-current-measuring-transformer-delete-dialog',
    templateUrl: './current-measuring-transformer-delete-dialog.component.html'
})
export class CurrentMeasuringTransformerDeleteDialogComponent {
    currentMeasuringTransformer: ICurrentMeasuringTransformer;

    constructor(
        private currentMeasuringTransformerService: CurrentMeasuringTransformerService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.currentMeasuringTransformerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'currentMeasuringTransformerListModification',
                content: 'Deleted an currentMeasuringTransformer'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-current-measuring-transformer-delete-popup',
    template: ''
})
export class CurrentMeasuringTransformerDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ currentMeasuringTransformer }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CurrentMeasuringTransformerDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.currentMeasuringTransformer = currentMeasuringTransformer;
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
