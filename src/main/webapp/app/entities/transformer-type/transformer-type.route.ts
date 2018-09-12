import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TransformerType } from 'app/shared/model/transformer-type.model';
import { TransformerTypeService } from './transformer-type.service';
import { TransformerTypeComponent } from './transformer-type.component';
import { TransformerTypeDetailComponent } from './transformer-type-detail.component';
import { TransformerTypeUpdateComponent } from './transformer-type-update.component';
import { TransformerTypeDeletePopupComponent } from './transformer-type-delete-dialog.component';
import { ITransformerType } from 'app/shared/model/transformer-type.model';

@Injectable({ providedIn: 'root' })
export class TransformerTypeResolve implements Resolve<ITransformerType> {
    constructor(private service: TransformerTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((transformerType: HttpResponse<TransformerType>) => transformerType.body));
        }
        return of(new TransformerType());
    }
}

export const transformerTypeRoute: Routes = [
    {
        path: 'transformer-type',
        component: TransformerTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformerTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformer-type/:id/view',
        component: TransformerTypeDetailComponent,
        resolve: {
            transformerType: TransformerTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformerTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformer-type/new',
        component: TransformerTypeUpdateComponent,
        resolve: {
            transformerType: TransformerTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformerTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformer-type/:id/edit',
        component: TransformerTypeUpdateComponent,
        resolve: {
            transformerType: TransformerTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformerTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transformerTypePopupRoute: Routes = [
    {
        path: 'transformer-type/:id/delete',
        component: TransformerTypeDeletePopupComponent,
        resolve: {
            transformerType: TransformerTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransformerTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
