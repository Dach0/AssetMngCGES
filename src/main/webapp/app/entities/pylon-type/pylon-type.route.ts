import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PylonType } from 'app/shared/model/pylon-type.model';
import { PylonTypeService } from './pylon-type.service';
import { PylonTypeComponent } from './pylon-type.component';
import { PylonTypeDetailComponent } from './pylon-type-detail.component';
import { PylonTypeUpdateComponent } from './pylon-type-update.component';
import { PylonTypeDeletePopupComponent } from './pylon-type-delete-dialog.component';
import { IPylonType } from 'app/shared/model/pylon-type.model';

@Injectable({ providedIn: 'root' })
export class PylonTypeResolve implements Resolve<IPylonType> {
    constructor(private service: PylonTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((pylonType: HttpResponse<PylonType>) => pylonType.body));
        }
        return of(new PylonType());
    }
}

export const pylonTypeRoute: Routes = [
    {
        path: 'pylon-type',
        component: PylonTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PylonTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pylon-type/:id/view',
        component: PylonTypeDetailComponent,
        resolve: {
            pylonType: PylonTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PylonTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pylon-type/new',
        component: PylonTypeUpdateComponent,
        resolve: {
            pylonType: PylonTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PylonTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pylon-type/:id/edit',
        component: PylonTypeUpdateComponent,
        resolve: {
            pylonType: PylonTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PylonTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pylonTypePopupRoute: Routes = [
    {
        path: 'pylon-type/:id/delete',
        component: PylonTypeDeletePopupComponent,
        resolve: {
            pylonType: PylonTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PylonTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
