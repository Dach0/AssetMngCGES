import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ElementCondition } from 'app/shared/model/element-condition.model';
import { ElementConditionService } from './element-condition.service';
import { ElementConditionComponent } from './element-condition.component';
import { ElementConditionDetailComponent } from './element-condition-detail.component';
import { ElementConditionUpdateComponent } from './element-condition-update.component';
import { ElementConditionDeletePopupComponent } from './element-condition-delete-dialog.component';
import { IElementCondition } from 'app/shared/model/element-condition.model';

@Injectable({ providedIn: 'root' })
export class ElementConditionResolve implements Resolve<IElementCondition> {
    constructor(private service: ElementConditionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((elementCondition: HttpResponse<ElementCondition>) => elementCondition.body));
        }
        return of(new ElementCondition());
    }
}

export const elementConditionRoute: Routes = [
    {
        path: 'element-condition',
        component: ElementConditionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementConditions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'element-condition/:id/view',
        component: ElementConditionDetailComponent,
        resolve: {
            elementCondition: ElementConditionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementConditions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'element-condition/new',
        component: ElementConditionUpdateComponent,
        resolve: {
            elementCondition: ElementConditionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementConditions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'element-condition/:id/edit',
        component: ElementConditionUpdateComponent,
        resolve: {
            elementCondition: ElementConditionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementConditions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const elementConditionPopupRoute: Routes = [
    {
        path: 'element-condition/:id/delete',
        component: ElementConditionDeletePopupComponent,
        resolve: {
            elementCondition: ElementConditionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ElementConditions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
