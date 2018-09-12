import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { VmtType } from 'app/shared/model/vmt-type.model';
import { VmtTypeService } from './vmt-type.service';
import { VmtTypeComponent } from './vmt-type.component';
import { VmtTypeDetailComponent } from './vmt-type-detail.component';
import { VmtTypeUpdateComponent } from './vmt-type-update.component';
import { VmtTypeDeletePopupComponent } from './vmt-type-delete-dialog.component';
import { IVmtType } from 'app/shared/model/vmt-type.model';

@Injectable({ providedIn: 'root' })
export class VmtTypeResolve implements Resolve<IVmtType> {
    constructor(private service: VmtTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((vmtType: HttpResponse<VmtType>) => vmtType.body));
        }
        return of(new VmtType());
    }
}

export const vmtTypeRoute: Routes = [
    {
        path: 'vmt-type',
        component: VmtTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VmtTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vmt-type/:id/view',
        component: VmtTypeDetailComponent,
        resolve: {
            vmtType: VmtTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VmtTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vmt-type/new',
        component: VmtTypeUpdateComponent,
        resolve: {
            vmtType: VmtTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VmtTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vmt-type/:id/edit',
        component: VmtTypeUpdateComponent,
        resolve: {
            vmtType: VmtTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VmtTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vmtTypePopupRoute: Routes = [
    {
        path: 'vmt-type/:id/delete',
        component: VmtTypeDeletePopupComponent,
        resolve: {
            vmtType: VmtTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VmtTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
