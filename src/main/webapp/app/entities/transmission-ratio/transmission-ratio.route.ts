import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TransmissionRatio } from 'app/shared/model/transmission-ratio.model';
import { TransmissionRatioService } from './transmission-ratio.service';
import { TransmissionRatioComponent } from './transmission-ratio.component';
import { TransmissionRatioDetailComponent } from './transmission-ratio-detail.component';
import { TransmissionRatioUpdateComponent } from './transmission-ratio-update.component';
import { TransmissionRatioDeletePopupComponent } from './transmission-ratio-delete-dialog.component';
import { ITransmissionRatio } from 'app/shared/model/transmission-ratio.model';

@Injectable({ providedIn: 'root' })
export class TransmissionRatioResolve implements Resolve<ITransmissionRatio> {
    constructor(private service: TransmissionRatioService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((transmissionRatio: HttpResponse<TransmissionRatio>) => transmissionRatio.body));
        }
        return of(new TransmissionRatio());
    }
}

export const transmissionRatioRoute: Routes = [
    {
        path: 'transmission-ratio',
        component: TransmissionRatioComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransmissionRatios'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transmission-ratio/:id/view',
        component: TransmissionRatioDetailComponent,
        resolve: {
            transmissionRatio: TransmissionRatioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransmissionRatios'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transmission-ratio/new',
        component: TransmissionRatioUpdateComponent,
        resolve: {
            transmissionRatio: TransmissionRatioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransmissionRatios'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transmission-ratio/:id/edit',
        component: TransmissionRatioUpdateComponent,
        resolve: {
            transmissionRatio: TransmissionRatioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransmissionRatios'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transmissionRatioPopupRoute: Routes = [
    {
        path: 'transmission-ratio/:id/delete',
        component: TransmissionRatioDeletePopupComponent,
        resolve: {
            transmissionRatio: TransmissionRatioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransmissionRatios'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
