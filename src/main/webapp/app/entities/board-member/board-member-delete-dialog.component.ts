import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBoardMember } from 'app/shared/model/board-member.model';
import { BoardMemberService } from './board-member.service';

@Component({
    selector: 'jhi-board-member-delete-dialog',
    templateUrl: './board-member-delete-dialog.component.html'
})
export class BoardMemberDeleteDialogComponent {
    boardMember: IBoardMember;

    constructor(
        private boardMemberService: BoardMemberService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.boardMemberService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'boardMemberListModification',
                content: 'Deleted an boardMember'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-board-member-delete-popup',
    template: ''
})
export class BoardMemberDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ boardMember }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BoardMemberDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.boardMember = boardMember;
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
