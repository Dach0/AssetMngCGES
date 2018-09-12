import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDisconnectorDrive } from 'app/shared/model/disconnector-drive.model';
import { DisconnectorDriveService } from './disconnector-drive.service';

@Component({
    selector: 'jhi-disconnector-drive-delete-dialog',
    templateUrl: './disconnector-drive-delete-dialog.component.html'
})
export class DisconnectorDriveDeleteDialogComponent {
    disconnectorDrive: IDisconnectorDrive;

    constructor(
        private disconnectorDriveService: DisconnectorDriveService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.disconnectorDriveService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'disconnectorDriveListModification',
                content: 'Deleted an disconnectorDrive'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-disconnector-drive-delete-popup',
    template: ''
})
export class DisconnectorDriveDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ disconnectorDrive }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DisconnectorDriveDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.disconnectorDrive = disconnectorDrive;
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
