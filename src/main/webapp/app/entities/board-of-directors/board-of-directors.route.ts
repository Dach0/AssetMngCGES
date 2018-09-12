import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BoardOfDirectors } from 'app/shared/model/board-of-directors.model';
import { BoardOfDirectorsService } from './board-of-directors.service';
import { BoardOfDirectorsComponent } from './board-of-directors.component';
import { BoardOfDirectorsDetailComponent } from './board-of-directors-detail.component';
import { BoardOfDirectorsUpdateComponent } from './board-of-directors-update.component';
import { BoardOfDirectorsDeletePopupComponent } from './board-of-directors-delete-dialog.component';
import { IBoardOfDirectors } from 'app/shared/model/board-of-directors.model';

@Injectable({ providedIn: 'root' })
export class BoardOfDirectorsResolve implements Resolve<IBoardOfDirectors> {
    constructor(private service: BoardOfDirectorsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((boardOfDirectors: HttpResponse<BoardOfDirectors>) => boardOfDirectors.body));
        }
        return of(new BoardOfDirectors());
    }
}

export const boardOfDirectorsRoute: Routes = [
    {
        path: 'board-of-directors',
        component: BoardOfDirectorsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardOfDirectors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'board-of-directors/:id/view',
        component: BoardOfDirectorsDetailComponent,
        resolve: {
            boardOfDirectors: BoardOfDirectorsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardOfDirectors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'board-of-directors/new',
        component: BoardOfDirectorsUpdateComponent,
        resolve: {
            boardOfDirectors: BoardOfDirectorsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardOfDirectors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'board-of-directors/:id/edit',
        component: BoardOfDirectorsUpdateComponent,
        resolve: {
            boardOfDirectors: BoardOfDirectorsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardOfDirectors'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const boardOfDirectorsPopupRoute: Routes = [
    {
        path: 'board-of-directors/:id/delete',
        component: BoardOfDirectorsDeletePopupComponent,
        resolve: {
            boardOfDirectors: BoardOfDirectorsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BoardOfDirectors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
