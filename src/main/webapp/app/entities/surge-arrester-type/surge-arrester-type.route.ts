import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SurgeArresterType } from 'app/shared/model/surge-arrester-type.model';
import { SurgeArresterTypeService } from './surge-arrester-type.service';
import { SurgeArresterTypeComponent } from './surge-arrester-type.component';
import { SurgeArresterTypeDetailComponent } from './surge-arrester-type-detail.component';
import { SurgeArresterTypeUpdateComponent } from './surge-arrester-type-update.component';
import { SurgeArresterTypeDeletePopupComponent } from './surge-arrester-type-delete-dialog.component';
import { ISurgeArresterType } from 'app/shared/model/surge-arrester-type.model';

@Injectable({ providedIn: 'root' })
export class SurgeArresterTypeResolve implements Resolve<ISurgeArresterType> {
    constructor(private service: SurgeArresterTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((surgeArresterType: HttpResponse<SurgeArresterType>) => surgeArresterType.body));
        }
        return of(new SurgeArresterType());
    }
}

export const surgeArresterTypeRoute: Routes = [
    {
        path: 'surge-arrester-type',
        component: SurgeArresterTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresterTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'surge-arrester-type/:id/view',
        component: SurgeArresterTypeDetailComponent,
        resolve: {
            surgeArresterType: SurgeArresterTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresterTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'surge-arrester-type/new',
        component: SurgeArresterTypeUpdateComponent,
        resolve: {
            surgeArresterType: SurgeArresterTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresterTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'surge-arrester-type/:id/edit',
        component: SurgeArresterTypeUpdateComponent,
        resolve: {
            surgeArresterType: SurgeArresterTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresterTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const surgeArresterTypePopupRoute: Routes = [
    {
        path: 'surge-arrester-type/:id/delete',
        component: SurgeArresterTypeDeletePopupComponent,
        resolve: {
            surgeArresterType: SurgeArresterTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresterTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
