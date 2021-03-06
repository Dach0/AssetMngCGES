import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITitleInBoard } from 'app/shared/model/title-in-board.model';
import { TitleInBoardService } from './title-in-board.service';

@Component({
    selector: 'jhi-title-in-board-delete-dialog',
    templateUrl: './title-in-board-delete-dialog.component.html'
})
export class TitleInBoardDeleteDialogComponent {
    titleInBoard: ITitleInBoard;

    constructor(
        private titleInBoardService: TitleInBoardService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.titleInBoardService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'titleInBoardListModification',
                content: 'Deleted an titleInBoard'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-title-in-board-delete-popup',
    template: ''
})
export class TitleInBoardDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ titleInBoard }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TitleInBoardDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.titleInBoard = titleInBoard;
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
