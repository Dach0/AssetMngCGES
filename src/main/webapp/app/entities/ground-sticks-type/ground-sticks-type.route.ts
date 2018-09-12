import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GroundSticksType } from 'app/shared/model/ground-sticks-type.model';
import { GroundSticksTypeService } from './ground-sticks-type.service';
import { GroundSticksTypeComponent } from './ground-sticks-type.component';
import { GroundSticksTypeDetailComponent } from './ground-sticks-type-detail.component';
import { GroundSticksTypeUpdateComponent } from './ground-sticks-type-update.component';
import { GroundSticksTypeDeletePopupComponent } from './ground-sticks-type-delete-dialog.component';
import { IGroundSticksType } from 'app/shared/model/ground-sticks-type.model';

@Injectable({ providedIn: 'root' })
export class GroundSticksTypeResolve implements Resolve<IGroundSticksType> {
    constructor(private service: GroundSticksTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((groundSticksType: HttpResponse<GroundSticksType>) => groundSticksType.body));
        }
        return of(new GroundSticksType());
    }
}

export const groundSticksTypeRoute: Routes = [
    {
        path: 'ground-sticks-type',
        component: GroundSticksTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ground-sticks-type/:id/view',
        component: GroundSticksTypeDetailComponent,
        resolve: {
            groundSticksType: GroundSticksTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ground-sticks-type/new',
        component: GroundSticksTypeUpdateComponent,
        resolve: {
            groundSticksType: GroundSticksTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ground-sticks-type/:id/edit',
        component: GroundSticksTypeUpdateComponent,
        resolve: {
            groundSticksType: GroundSticksTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const groundSticksTypePopupRoute: Routes = [
    {
        path: 'ground-sticks-type/:id/delete',
        component: GroundSticksTypeDeletePopupComponent,
        resolve: {
            groundSticksType: GroundSticksTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
