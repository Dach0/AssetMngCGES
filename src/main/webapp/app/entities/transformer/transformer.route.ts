import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Transformer } from 'app/shared/model/transformer.model';
import { TransformerService } from './transformer.service';
import { TransformerComponent } from './transformer.component';
import { TransformerDetailComponent } from './transformer-detail.component';
import { TransformerUpdateComponent } from './transformer-update.component';
import { TransformerDeletePopupComponent } from './transformer-delete-dialog.component';
import { ITransformer } from 'app/shared/model/transformer.model';

@Injectable({ providedIn: 'root' })
export class TransformerResolve implements Resolve<ITransformer> {
    constructor(private service: TransformerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((transformer: HttpResponse<Transformer>) => transformer.body));
        }
        return of(new Transformer());
    }
}

export const transformerRoute: Routes = [
    {
        path: 'transformer',
        component: TransformerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformer/:id/view',
        component: TransformerDetailComponent,
        resolve: {
            transformer: TransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformer/new',
        component: TransformerUpdateComponent,
        resolve: {
            transformer: TransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transformers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transformer/:id/edit',
        component: TransformerUpdateComponent,
        resolve: {
            transformer: TransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transformers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transformerPopupRoute: Routes = [
    {
        path: 'transformer/:id/delete',
        component: TransformerDeletePopupComponent,
        resolve: {
            transformer: TransformerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transformers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
