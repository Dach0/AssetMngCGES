import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAssetCondition } from 'app/shared/model/asset-condition.model';

type EntityResponseType = HttpResponse<IAssetCondition>;
type EntityArrayResponseType = HttpResponse<IAssetCondition[]>;

@Injectable({ providedIn: 'root' })
export class AssetConditionService {
    private resourceUrl = SERVER_API_URL + 'api/asset-conditions';

    constructor(private http: HttpClient) {}

    create(assetCondition: IAssetCondition): Observable<EntityResponseType> {
        return this.http.post<IAssetCondition>(this.resourceUrl, assetCondition, { observe: 'response' });
    }

    update(assetCondition: IAssetCondition): Observable<EntityResponseType> {
        return this.http.put<IAssetCondition>(this.resourceUrl, assetCondition, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAssetCondition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAssetCondition[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
