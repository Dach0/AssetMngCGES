import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CorrectionFactor } from 'app/shared/model/correction-factor.model';
import { CorrectionFactorService } from './correction-factor.service';
import { CorrectionFactorComponent } from './correction-factor.component';
import { CorrectionFactorDetailComponent } from './correction-factor-detail.component';
import { CorrectionFactorUpdateComponent } from './correction-factor-update.component';
import { CorrectionFactorDeletePopupComponent } from './correction-factor-delete-dialog.component';
import { ICorrectionFactor } from 'app/shared/model/correction-factor.model';

@Injectable({ providedIn: 'root' })
export class CorrectionFactorResolve implements Resolve<ICorrectionFactor> {
    constructor(private service: CorrectionFactorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((correctionFactor: HttpResponse<CorrectionFactor>) => correctionFactor.body));
        }
        return of(new CorrectionFactor());
    }
}

export const correctionFactorRoute: Routes = [
    {
        path: 'correction-factor',
        component: CorrectionFactorComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CorrectionFactors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'correction-factor/:id/view',
        component: CorrectionFactorDetailComponent,
        resolve: {
            correctionFactor: CorrectionFactorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CorrectionFactors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'correction-factor/new',
        component: CorrectionFactorUpdateComponent,
        resolve: {
            correctionFactor: CorrectionFactorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CorrectionFactors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'correction-factor/:id/edit',
        component: CorrectionFactorUpdateComponent,
        resolve: {
            correctionFactor: CorrectionFactorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CorrectionFactors'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const correctionFactorPopupRoute: Routes = [
    {
        path: 'correction-factor/:id/delete',
        component: CorrectionFactorDeletePopupComponent,
        resolve: {
            correctionFactor: CorrectionFactorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CorrectionFactors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
