import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';
import { DisconnectorLineExitService } from './disconnector-line-exit.service';
import { DisconnectorLineExitComponent } from './disconnector-line-exit.component';
import { DisconnectorLineExitDetailComponent } from './disconnector-line-exit-detail.component';
import { DisconnectorLineExitUpdateComponent } from './disconnector-line-exit-update.component';
import { DisconnectorLineExitDeletePopupComponent } from './disconnector-line-exit-delete-dialog.component';
import { IDisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';

@Injectable({ providedIn: 'root' })
export class DisconnectorLineExitResolve implements Resolve<IDisconnectorLineExit> {
    constructor(private service: DisconnectorLineExitService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((disconnectorLineExit: HttpResponse<DisconnectorLineExit>) => disconnectorLineExit.body));
        }
        return of(new DisconnectorLineExit());
    }
}

export const disconnectorLineExitRoute: Routes = [
    {
        path: 'disconnector-line-exit',
        component: DisconnectorLineExitComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorLineExits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-line-exit/:id/view',
        component: DisconnectorLineExitDetailComponent,
        resolve: {
            disconnectorLineExit: DisconnectorLineExitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorLineExits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-line-exit/new',
        component: DisconnectorLineExitUpdateComponent,
        resolve: {
            disconnectorLineExit: DisconnectorLineExitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorLineExits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-line-exit/:id/edit',
        component: DisconnectorLineExitUpdateComponent,
        resolve: {
            disconnectorLineExit: DisconnectorLineExitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorLineExits'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const disconnectorLineExitPopupRoute: Routes = [
    {
        path: 'disconnector-line-exit/:id/delete',
        component: DisconnectorLineExitDeletePopupComponent,
        resolve: {
            disconnectorLineExit: DisconnectorLineExitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorLineExits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
