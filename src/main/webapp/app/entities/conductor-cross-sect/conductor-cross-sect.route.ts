import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';
import { ConductorCrossSectService } from './conductor-cross-sect.service';
import { ConductorCrossSectComponent } from './conductor-cross-sect.component';
import { ConductorCrossSectDetailComponent } from './conductor-cross-sect-detail.component';
import { ConductorCrossSectUpdateComponent } from './conductor-cross-sect-update.component';
import { ConductorCrossSectDeletePopupComponent } from './conductor-cross-sect-delete-dialog.component';
import { IConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';

@Injectable({ providedIn: 'root' })
export class ConductorCrossSectResolve implements Resolve<IConductorCrossSect> {
    constructor(private service: ConductorCrossSectService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((conductorCrossSect: HttpResponse<ConductorCrossSect>) => conductorCrossSect.body));
        }
        return of(new ConductorCrossSect());
    }
}

export const conductorCrossSectRoute: Routes = [
    {
        path: 'conductor-cross-sect',
        component: ConductorCrossSectComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConductorCrossSects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conductor-cross-sect/:id/view',
        component: ConductorCrossSectDetailComponent,
        resolve: {
            conductorCrossSect: ConductorCrossSectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConductorCrossSects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conductor-cross-sect/new',
        component: ConductorCrossSectUpdateComponent,
        resolve: {
            conductorCrossSect: ConductorCrossSectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConductorCrossSects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conductor-cross-sect/:id/edit',
        component: ConductorCrossSectUpdateComponent,
        resolve: {
            conductorCrossSect: ConductorCrossSectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConductorCrossSects'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const conductorCrossSectPopupRoute: Routes = [
    {
        path: 'conductor-cross-sect/:id/delete',
        component: ConductorCrossSectDeletePopupComponent,
        resolve: {
            conductorCrossSect: ConductorCrossSectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConductorCrossSects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
