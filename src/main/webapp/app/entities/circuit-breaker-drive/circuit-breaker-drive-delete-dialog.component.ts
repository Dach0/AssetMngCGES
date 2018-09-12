import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';
import { CircuitBreakerDriveService } from './circuit-breaker-drive.service';

@Component({
    selector: 'jhi-circuit-breaker-drive-delete-dialog',
    templateUrl: './circuit-breaker-drive-delete-dialog.component.html'
})
export class CircuitBreakerDriveDeleteDialogComponent {
    circuitBreakerDrive: ICircuitBreakerDrive;

    constructor(
        private circuitBreakerDriveService: CircuitBreakerDriveService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.circuitBreakerDriveService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'circuitBreakerDriveListModification',
                content: 'Deleted an circuitBreakerDrive'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-circuit-breaker-drive-delete-popup',
    template: ''
})
export class CircuitBreakerDriveDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ circuitBreakerDrive }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CircuitBreakerDriveDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.circuitBreakerDrive = circuitBreakerDrive;
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
