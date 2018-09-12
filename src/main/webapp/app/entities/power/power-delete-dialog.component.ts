import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPower } from 'app/shared/model/power.model';
import { PowerService } from './power.service';

@Component({
    selector: 'jhi-power-delete-dialog',
    templateUrl: './power-delete-dialog.component.html'
})
export class PowerDeleteDialogComponent {
    power: IPower;

    constructor(private powerService: PowerService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.powerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'powerListModification',
                content: 'Deleted an power'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-power-delete-popup',
    template: ''
})
export class PowerDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ power }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PowerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.power = power;
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
