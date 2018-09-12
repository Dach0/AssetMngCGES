import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Ohl } from 'app/shared/model/ohl.model';
import { OhlService } from './ohl.service';
import { OhlComponent } from './ohl.component';
import { OhlDetailComponent } from './ohl-detail.component';
import { OhlUpdateComponent } from './ohl-update.component';
import { OhlDeletePopupComponent } from './ohl-delete-dialog.component';
import { IOhl } from 'app/shared/model/ohl.model';

@Injectable({ providedIn: 'root' })
export class OhlResolve implements Resolve<IOhl> {
    constructor(private service: OhlService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((ohl: HttpResponse<Ohl>) => ohl.body));
        }
        return of(new Ohl());
    }
}

export const ohlRoute: Routes = [
    {
        path: 'ohl',
        component: OhlComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ohls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ohl/:id/view',
        component: OhlDetailComponent,
        resolve: {
            ohl: OhlResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ohls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ohl/new',
        component: OhlUpdateComponent,
        resolve: {
            ohl: OhlResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ohls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ohl/:id/edit',
        component: OhlUpdateComponent,
        resolve: {
            ohl: OhlResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ohls'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ohlPopupRoute: Routes = [
    {
        path: 'ohl/:id/delete',
        component: OhlDeletePopupComponent,
        resolve: {
            ohl: OhlResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ohls'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
