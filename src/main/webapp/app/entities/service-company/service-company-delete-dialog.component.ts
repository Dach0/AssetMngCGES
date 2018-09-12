import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IServiceCompany } from 'app/shared/model/service-company.model';
import { ServiceCompanyService } from './service-company.service';

@Component({
    selector: 'jhi-service-company-delete-dialog',
    templateUrl: './service-company-delete-dialog.component.html'
})
export class ServiceCompanyDeleteDialogComponent {
    serviceCompany: IServiceCompany;

    constructor(
        private serviceCompanyService: ServiceCompanyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.serviceCompanyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'serviceCompanyListModification',
                content: 'Deleted an serviceCompany'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-service-company-delete-popup',
    template: ''
})
export class ServiceCompanyDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ serviceCompany }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ServiceCompanyDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.serviceCompany = serviceCompany;
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
