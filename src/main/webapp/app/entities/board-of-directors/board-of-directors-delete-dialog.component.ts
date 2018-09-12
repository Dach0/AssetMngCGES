import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBoardOfDirectors } from 'app/shared/model/board-of-directors.model';
import { BoardOfDirectorsService } from './board-of-directors.service';

@Component({
    selector: 'jhi-board-of-directors-delete-dialog',
    templateUrl: './board-of-directors-delete-dialog.component.html'
})
export class BoardOfDirectorsDeleteDialogComponent {
    boardOfDirectors: IBoardOfDirectors;

    constructor(
        private boardOfDirectorsService: BoardOfDirectorsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.boardOfDirectorsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'boardOfDirectorsListModification',
                content: 'Deleted an boardOfDirectors'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-board-of-directors-delete-popup',
    template: ''
})
export class BoardOfDirectorsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ boardOfDirectors }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BoardOfDirectorsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.boardOfDirectors = boardOfDirectors;
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
