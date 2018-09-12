import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';
import { EarthWireCrossSectService } from './earth-wire-cross-sect.service';

@Component({
    selector: 'jhi-earth-wire-cross-sect-delete-dialog',
    templateUrl: './earth-wire-cross-sect-delete-dialog.component.html'
})
export class EarthWireCrossSectDeleteDialogComponent {
    earthWireCrossSect: IEarthWireCrossSect;

    constructor(
        private earthWireCrossSectService: EarthWireCrossSectService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.earthWireCrossSectService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'earthWireCrossSectListModification',
                content: 'Deleted an earthWireCrossSect'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-earth-wire-cross-sect-delete-popup',
    template: ''
})
export class EarthWireCrossSectDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ earthWireCrossSect }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EarthWireCrossSectDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.earthWireCrossSect = earthWireCrossSect;
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
