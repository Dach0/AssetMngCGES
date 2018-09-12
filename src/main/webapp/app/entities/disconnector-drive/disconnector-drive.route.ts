import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DisconnectorDrive } from 'app/shared/model/disconnector-drive.model';
import { DisconnectorDriveService } from './disconnector-drive.service';
import { DisconnectorDriveComponent } from './disconnector-drive.component';
import { DisconnectorDriveDetailComponent } from './disconnector-drive-detail.component';
import { DisconnectorDriveUpdateComponent } from './disconnector-drive-update.component';
import { DisconnectorDriveDeletePopupComponent } from './disconnector-drive-delete-dialog.component';
import { IDisconnectorDrive } from 'app/shared/model/disconnector-drive.model';

@Injectable({ providedIn: 'root' })
export class DisconnectorDriveResolve implements Resolve<IDisconnectorDrive> {
    constructor(private service: DisconnectorDriveService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((disconnectorDrive: HttpResponse<DisconnectorDrive>) => disconnectorDrive.body));
        }
        return of(new DisconnectorDrive());
    }
}

export const disconnectorDriveRoute: Routes = [
    {
        path: 'disconnector-drive',
        component: DisconnectorDriveComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-drive/:id/view',
        component: DisconnectorDriveDetailComponent,
        resolve: {
            disconnectorDrive: DisconnectorDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-drive/new',
        component: DisconnectorDriveUpdateComponent,
        resolve: {
            disconnectorDrive: DisconnectorDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'disconnector-drive/:id/edit',
        component: DisconnectorDriveUpdateComponent,
        resolve: {
            disconnectorDrive: DisconnectorDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorDrives'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const disconnectorDrivePopupRoute: Routes = [
    {
        path: 'disconnector-drive/:id/delete',
        component: DisconnectorDriveDeletePopupComponent,
        resolve: {
            disconnectorDrive: DisconnectorDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DisconnectorDrives'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
