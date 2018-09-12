import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';
import { GroundSticksDriveService } from './ground-sticks-drive.service';

@Component({
    selector: 'jhi-ground-sticks-drive-delete-dialog',
    templateUrl: './ground-sticks-drive-delete-dialog.component.html'
})
export class GroundSticksDriveDeleteDialogComponent {
    groundSticksDrive: IGroundSticksDrive;

    constructor(
        private groundSticksDriveService: GroundSticksDriveService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.groundSticksDriveService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'groundSticksDriveListModification',
                content: 'Deleted an groundSticksDrive'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ground-sticks-drive-delete-popup',
    template: ''
})
export class GroundSticksDriveDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ groundSticksDrive }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GroundSticksDriveDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.groundSticksDrive = groundSticksDrive;
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
