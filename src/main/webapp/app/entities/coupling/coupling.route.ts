import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Coupling } from 'app/shared/model/coupling.model';
import { CouplingService } from './coupling.service';
import { CouplingComponent } from './coupling.component';
import { CouplingDetailComponent } from './coupling-detail.component';
import { CouplingUpdateComponent } from './coupling-update.component';
import { CouplingDeletePopupComponent } from './coupling-delete-dialog.component';
import { ICoupling } from 'app/shared/model/coupling.model';

@Injectable({ providedIn: 'root' })
export class CouplingResolve implements Resolve<ICoupling> {
    constructor(private service: CouplingService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((coupling: HttpResponse<Coupling>) => coupling.body));
        }
        return of(new Coupling());
    }
}

export const couplingRoute: Routes = [
    {
        path: 'coupling',
        component: CouplingComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Couplings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'coupling/:id/view',
        component: CouplingDetailComponent,
        resolve: {
            coupling: CouplingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Couplings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'coupling/new',
        component: CouplingUpdateComponent,
        resolve: {
            coupling: CouplingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Couplings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'coupling/:id/edit',
        component: CouplingUpdateComponent,
        resolve: {
            coupling: CouplingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Couplings'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const couplingPopupRoute: Routes = [
    {
        path: 'coupling/:id/delete',
        component: CouplingDeletePopupComponent,
        resolve: {
            coupling: CouplingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Couplings'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
