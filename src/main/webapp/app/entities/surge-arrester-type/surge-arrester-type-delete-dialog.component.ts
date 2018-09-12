import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISurgeArresterType } from 'app/shared/model/surge-arrester-type.model';
import { SurgeArresterTypeService } from './surge-arrester-type.service';

@Component({
    selector: 'jhi-surge-arrester-type-delete-dialog',
    templateUrl: './surge-arrester-type-delete-dialog.component.html'
})
export class SurgeArresterTypeDeleteDialogComponent {
    surgeArresterType: ISurgeArresterType;

    constructor(
        private surgeArresterTypeService: SurgeArresterTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.surgeArresterTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'surgeArresterTypeListModification',
                content: 'Deleted an surgeArresterType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-surge-arrester-type-delete-popup',
    template: ''
})
export class SurgeArresterTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ surgeArresterType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SurgeArresterTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.surgeArresterType = surgeArresterType;
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
