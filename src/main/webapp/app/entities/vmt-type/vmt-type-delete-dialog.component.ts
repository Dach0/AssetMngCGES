import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVmtType } from 'app/shared/model/vmt-type.model';
import { VmtTypeService } from './vmt-type.service';

@Component({
    selector: 'jhi-vmt-type-delete-dialog',
    templateUrl: './vmt-type-delete-dialog.component.html'
})
export class VmtTypeDeleteDialogComponent {
    vmtType: IVmtType;

    constructor(private vmtTypeService: VmtTypeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vmtTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vmtTypeListModification',
                content: 'Deleted an vmtType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vmt-type-delete-popup',
    template: ''
})
export class VmtTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vmtType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VmtTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.vmtType = vmtType;
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
