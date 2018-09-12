import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AssetStatus } from 'app/shared/model/asset-status.model';
import { AssetStatusService } from './asset-status.service';
import { AssetStatusComponent } from './asset-status.component';
import { AssetStatusDetailComponent } from './asset-status-detail.component';
import { AssetStatusUpdateComponent } from './asset-status-update.component';
import { AssetStatusDeletePopupComponent } from './asset-status-delete-dialog.component';
import { IAssetStatus } from 'app/shared/model/asset-status.model';

@Injectable({ providedIn: 'root' })
export class AssetStatusResolve implements Resolve<IAssetStatus> {
    constructor(private service: AssetStatusService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((assetStatus: HttpResponse<AssetStatus>) => assetStatus.body));
        }
        return of(new AssetStatus());
    }
}

export const assetStatusRoute: Routes = [
    {
        path: 'asset-status',
        component: AssetStatusComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asset-status/:id/view',
        component: AssetStatusDetailComponent,
        resolve: {
            assetStatus: AssetStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asset-status/new',
        component: AssetStatusUpdateComponent,
        resolve: {
            assetStatus: AssetStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asset-status/:id/edit',
        component: AssetStatusUpdateComponent,
        resolve: {
            assetStatus: AssetStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetStatuses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const assetStatusPopupRoute: Routes = [
    {
        path: 'asset-status/:id/delete',
        component: AssetStatusDeletePopupComponent,
        resolve: {
            assetStatus: AssetStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AssetStatuses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
