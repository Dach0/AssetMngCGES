import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EmployeeGroup } from 'app/shared/model/employee-group.model';
import { EmployeeGroupService } from './employee-group.service';
import { EmployeeGroupComponent } from './employee-group.component';
import { EmployeeGroupDetailComponent } from './employee-group-detail.component';
import { EmployeeGroupUpdateComponent } from './employee-group-update.component';
import { EmployeeGroupDeletePopupComponent } from './employee-group-delete-dialog.component';
import { IEmployeeGroup } from 'app/shared/model/employee-group.model';

@Injectable({ providedIn: 'root' })
export class EmployeeGroupResolve implements Resolve<IEmployeeGroup> {
    constructor(private service: EmployeeGroupService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((employeeGroup: HttpResponse<EmployeeGroup>) => employeeGroup.body));
        }
        return of(new EmployeeGroup());
    }
}

export const employeeGroupRoute: Routes = [
    {
        path: 'employee-group',
        component: EmployeeGroupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeeGroups'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-group/:id/view',
        component: EmployeeGroupDetailComponent,
        resolve: {
            employeeGroup: EmployeeGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeeGroups'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-group/new',
        component: EmployeeGroupUpdateComponent,
        resolve: {
            employeeGroup: EmployeeGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeeGroups'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-group/:id/edit',
        component: EmployeeGroupUpdateComponent,
        resolve: {
            employeeGroup: EmployeeGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeeGroups'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const employeeGroupPopupRoute: Routes = [
    {
        path: 'employee-group/:id/delete',
        component: EmployeeGroupDeletePopupComponent,
        resolve: {
            employeeGroup: EmployeeGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeeGroups'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
