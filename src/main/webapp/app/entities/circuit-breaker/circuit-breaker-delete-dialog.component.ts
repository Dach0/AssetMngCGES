import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICircuitBreaker } from 'app/shared/model/circuit-breaker.model';
import { CircuitBreakerService } from './circuit-breaker.service';

@Component({
    selector: 'jhi-circuit-breaker-delete-dialog',
    templateUrl: './circuit-breaker-delete-dialog.component.html'
})
export class CircuitBreakerDeleteDialogComponent {
    circuitBreaker: ICircuitBreaker;

    constructor(
        private circuitBreakerService: CircuitBreakerService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.circuitBreakerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'circuitBreakerListModification',
                content: 'Deleted an circuitBreaker'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-circuit-breaker-delete-popup',
    template: ''
})
export class CircuitBreakerDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ circuitBreaker }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CircuitBreakerDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.circuitBreaker = circuitBreaker;
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
