import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmployeeGroup } from 'app/shared/model/employee-group.model';
import { EmployeeGroupService } from './employee-group.service';

@Component({
    selector: 'jhi-employee-group-delete-dialog',
    templateUrl: './employee-group-delete-dialog.component.html'
})
export class EmployeeGroupDeleteDialogComponent {
    employeeGroup: IEmployeeGroup;

    constructor(
        private employeeGroupService: EmployeeGroupService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.employeeGroupService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'employeeGroupListModification',
                content: 'Deleted an employeeGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-employee-group-delete-popup',
    template: ''
})
export class EmployeeGroupDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employeeGroup }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EmployeeGroupDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.employeeGroup = employeeGroup;
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
