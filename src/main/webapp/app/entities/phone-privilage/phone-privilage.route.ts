import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PhonePrivilage } from 'app/shared/model/phone-privilage.model';
import { PhonePrivilageService } from './phone-privilage.service';
import { PhonePrivilageComponent } from './phone-privilage.component';
import { PhonePrivilageDetailComponent } from './phone-privilage-detail.component';
import { PhonePrivilageUpdateComponent } from './phone-privilage-update.component';
import { PhonePrivilageDeletePopupComponent } from './phone-privilage-delete-dialog.component';
import { IPhonePrivilage } from 'app/shared/model/phone-privilage.model';

@Injectable({ providedIn: 'root' })
export class PhonePrivilageResolve implements Resolve<IPhonePrivilage> {
    constructor(private service: PhonePrivilageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((phonePrivilage: HttpResponse<PhonePrivilage>) => phonePrivilage.body));
        }
        return of(new PhonePrivilage());
    }
}

export const phonePrivilageRoute: Routes = [
    {
        path: 'phone-privilage',
        component: PhonePrivilageComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PhonePrivilages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'phone-privilage/:id/view',
        component: PhonePrivilageDetailComponent,
        resolve: {
            phonePrivilage: PhonePrivilageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PhonePrivilages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'phone-privilage/new',
        component: PhonePrivilageUpdateComponent,
        resolve: {
            phonePrivilage: PhonePrivilageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PhonePrivilages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'phone-privilage/:id/edit',
        component: PhonePrivilageUpdateComponent,
        resolve: {
            phonePrivilage: PhonePrivilageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PhonePrivilages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const phonePrivilagePopupRoute: Routes = [
    {
        path: 'phone-privilage/:id/delete',
        component: PhonePrivilageDeletePopupComponent,
        resolve: {
            phonePrivilage: PhonePrivilageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PhonePrivilages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
