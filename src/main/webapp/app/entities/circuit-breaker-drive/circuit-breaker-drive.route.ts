import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';
import { CircuitBreakerDriveService } from './circuit-breaker-drive.service';
import { CircuitBreakerDriveComponent } from './circuit-breaker-drive.component';
import { CircuitBreakerDriveDetailComponent } from './circuit-breaker-drive-detail.component';
import { CircuitBreakerDriveUpdateComponent } from './circuit-breaker-drive-update.component';
import { CircuitBreakerDriveDeletePopupComponent } from './circuit-breaker-drive-delete-dialog.component';
import { ICircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';

@Injectable({ providedIn: 'root' })
export class CircuitBreakerDriveResolve implements Resolve<ICircuitBreakerDrive> {
    constructor(private service: CircuitBreakerDriveService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((circuitBreakerDrive: HttpResponse<CircuitBreakerDrive>) => circuitBreakerDrive.body));
        }
        return of(new CircuitBreakerDrive());
    }
}

export const circuitBreakerDriveRoute: Routes = [
    {
        path: 'circuit-breaker-drive',
        component: CircuitBreakerDriveComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker-drive/:id/view',
        component: CircuitBreakerDriveDetailComponent,
        resolve: {
            circuitBreakerDrive: CircuitBreakerDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker-drive/new',
        component: CircuitBreakerDriveUpdateComponent,
        resolve: {
            circuitBreakerDrive: CircuitBreakerDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerDrives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker-drive/:id/edit',
        component: CircuitBreakerDriveUpdateComponent,
        resolve: {
            circuitBreakerDrive: CircuitBreakerDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerDrives'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const circuitBreakerDrivePopupRoute: Routes = [
    {
        path: 'circuit-breaker-drive/:id/delete',
        component: CircuitBreakerDriveDeletePopupComponent,
        resolve: {
            circuitBreakerDrive: CircuitBreakerDriveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerDrives'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
