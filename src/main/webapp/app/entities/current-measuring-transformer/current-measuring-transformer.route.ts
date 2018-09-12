import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';
import { CurrentMeasuringTransformerService } from './current-measuring-transformer.service';
import { CurrentMeasuringTransformerComponent } from './current-measuring-transformer.component';
import { CurrentMeasuringTransformerDetailComponent } from './current-measuring-transformer-detail.component';
import { CurrentMeasuringTransformerUpdateComponent } from './current-measuring-transformer-update.component';
import { CurrentMeasuringTransformerDeletePopupComponent } from './current-measuring-transformer-delete-dialog.component';
import { ICurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';

@Injectable({ providedIn: 'root' })
export class CurrentMeasuringTransformerResolve implements Resolve<ICurrentMeasuringTransformer> {
    constructor(private service: CurrentMeasuringTransformerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((currentMeasuringTransformer: HttpResponse<CurrentMeasuringTransformer>) => currentMeasuringTransformer.body));
        }
        return of(new CurrentMeasuringTransformer());
    }
}

export const currentMeasuringTransformerRoute: Routes = [
    {
        path: 'current-measuring-transformer',
        component: CurrentMeasuringTransformerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CurrentMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'current-measuring-transformer/:id/view',
        component: CurrentMeasuringTransformerDetailComponent,
        resolve: {
            currentMeasuringTransformer: CurrentMeasuringTransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CurrentMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'current-measuring-transformer/new',
        component: CurrentMeasuringTransformerUpdateComponent,
        resolve: {
            currentMeasuringTransformer: CurrentMeasuringTransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CurrentMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'current-measuring-transformer/:id/edit',
        component: CurrentMeasuringTransformerUpdateComponent,
        resolve: {
            currentMeasuringTransformer: CurrentMeasuringTransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CurrentMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const currentMeasuringTransformerPopupRoute: Routes = [
    {
        path: 'current-measuring-transformer/:id/delete',
        component: CurrentMeasuringTransformerDeletePopupComponent,
        resolve: {
            currentMeasuringTransformer: CurrentMeasuringTransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CurrentMeasuringTransformers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
