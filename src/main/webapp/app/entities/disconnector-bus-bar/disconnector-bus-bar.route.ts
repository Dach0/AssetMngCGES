import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';
import { DisconnectorBusBarService } from './disconnector-bus-bar.service';
import { DisconnectorBusBarComponent } from './disconnector-bus-bar.component';
import { DisconnectorBusBarDetailComponent } from './disconnector-bus-bar-detail.component';
import { DisconnectorBusBarUpdateComponent } from './disconnector-bus-bar-update.component';
import { DisconnectorBusBarDeletePopupComponent } from './disconnector-bus-bar-delete-dialog.component';
import { IDisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';

@Injectable({ providedIn: 'root' })
export class DisconnectorBusBarResolve implements Resolve<IDisconnectorBusBar> {
    constructor(private service: DisconnectorBusBarService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((disconnectorBusBar: HttpResponse<DisconnectorBusBar>) => disconnectorBusBar.body));
        }
        return of(new DisconnectorBusBar());
    }
}

export const disconnectorBusBarRoute: Routes = [
    {
        path: 'disconnector-bus-bar',
        component: DisconnectorBusBarComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorBusBars'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-bus-bar/:id/view',
        component: DisconnectorBusBarDetailComponent,
        resolve: {
            disconnectorBusBar: DisconnectorBusBarResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorBusBars'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-bus-bar/new',
        component: DisconnectorBusBarUpdateComponent,
        resolve: {
            disconnectorBusBar: DisconnectorBusBarResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorBusBars'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-bus-bar/:id/edit',
        component: DisconnectorBusBarUpdateComponent,
        resolve: {
            disconnectorBusBar: DisconnectorBusBarResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorBusBars'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const disconnectorBusBarPopupRoute: Routes = [
    {
        path: 'disconnector-bus-bar/:id/delete',
        component: DisconnectorBusBarDeletePopupComponent,
        resolve: {
            disconnectorBusBar: DisconnectorBusBarResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorBusBars'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
