import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DisconnectorType } from 'app/shared/model/disconnector-type.model';
import { DisconnectorTypeService } from './disconnector-type.service';
import { DisconnectorTypeComponent } from './disconnector-type.component';
import { DisconnectorTypeDetailComponent } from './disconnector-type-detail.component';
import { DisconnectorTypeUpdateComponent } from './disconnector-type-update.component';
import { DisconnectorTypeDeletePopupComponent } from './disconnector-type-delete-dialog.component';
import { IDisconnectorType } from 'app/shared/model/disconnector-type.model';

@Injectable({ providedIn: 'root' })
export class DisconnectorTypeResolve implements Resolve<IDisconnectorType> {
    constructor(private service: DisconnectorTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((disconnectorType: HttpResponse<DisconnectorType>) => disconnectorType.body));
        }
        return of(new DisconnectorType());
    }
}

export const disconnectorTypeRoute: Routes = [
    {
        path: 'disconnector-type',
        component: DisconnectorTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-type/:id/view',
        component: DisconnectorTypeDetailComponent,
        resolve: {
            disconnectorType: DisconnectorTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-type/new',
        component: DisconnectorTypeUpdateComponent,
        resolve: {
            disconnectorType: DisconnectorTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-type/:id/edit',
        component: DisconnectorTypeUpdateComponent,
        resolve: {
            disconnectorType: DisconnectorTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const disconnectorTypePopupRoute: Routes = [
    {
        path: 'disconnector-type/:id/delete',
        component: DisconnectorTypeDeletePopupComponent,
        resolve: {
            disconnectorType: DisconnectorTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
