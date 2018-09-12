import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ThermalLimit } from 'app/shared/model/thermal-limit.model';
import { ThermalLimitService } from './thermal-limit.service';
import { ThermalLimitComponent } from './thermal-limit.component';
import { ThermalLimitDetailComponent } from './thermal-limit-detail.component';
import { ThermalLimitUpdateComponent } from './thermal-limit-update.component';
import { ThermalLimitDeletePopupComponent } from './thermal-limit-delete-dialog.component';
import { IThermalLimit } from 'app/shared/model/thermal-limit.model';

@Injectable({ providedIn: 'root' })
export class ThermalLimitResolve implements Resolve<IThermalLimit> {
    constructor(private service: ThermalLimitService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((thermalLimit: HttpResponse<ThermalLimit>) => thermalLimit.body));
        }
        return of(new ThermalLimit());
    }
}

export const thermalLimitRoute: Routes = [
    {
        path: 'thermal-limit',
        component: ThermalLimitComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ThermalLimits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'thermal-limit/:id/view',
        component: ThermalLimitDetailComponent,
        resolve: {
            thermalLimit: ThermalLimitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ThermalLimits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'thermal-limit/new',
        component: ThermalLimitUpdateComponent,
        resolve: {
            thermalLimit: ThermalLimitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ThermalLimits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'thermal-limit/:id/edit',
        component: ThermalLimitUpdateComponent,
        resolve: {
            thermalLimit: ThermalLimitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ThermalLimits'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const thermalLimitPopupRoute: Routes = [
    {
        path: 'thermal-limit/:id/delete',
        component: ThermalLimitDeletePopupComponent,
        resolve: {
            thermalLimit: ThermalLimitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ThermalLimits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
