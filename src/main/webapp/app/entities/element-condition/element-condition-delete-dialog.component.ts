import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElementCondition } from 'app/shared/model/element-condition.model';
import { ElementConditionService } from './element-condition.service';

@Component({
    selector: 'jhi-element-condition-delete-dialog',
    templateUrl: './element-condition-delete-dialog.component.html'
})
export class ElementConditionDeleteDialogComponent {
    elementCondition: IElementCondition;

    constructor(
        private elementConditionService: ElementConditionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.elementConditionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'elementConditionListModification',
                content: 'Deleted an elementCondition'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-element-condition-delete-popup',
    template: ''
})
export class ElementConditionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ elementCondition }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ElementConditionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.elementCondition = elementCondition;
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
