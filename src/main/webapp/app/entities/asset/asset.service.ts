import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAsset } from 'app/shared/model/asset.model';

type EntityResponseType = HttpResponse<IAsset>;
type EntityArrayResponseType = HttpResponse<IAsset[]>;

@Injectable({ providedIn: 'root' })
export class AssetService {
    private resourceUrl = SERVER_API_URL + 'api/assets';

    constructor(private http: HttpClient) {}

    create(asset: IAsset): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(asset);
        return this.http
            .post<IAsset>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(asset: IAsset): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(asset);
        return this.http
            .put<IAsset>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAsset>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAsset[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(asset: IAsset): IAsset {
        const copy: IAsset = Object.assign({}, asset, {
            dateOfObligation:
                asset.dateOfObligation != null && asset.dateOfObligation.isValid() ? asset.dateOfObligation.format(DATE_FORMAT) : null,
            purchasedDate: asset.purchasedDate != null && asset.purchasedDate.isValid() ? asset.purchasedDate.format(DATE_FORMAT) : null,
            inServiceDate: asset.inServiceDate != null && asset.inServiceDate.isValid() ? asset.inServiceDate.format(DATE_FORMAT) : null,
            warrenty: asset.warrenty != null && asset.warrenty.isValid() ? asset.warrenty.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dateOfObligation = res.body.dateOfObligation != null ? moment(res.body.dateOfObligation) : null;
        res.body.purchasedDate = res.body.purchasedDate != null ? moment(res.body.purchasedDate) : null;
        res.body.inServiceDate = res.body.inServiceDate != null ? moment(res.body.inServiceDate) : null;
        res.body.warrenty = res.body.warrenty != null ? moment(res.body.warrenty) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((asset: IAsset) => {
            asset.dateOfObligation = asset.dateOfObligation != null ? moment(asset.dateOfObligation) : null;
            asset.purchasedDate = asset.purchasedDate != null ? moment(asset.purchasedDate) : null;
            asset.inServiceDate = asset.inServiceDate != null ? moment(asset.inServiceDate) : null;
            asset.warrenty = asset.warrenty != null ? moment(asset.warrenty) : null;
        });
        return res;
    }
}
