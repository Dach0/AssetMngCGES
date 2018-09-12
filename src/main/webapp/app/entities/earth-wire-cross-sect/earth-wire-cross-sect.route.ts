import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';
import { EarthWireCrossSectService } from './earth-wire-cross-sect.service';
import { EarthWireCrossSectComponent } from './earth-wire-cross-sect.component';
import { EarthWireCrossSectDetailComponent } from './earth-wire-cross-sect-detail.component';
import { EarthWireCrossSectUpdateComponent } from './earth-wire-cross-sect-update.component';
import { EarthWireCrossSectDeletePopupComponent } from './earth-wire-cross-sect-delete-dialog.component';
import { IEarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';

@Injectable({ providedIn: 'root' })
export class EarthWireCrossSectResolve implements Resolve<IEarthWireCrossSect> {
    constructor(private service: EarthWireCrossSectService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((earthWireCrossSect: HttpResponse<EarthWireCrossSect>) => earthWireCrossSect.body));
        }
        return of(new EarthWireCrossSect());
    }
}

export const earthWireCrossSectRoute: Routes = [
    {
        path: 'earth-wire-cross-sect',
        component: EarthWireCrossSectComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EarthWireCrossSects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'earth-wire-cross-sect/:id/view',
        component: EarthWireCrossSectDetailComponent,
        resolve: {
            earthWireCrossSect: EarthWireCrossSectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EarthWireCrossSects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'earth-wire-cross-sect/new',
        component: EarthWireCrossSectUpdateComponent,
        resolve: {
            earthWireCrossSect: EarthWireCrossSectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EarthWireCrossSects'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'earth-wire-cross-sect/:id/edit',
        component: EarthWireCrossSectUpdateComponent,
        resolve: {
            earthWireCrossSect: EarthWireCrossSectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EarthWireCrossSects'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const earthWireCrossSectPopupRoute: Routes = [
    {
        path: 'earth-wire-cross-sect/:id/delete',
        component: EarthWireCrossSectDeletePopupComponent,
        resolve: {
            earthWireCrossSect: EarthWireCrossSectResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EarthWireCrossSects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
