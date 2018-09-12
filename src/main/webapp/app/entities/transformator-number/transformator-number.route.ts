import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TransformatorNumber } from 'app/shared/model/transformator-number.model';
import { TransformatorNumberService } from './transformator-number.service';
import { TransformatorNumberComponent } from './transformator-number.component';
import { TransformatorNumberDetailComponent } from './transformator-number-detail.component';
import { TransformatorNumberUpdateComponent } from './transformator-number-update.component';
import { TransformatorNumberDeletePopupComponent } from './transformator-number-delete-dialog.component';
import { ITransformatorNumber } from 'app/shared/model/transformator-number.model';

@Injectable({ providedIn: 'root' })
export class TransformatorNumberResolve implements Resolve<ITransformatorNumber> {
    constructor(private service: TransformatorNumberService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((transformatorNumber: HttpResponse<TransformatorNumber>) => transformatorNumber.body));
        }
        return of(new TransformatorNumber());
    }
}

export const transformatorNumberRoute: Routes = [
    {
        path: 'transformator-number',
        component: TransformatorNumberComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformatorNumbers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformator-number/:id/view',
        component: TransformatorNumberDetailComponent,
        resolve: {
            transformatorNumber: TransformatorNumberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformatorNumbers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformator-number/new',
        component: TransformatorNumberUpdateComponent,
        resolve: {
            transformatorNumber: TransformatorNumberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformatorNumbers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformator-number/:id/edit',
        component: TransformatorNumberUpdateComponent,
        resolve: {
            transformatorNumber: TransformatorNumberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformatorNumbers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transformatorNumberPopupRoute: Routes = [
    {
        path: 'transformator-number/:id/delete',
        component: TransformatorNumberDeletePopupComponent,
        resolve: {
            transformatorNumber: TransformatorNumberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformatorNumbers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
