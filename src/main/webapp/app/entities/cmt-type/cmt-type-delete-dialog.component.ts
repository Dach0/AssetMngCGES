import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICmtType } from 'app/shared/model/cmt-type.model';
import { CmtTypeService } from './cmt-type.service';

@Component({
    selector: 'jhi-cmt-type-delete-dialog',
    templateUrl: './cmt-type-delete-dialog.component.html'
})
export class CmtTypeDeleteDialogComponent {
    cmtType: ICmtType;

    constructor(private cmtTypeService: CmtTypeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cmtTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cmtTypeListModification',
                content: 'Deleted an cmtType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cmt-type-delete-popup',
    template: ''
})
export class CmtTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cmtType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CmtTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cmtType = cmtType;
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
