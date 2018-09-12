import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ServiceType } from 'app/shared/model/service-type.model';
import { ServiceTypeService } from './service-type.service';
import { ServiceTypeComponent } from './service-type.component';
import { ServiceTypeDetailComponent } from './service-type-detail.component';
import { ServiceTypeUpdateComponent } from './service-type-update.component';
import { ServiceTypeDeletePopupComponent } from './service-type-delete-dialog.component';
import { IServiceType } from 'app/shared/model/service-type.model';

@Injectable({ providedIn: 'root' })
export class ServiceTypeResolve implements Resolve<IServiceType> {
    constructor(private service: ServiceTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((serviceType: HttpResponse<ServiceType>) => serviceType.body));
        }
        return of(new ServiceType());
    }
}

export const serviceTypeRoute: Routes = [
    {
        path: 'service-type',
        component: ServiceTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-type/:id/view',
        component: ServiceTypeDetailComponent,
        resolve: {
            serviceType: ServiceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-type/new',
        component: ServiceTypeUpdateComponent,
        resolve: {
            serviceType: ServiceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-type/:id/edit',
        component: ServiceTypeUpdateComponent,
        resolve: {
            serviceType: ServiceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const serviceTypePopupRoute: Routes = [
    {
        path: 'service-type/:id/delete',
        component: ServiceTypeDeletePopupComponent,
        resolve: {
            serviceType: ServiceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
