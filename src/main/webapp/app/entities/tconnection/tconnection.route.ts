import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tconnection } from 'app/shared/model/tconnection.model';
import { TconnectionService } from './tconnection.service';
import { TconnectionComponent } from './tconnection.component';
import { TconnectionDetailComponent } from './tconnection-detail.component';
import { TconnectionUpdateComponent } from './tconnection-update.component';
import { TconnectionDeletePopupComponent } from './tconnection-delete-dialog.component';
import { ITconnection } from 'app/shared/model/tconnection.model';

@Injectable({ providedIn: 'root' })
export class TconnectionResolve implements Resolve<ITconnection> {
    constructor(private service: TconnectionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((tconnection: HttpResponse<Tconnection>) => tconnection.body));
        }
        return of(new Tconnection());
    }
}

export const tconnectionRoute: Routes = [
    {
        path: 'tconnection',
        component: TconnectionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tconnections'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tconnection/:id/view',
        component: TconnectionDetailComponent,
        resolve: {
            tconnection: TconnectionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tconnections'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tconnection/new',
        component: TconnectionUpdateComponent,
        resolve: {
            tconnection: TconnectionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tconnections'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tconnection/:id/edit',
        component: TconnectionUpdateComponent,
        resolve: {
            tconnection: TconnectionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tconnections'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tconnectionPopupRoute: Routes = [
    {
        path: 'tconnection/:id/delete',
        component: TconnectionDeletePopupComponent,
        resolve: {
            tconnection: TconnectionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tconnections'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
