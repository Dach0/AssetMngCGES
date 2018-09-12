import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SurgeArrester } from 'app/shared/model/surge-arrester.model';
import { SurgeArresterService } from './surge-arrester.service';
import { SurgeArresterComponent } from './surge-arrester.component';
import { SurgeArresterDetailComponent } from './surge-arrester-detail.component';
import { SurgeArresterUpdateComponent } from './surge-arrester-update.component';
import { SurgeArresterDeletePopupComponent } from './surge-arrester-delete-dialog.component';
import { ISurgeArrester } from 'app/shared/model/surge-arrester.model';

@Injectable({ providedIn: 'root' })
export class SurgeArresterResolve implements Resolve<ISurgeArrester> {
    constructor(private service: SurgeArresterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((surgeArrester: HttpResponse<SurgeArrester>) => surgeArrester.body));
        }
        return of(new SurgeArrester());
    }
}

export const surgeArresterRoute: Routes = [
    {
        path: 'surge-arrester',
        component: SurgeArresterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'surge-arrester/:id/view',
        component: SurgeArresterDetailComponent,
        resolve: {
            surgeArrester: SurgeArresterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'surge-arrester/new',
        component: SurgeArresterUpdateComponent,
        resolve: {
            surgeArrester: SurgeArresterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'surge-arrester/:id/edit',
        component: SurgeArresterUpdateComponent,
        resolve: {
            surgeArrester: SurgeArresterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresters'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const surgeArresterPopupRoute: Routes = [
    {
        path: 'surge-arrester/:id/delete',
        component: SurgeArresterDeletePopupComponent,
        resolve: {
            surgeArrester: SurgeArresterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SurgeArresters'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
