import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GroundingSticks } from 'app/shared/model/grounding-sticks.model';
import { GroundingSticksService } from './grounding-sticks.service';
import { GroundingSticksComponent } from './grounding-sticks.component';
import { GroundingSticksDetailComponent } from './grounding-sticks-detail.component';
import { GroundingSticksUpdateComponent } from './grounding-sticks-update.component';
import { GroundingSticksDeletePopupComponent } from './grounding-sticks-delete-dialog.component';
import { IGroundingSticks } from 'app/shared/model/grounding-sticks.model';

@Injectable({ providedIn: 'root' })
export class GroundingSticksResolve implements Resolve<IGroundingSticks> {
    constructor(private service: GroundingSticksService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((groundingSticks: HttpResponse<GroundingSticks>) => groundingSticks.body));
        }
        return of(new GroundingSticks());
    }
}

export const groundingSticksRoute: Routes = [
    {
        path: 'grounding-sticks',
        component: GroundingSticksComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundingSticks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'grounding-sticks/:id/view',
        component: GroundingSticksDetailComponent,
        resolve: {
            groundingSticks: GroundingSticksResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundingSticks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'grounding-sticks/new',
        component: GroundingSticksUpdateComponent,
        resolve: {
            groundingSticks: GroundingSticksResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundingSticks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'grounding-sticks/:id/edit',
        component: GroundingSticksUpdateComponent,
        resolve: {
            groundingSticks: GroundingSticksResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundingSticks'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const groundingSticksPopupRoute: Routes = [
    {
        path: 'grounding-sticks/:id/delete',
        component: GroundingSticksDeletePopupComponent,
        resolve: {
            groundingSticks: GroundingSticksResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroundingSticks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
