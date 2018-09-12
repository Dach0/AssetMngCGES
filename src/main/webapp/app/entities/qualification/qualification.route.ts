import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Qualification } from 'app/shared/model/qualification.model';
import { QualificationService } from './qualification.service';
import { QualificationComponent } from './qualification.component';
import { QualificationDetailComponent } from './qualification-detail.component';
import { QualificationUpdateComponent } from './qualification-update.component';
import { QualificationDeletePopupComponent } from './qualification-delete-dialog.component';
import { IQualification } from 'app/shared/model/qualification.model';

@Injectable({ providedIn: 'root' })
export class QualificationResolve implements Resolve<IQualification> {
    constructor(private service: QualificationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((qualification: HttpResponse<Qualification>) => qualification.body));
        }
        return of(new Qualification());
    }
}

export const qualificationRoute: Routes = [
    {
        path: 'qualification',
        component: QualificationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Qualifications'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'qualification/:id/view',
        component: QualificationDetailComponent,
        resolve: {
            qualification: QualificationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Qualifications'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'qualification/new',
        component: QualificationUpdateComponent,
        resolve: {
            qualification: QualificationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Qualifications'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'qualification/:id/edit',
        component: QualificationUpdateComponent,
        resolve: {
            qualification: QualificationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Qualifications'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const qualificationPopupRoute: Routes = [
    {
        path: 'qualification/:id/delete',
        component: QualificationDeletePopupComponent,
        resolve: {
            qualification: QualificationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Qualifications'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
