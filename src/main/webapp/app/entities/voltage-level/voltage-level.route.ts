import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { VoltageLevel } from 'app/shared/model/voltage-level.model';
import { VoltageLevelService } from './voltage-level.service';
import { VoltageLevelComponent } from './voltage-level.component';
import { VoltageLevelDetailComponent } from './voltage-level-detail.component';
import { VoltageLevelUpdateComponent } from './voltage-level-update.component';
import { VoltageLevelDeletePopupComponent } from './voltage-level-delete-dialog.component';
import { IVoltageLevel } from 'app/shared/model/voltage-level.model';

@Injectable({ providedIn: 'root' })
export class VoltageLevelResolve implements Resolve<IVoltageLevel> {
    constructor(private service: VoltageLevelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((voltageLevel: HttpResponse<VoltageLevel>) => voltageLevel.body));
        }
        return of(new VoltageLevel());
    }
}

export const voltageLevelRoute: Routes = [
    {
        path: 'voltage-level',
        component: VoltageLevelComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageLevels'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'voltage-level/:id/view',
        component: VoltageLevelDetailComponent,
        resolve: {
            voltageLevel: VoltageLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageLevels'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'voltage-level/new',
        component: VoltageLevelUpdateComponent,
        resolve: {
            voltageLevel: VoltageLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageLevels'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'voltage-level/:id/edit',
        component: VoltageLevelUpdateComponent,
        resolve: {
            voltageLevel: VoltageLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageLevels'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const voltageLevelPopupRoute: Routes = [
    {
        path: 'voltage-level/:id/delete',
        component: VoltageLevelDeletePopupComponent,
        resolve: {
            voltageLevel: VoltageLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageLevels'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
