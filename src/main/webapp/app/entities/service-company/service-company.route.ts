import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ServiceCompany } from 'app/shared/model/service-company.model';
import { ServiceCompanyService } from './service-company.service';
import { ServiceCompanyComponent } from './service-company.component';
import { ServiceCompanyDetailComponent } from './service-company-detail.component';
import { ServiceCompanyUpdateComponent } from './service-company-update.component';
import { ServiceCompanyDeletePopupComponent } from './service-company-delete-dialog.component';
import { IServiceCompany } from 'app/shared/model/service-company.model';

@Injectable({ providedIn: 'root' })
export class ServiceCompanyResolve implements Resolve<IServiceCompany> {
    constructor(private service: ServiceCompanyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((serviceCompany: HttpResponse<ServiceCompany>) => serviceCompany.body));
        }
        return of(new ServiceCompany());
    }
}

export const serviceCompanyRoute: Routes = [
    {
        path: 'service-company',
        component: ServiceCompanyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceCompanies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-company/:id/view',
        component: ServiceCompanyDetailComponent,
        resolve: {
            serviceCompany: ServiceCompanyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceCompanies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-company/new',
        component: ServiceCompanyUpdateComponent,
        resolve: {
            serviceCompany: ServiceCompanyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceCompanies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-company/:id/edit',
        component: ServiceCompanyUpdateComponent,
        resolve: {
            serviceCompany: ServiceCompanyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceCompanies'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const serviceCompanyPopupRoute: Routes = [
    {
        path: 'service-company/:id/delete',
        component: ServiceCompanyDeletePopupComponent,
        resolve: {
            serviceCompany: ServiceCompanyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ServiceCompanies'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
