import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TitleInBoard } from 'app/shared/model/title-in-board.model';
import { TitleInBoardService } from './title-in-board.service';
import { TitleInBoardComponent } from './title-in-board.component';
import { TitleInBoardDetailComponent } from './title-in-board-detail.component';
import { TitleInBoardUpdateComponent } from './title-in-board-update.component';
import { TitleInBoardDeletePopupComponent } from './title-in-board-delete-dialog.component';
import { ITitleInBoard } from 'app/shared/model/title-in-board.model';

@Injectable({ providedIn: 'root' })
export class TitleInBoardResolve implements Resolve<ITitleInBoard> {
    constructor(private service: TitleInBoardService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((titleInBoard: HttpResponse<TitleInBoard>) => titleInBoard.body));
        }
        return of(new TitleInBoard());
    }
}

export const titleInBoardRoute: Routes = [
    {
        path: 'title-in-board',
        component: TitleInBoardComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TitleInBoards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'title-in-board/:id/view',
        component: TitleInBoardDetailComponent,
        resolve: {
            titleInBoard: TitleInBoardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TitleInBoards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'title-in-board/new',
        component: TitleInBoardUpdateComponent,
        resolve: {
            titleInBoard: TitleInBoardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TitleInBoards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'title-in-board/:id/edit',
        component: TitleInBoardUpdateComponent,
        resolve: {
            titleInBoard: TitleInBoardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TitleInBoards'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const titleInBoardPopupRoute: Routes = [
    {
        path: 'title-in-board/:id/delete',
        component: TitleInBoardDeletePopupComponent,
        resolve: {
            titleInBoard: TitleInBoardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TitleInBoards'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
