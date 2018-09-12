import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';
import { CircuitBreakerTypeService } from './circuit-breaker-type.service';
import { CircuitBreakerTypeComponent } from './circuit-breaker-type.component';
import { CircuitBreakerTypeDetailComponent } from './circuit-breaker-type-detail.component';
import { CircuitBreakerTypeUpdateComponent } from './circuit-breaker-type-update.component';
import { CircuitBreakerTypeDeletePopupComponent } from './circuit-breaker-type-delete-dialog.component';
import { ICircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';

@Injectable({ providedIn: 'root' })
export class CircuitBreakerTypeResolve implements Resolve<ICircuitBreakerType> {
    constructor(private service: CircuitBreakerTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((circuitBreakerType: HttpResponse<CircuitBreakerType>) => circuitBreakerType.body));
        }
        return of(new CircuitBreakerType());
    }
}

export const circuitBreakerTypeRoute: Routes = [
    {
        path: 'circuit-breaker-type',
        component: CircuitBreakerTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker-type/:id/view',
        component: CircuitBreakerTypeDetailComponent,
        resolve: {
            circuitBreakerType: CircuitBreakerTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker-type/new',
        component: CircuitBreakerTypeUpdateComponent,
        resolve: {
            circuitBreakerType: CircuitBreakerTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker-type/:id/edit',
        component: CircuitBreakerTypeUpdateComponent,
        resolve: {
            circuitBreakerType: CircuitBreakerTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const circuitBreakerTypePopupRoute: Routes = [
    {
        path: 'circuit-breaker-type/:id/delete',
        component: CircuitBreakerTypeDeletePopupComponent,
        resolve: {
            circuitBreakerType: CircuitBreakerTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakerTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
