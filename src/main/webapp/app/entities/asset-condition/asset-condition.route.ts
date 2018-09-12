import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AssetCondition } from 'app/shared/model/asset-condition.model';
import { AssetConditionService } from './asset-condition.service';
import { AssetConditionComponent } from './asset-condition.component';
import { AssetConditionDetailComponent } from './asset-condition-detail.component';
import { AssetConditionUpdateComponent } from './asset-condition-update.component';
import { AssetConditionDeletePopupComponent } from './asset-condition-delete-dialog.component';
import { IAssetCondition } from 'app/shared/model/asset-condition.model';

@Injectable({ providedIn: 'root' })
export class AssetConditionResolve implements Resolve<IAssetCondition> {
    constructor(private service: AssetConditionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((assetCondition: HttpResponse<AssetCondition>) => assetCondition.body));
        }
        return of(new AssetCondition());
    }
}

export const assetConditionRoute: Routes = [
    {
        path: 'asset-condition',
        component: AssetConditionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetConditions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asset-condition/:id/view',
        component: AssetConditionDetailComponent,
        resolve: {
            assetCondition: AssetConditionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetConditions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asset-condition/new',
        component: AssetConditionUpdateComponent,
        resolve: {
            assetCondition: AssetConditionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetConditions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asset-condition/:id/edit',
        component: AssetConditionUpdateComponent,
        resolve: {
            assetCondition: AssetConditionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetConditions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const assetConditionPopupRoute: Routes = [
    {
        path: 'asset-condition/:id/delete',
        component: AssetConditionDeletePopupComponent,
        resolve: {
            assetCondition: AssetConditionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetConditions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
