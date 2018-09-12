import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConductorCrossSect } from 'app/shared/model/conductor-cross-sect.model';

type EntityResponseType = HttpResponse<IConductorCrossSect>;
type EntityArrayResponseType = HttpResponse<IConductorCrossSect[]>;

@Injectable({ providedIn: 'root' })
export class ConductorCrossSectService {
    private resourceUrl = SERVER_API_URL + 'api/conductor-cross-sects';

    constructor(private http: HttpClient) {}

    create(conductorCrossSect: IConductorCrossSect): Observable<EntityResponseType> {
        return this.http.post<IConductorCrossSect>(this.resourceUrl, conductorCrossSect, { observe: 'response' });
    }

    update(conductorCrossSect: IConductorCrossSect): Observable<EntityResponseType> {
        return this.http.put<IConductorCrossSect>(this.resourceUrl, conductorCrossSect, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IConductorCrossSect>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IConductorCrossSect[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
