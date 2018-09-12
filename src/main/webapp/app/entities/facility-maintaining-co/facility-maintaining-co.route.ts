import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';
import { FacilityMaintainingCoService } from './facility-maintaining-co.service';
import { FacilityMaintainingCoComponent } from './facility-maintaining-co.component';
import { FacilityMaintainingCoDetailComponent } from './facility-maintaining-co-detail.component';
import { FacilityMaintainingCoUpdateComponent } from './facility-maintaining-co-update.component';
import { FacilityMaintainingCoDeletePopupComponent } from './facility-maintaining-co-delete-dialog.component';
import { IFacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';

@Injectable({ providedIn: 'root' })
export class FacilityMaintainingCoResolve implements Resolve<IFacilityMaintainingCo> {
    constructor(private service: FacilityMaintainingCoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((facilityMaintainingCo: HttpResponse<FacilityMaintainingCo>) => facilityMaintainingCo.body));
        }
        return of(new FacilityMaintainingCo());
    }
}

export const facilityMaintainingCoRoute: Routes = [
    {
        path: 'facility-maintaining-co',
        component: FacilityMaintainingCoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FacilityMaintainingCos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'facility-maintaining-co/:id/view',
        component: FacilityMaintainingCoDetailComponent,
        resolve: {
            facilityMaintainingCo: FacilityMaintainingCoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FacilityMaintainingCos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'facility-maintaining-co/new',
        component: FacilityMaintainingCoUpdateComponent,
        resolve: {
            facilityMaintainingCo: FacilityMaintainingCoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FacilityMaintainingCos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'facility-maintaining-co/:id/edit',
        component: FacilityMaintainingCoUpdateComponent,
        resolve: {
            facilityMaintainingCo: FacilityMaintainingCoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FacilityMaintainingCos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const facilityMaintainingCoPopupRoute: Routes = [
    {
        path: 'facility-maintaining-co/:id/delete',
        component: FacilityMaintainingCoDeletePopupComponent,
        resolve: {
            facilityMaintainingCo: FacilityMaintainingCoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FacilityMaintainingCos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
