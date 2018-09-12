import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAssetStatus } from 'app/shared/model/asset-status.model';

type EntityResponseType = HttpResponse<IAssetStatus>;
type EntityArrayResponseType = HttpResponse<IAssetStatus[]>;

@Injectable({ providedIn: 'root' })
export class AssetStatusService {
    private resourceUrl = SERVER_API_URL + 'api/asset-statuses';

    constructor(private http: HttpClient) {}

    create(assetStatus: IAssetStatus): Observable<EntityResponseType> {
        return this.http.post<IAssetStatus>(this.resourceUrl, assetStatus, { observe: 'response' });
    }

    update(assetStatus: IAssetStatus): Observable<EntityResponseType> {
        return this.http.put<IAssetStatus>(this.resourceUrl, assetStatus, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAssetStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAssetStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
