import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISurgeArrester } from 'app/shared/model/surge-arrester.model';
import { SurgeArresterService } from './surge-arrester.service';

@Component({
    selector: 'jhi-surge-arrester-delete-dialog',
    templateUrl: './surge-arrester-delete-dialog.component.html'
})
export class SurgeArresterDeleteDialogComponent {
    surgeArrester: ISurgeArrester;

    constructor(
        private surgeArresterService: SurgeArresterService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.surgeArresterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'surgeArresterListModification',
                content: 'Deleted an surgeArrester'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-surge-arrester-delete-popup',
    template: ''
})
export class SurgeArresterDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ surgeArrester }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SurgeArresterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.surgeArrester = surgeArrester;
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
