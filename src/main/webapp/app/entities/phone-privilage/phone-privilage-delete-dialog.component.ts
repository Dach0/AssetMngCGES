import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPhonePrivilage } from 'app/shared/model/phone-privilage.model';
import { PhonePrivilageService } from './phone-privilage.service';

@Component({
    selector: 'jhi-phone-privilage-delete-dialog',
    templateUrl: './phone-privilage-delete-dialog.component.html'
})
export class PhonePrivilageDeleteDialogComponent {
    phonePrivilage: IPhonePrivilage;

    constructor(
        private phonePrivilageService: PhonePrivilageService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.phonePrivilageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'phonePrivilageListModification',
                content: 'Deleted an phonePrivilage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-phone-privilage-delete-popup',
    template: ''
})
export class PhonePrivilageDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ phonePrivilage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PhonePrivilageDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.phonePrivilage = phonePrivilage;
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
