import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CmtType } from 'app/shared/model/cmt-type.model';
import { CmtTypeService } from './cmt-type.service';
import { CmtTypeComponent } from './cmt-type.component';
import { CmtTypeDetailComponent } from './cmt-type-detail.component';
import { CmtTypeUpdateComponent } from './cmt-type-update.component';
import { CmtTypeDeletePopupComponent } from './cmt-type-delete-dialog.component';
import { ICmtType } from 'app/shared/model/cmt-type.model';

@Injectable({ providedIn: 'root' })
export class CmtTypeResolve implements Resolve<ICmtType> {
    constructor(private service: CmtTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((cmtType: HttpResponse<CmtType>) => cmtType.body));
        }
        return of(new CmtType());
    }
}

export const cmtTypeRoute: Routes = [
    {
        path: 'cmt-type',
        component: CmtTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CmtTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cmt-type/:id/view',
        component: CmtTypeDetailComponent,
        resolve: {
            cmtType: CmtTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CmtTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cmt-type/new',
        component: CmtTypeUpdateComponent,
        resolve: {
            cmtType: CmtTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CmtTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cmt-type/:id/edit',
        component: CmtTypeUpdateComponent,
        resolve: {
            cmtType: CmtTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CmtTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cmtTypePopupRoute: Routes = [
    {
        path: 'cmt-type/:id/delete',
        component: CmtTypeDeletePopupComponent,
        resolve: {
            cmtType: CmtTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CmtTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
