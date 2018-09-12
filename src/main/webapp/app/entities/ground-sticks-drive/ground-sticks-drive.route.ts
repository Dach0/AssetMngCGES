import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';
import { GroundSticksDriveService } from './ground-sticks-drive.service';
import { GroundSticksDriveComponent } from './ground-sticks-drive.component';
import { GroundSticksDriveDetailComponent } from './ground-sticks-drive-detail.component';
import { GroundSticksDriveUpdateComponent } from './ground-sticks-drive-update.component';
import { GroundSticksDriveDeletePopupComponent } from './ground-sticks-drive-delete-dialog.component';
import { IGroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';

@Injectable({ providedIn: 'root' })
export class GroundSticksDriveResolve implements Resolve<IGroundSticksDrive> {
    constructor(private service: GroundSticksDriveService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((groundSticksDrive: HttpResponse<GroundSticksDrive>) => groundSticksDrive.body));
        }
        return of(new GroundSticksDrive());
    }
}

export const groundSticksDriveRoute: Routes = [
    {
        path: 'ground-sticks-drive',
        component: GroundSticksDriveComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ground-sticks-drive/:id/view',
        component: GroundSticksDriveDetailComponent,
        resolve: {
            groundSticksDrive: GroundSticksDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ground-sticks-drive/new',
        component: GroundSticksDriveUpdateComponent,
        resolve: {
            groundSticksDrive: GroundSticksDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ground-sticks-drive/:id/edit',
        component: GroundSticksDriveUpdateComponent,
        resolve: {
            groundSticksDrive: GroundSticksDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksDrives'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const groundSticksDrivePopupRoute: Routes = [
    {
        path: 'ground-sticks-drive/:id/delete',
        component: GroundSticksDriveDeletePopupComponent,
        resolve: {
            groundSticksDrive: GroundSticksDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundSticksDrives'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
