import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Substation } from 'app/shared/model/substation.model';
import { SubstationService } from './substation.service';
import { SubstationComponent } from './substation.component';
import { SubstationDetailComponent } from './substation-detail.component';
import { SubstationUpdateComponent } from './substation-update.component';
import { SubstationDeletePopupComponent } from './substation-delete-dialog.component';
import { ISubstation } from 'app/shared/model/substation.model';

@Injectable({ providedIn: 'root' })
export class SubstationResolve implements Resolve<ISubstation> {
    constructor(private service: SubstationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((substation: HttpResponse<Substation>) => substation.body));
        }
        return of(new Substation());
    }
}

export const substationRoute: Routes = [
    {
        path: 'substation',
        component: SubstationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Substations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'substation/:id/view',
        component: SubstationDetailComponent,
        resolve: {
            substation: SubstationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Substations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'substation/new',
        component: SubstationUpdateComponent,
        resolve: {
            substation: SubstationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Substations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'substation/:id/edit',
        component: SubstationUpdateComponent,
        resolve: {
            substation: SubstationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Substations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const substationPopupRoute: Routes = [
    {
        path: 'substation/:id/delete',
        component: SubstationDeletePopupComponent,
        resolve: {
            substation: SubstationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Substations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
