import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';
import { CircuitBreakerTypeService } from './circuit-breaker-type.service';

@Component({
    selector: 'jhi-circuit-breaker-type-delete-dialog',
    templateUrl: './circuit-breaker-type-delete-dialog.component.html'
})
export class CircuitBreakerTypeDeleteDialogComponent {
    circuitBreakerType: ICircuitBreakerType;

    constructor(
        private circuitBreakerTypeService: CircuitBreakerTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.circuitBreakerTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'circuitBreakerTypeListModification',
                content: 'Deleted an circuitBreakerType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-circuit-breaker-type-delete-popup',
    template: ''
})
export class CircuitBreakerTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ circuitBreakerType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CircuitBreakerTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.circuitBreakerType = circuitBreakerType;
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
