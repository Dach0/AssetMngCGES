import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Power } from 'app/shared/model/power.model';
import { PowerService } from './power.service';
import { PowerComponent } from './power.component';
import { PowerDetailComponent } from './power-detail.component';
import { PowerUpdateComponent } from './power-update.component';
import { PowerDeletePopupComponent } from './power-delete-dialog.component';
import { IPower } from 'app/shared/model/power.model';

@Injectable({ providedIn: 'root' })
export class PowerResolve implements Resolve<IPower> {
    constructor(private service: PowerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((power: HttpResponse<Power>) => power.body));
        }
        return of(new Power());
    }
}

export const powerRoute: Routes = [
    {
        path: 'power',
        component: PowerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Powers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'power/:id/view',
        component: PowerDetailComponent,
        resolve: {
            power: PowerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Powers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'power/new',
        component: PowerUpdateComponent,
        resolve: {
            power: PowerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Powers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'power/:id/edit',
        component: PowerUpdateComponent,
        resolve: {
            power: PowerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Powers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const powerPopupRoute: Routes = [
    {
        path: 'power/:id/delete',
        component: PowerDeletePopupComponent,
        resolve: {
            power: PowerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Powers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
