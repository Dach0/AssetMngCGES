import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BoardMember } from 'app/shared/model/board-member.model';
import { BoardMemberService } from './board-member.service';
import { BoardMemberComponent } from './board-member.component';
import { BoardMemberDetailComponent } from './board-member-detail.component';
import { BoardMemberUpdateComponent } from './board-member-update.component';
import { BoardMemberDeletePopupComponent } from './board-member-delete-dialog.component';
import { IBoardMember } from 'app/shared/model/board-member.model';

@Injectable({ providedIn: 'root' })
export class BoardMemberResolve implements Resolve<IBoardMember> {
    constructor(private service: BoardMemberService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((boardMember: HttpResponse<BoardMember>) => boardMember.body));
        }
        return of(new BoardMember());
    }
}

export const boardMemberRoute: Routes = [
    {
        path: 'board-member',
        component: BoardMemberComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardMembers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'board-member/:id/view',
        component: BoardMemberDetailComponent,
        resolve: {
            boardMember: BoardMemberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardMembers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'board-member/new',
        component: BoardMemberUpdateComponent,
        resolve: {
            boardMember: BoardMemberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardMembers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'board-member/:id/edit',
        component: BoardMemberUpdateComponent,
        resolve: {
            boardMember: BoardMemberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardMembers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const boardMemberPopupRoute: Routes = [
    {
        path: 'board-member/:id/delete',
        component: BoardMemberDeletePopupComponent,
        resolve: {
            boardMember: BoardMemberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardMembers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
