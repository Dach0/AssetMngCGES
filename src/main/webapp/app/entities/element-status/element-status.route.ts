import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ElementStatus } from 'app/shared/model/element-status.model';
import { ElementStatusService } from './element-status.service';
import { ElementStatusComponent } from './element-status.component';
import { ElementStatusDetailComponent } from './element-status-detail.component';
import { ElementStatusUpdateComponent } from './element-status-update.component';
import { ElementStatusDeletePopupComponent } from './element-status-delete-dialog.component';
import { IElementStatus } from 'app/shared/model/element-status.model';

@Injectable({ providedIn: 'root' })
export class ElementStatusResolve implements Resolve<IElementStatus> {
    constructor(private service: ElementStatusService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((elementStatus: HttpResponse<ElementStatus>) => elementStatus.body));
        }
        return of(new ElementStatus());
    }
}

export const elementStatusRoute: Routes = [
    {
        path: 'element-status',
        component: ElementStatusComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'element-status/:id/view',
        component: ElementStatusDetailComponent,
        resolve: {
            elementStatus: ElementStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'element-status/new',
        component: ElementStatusUpdateComponent,
        resolve: {
            elementStatus: ElementStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementStatuses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'element-status/:id/edit',
        component: ElementStatusUpdateComponent,
        resolve: {
            elementStatus: ElementStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementStatuses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const elementStatusPopupRoute: Routes = [
    {
        path: 'element-status/:id/delete',
        component: ElementStatusDeletePopupComponent,
        resolve: {
            elementStatus: ElementStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementStatuses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
