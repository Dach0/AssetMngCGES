import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CircuitBreaker } from 'app/shared/model/circuit-breaker.model';
import { CircuitBreakerService } from './circuit-breaker.service';
import { CircuitBreakerComponent } from './circuit-breaker.component';
import { CircuitBreakerDetailComponent } from './circuit-breaker-detail.component';
import { CircuitBreakerUpdateComponent } from './circuit-breaker-update.component';
import { CircuitBreakerDeletePopupComponent } from './circuit-breaker-delete-dialog.component';
import { ICircuitBreaker } from 'app/shared/model/circuit-breaker.model';

@Injectable({ providedIn: 'root' })
export class CircuitBreakerResolve implements Resolve<ICircuitBreaker> {
    constructor(private service: CircuitBreakerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((circuitBreaker: HttpResponse<CircuitBreaker>) => circuitBreaker.body));
        }
        return of(new CircuitBreaker());
    }
}

export const circuitBreakerRoute: Routes = [
    {
        path: 'circuit-breaker',
        component: CircuitBreakerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker/:id/view',
        component: CircuitBreakerDetailComponent,
        resolve: {
            circuitBreaker: CircuitBreakerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker/new',
        component: CircuitBreakerUpdateComponent,
        resolve: {
            circuitBreaker: CircuitBreakerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circuit-breaker/:id/edit',
        component: CircuitBreakerUpdateComponent,
        resolve: {
            circuitBreaker: CircuitBreakerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const circuitBreakerPopupRoute: Routes = [
    {
        path: 'circuit-breaker/:id/delete',
        component: CircuitBreakerDeletePopupComponent,
        resolve: {
            circuitBreaker: CircuitBreakerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CircuitBreakers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
