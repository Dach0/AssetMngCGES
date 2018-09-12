import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { VoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';
import { VoltageMeasuringTransformerService } from './voltage-measuring-transformer.service';
import { VoltageMeasuringTransformerComponent } from './voltage-measuring-transformer.component';
import { VoltageMeasuringTransformerDetailComponent } from './voltage-measuring-transformer-detail.component';
import { VoltageMeasuringTransformerUpdateComponent } from './voltage-measuring-transformer-update.component';
import { VoltageMeasuringTransformerDeletePopupComponent } from './voltage-measuring-transformer-delete-dialog.component';
import { IVoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';

@Injectable({ providedIn: 'root' })
export class VoltageMeasuringTransformerResolve implements Resolve<IVoltageMeasuringTransformer> {
    constructor(private service: VoltageMeasuringTransformerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((voltageMeasuringTransformer: HttpResponse<VoltageMeasuringTransformer>) => voltageMeasuringTransformer.body));
        }
        return of(new VoltageMeasuringTransformer());
    }
}

export const voltageMeasuringTransformerRoute: Routes = [
    {
        path: 'voltage-measuring-transformer',
        component: VoltageMeasuringTransformerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'voltage-measuring-transformer/:id/view',
        component: VoltageMeasuringTransformerDetailComponent,
        resolve: {
            voltageMeasuringTransformer: VoltageMeasuringTransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'voltage-measuring-transformer/new',
        component: VoltageMeasuringTransformerUpdateComponent,
        resolve: {
            voltageMeasuringTransformer: VoltageMeasuringTransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'voltage-measuring-transformer/:id/edit',
        component: VoltageMeasuringTransformerUpdateComponent,
        resolve: {
            voltageMeasuringTransformer: VoltageMeasuringTransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const voltageMeasuringTransformerPopupRoute: Routes = [
    {
        path: 'voltage-measuring-transformer/:id/delete',
        component: VoltageMeasuringTransformerDeletePopupComponent,
        resolve: {
            voltageMeasuringTransformer: VoltageMeasuringTransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VoltageMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
