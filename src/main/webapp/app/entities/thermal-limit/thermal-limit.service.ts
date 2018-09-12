import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IThermalLimit } from 'app/shared/model/thermal-limit.model';

type EntityResponseType = HttpResponse<IThermalLimit>;
type EntityArrayResponseType = HttpResponse<IThermalLimit[]>;

@Injectable({ providedIn: 'root' })
export class ThermalLimitService {
    private resourceUrl = SERVER_API_URL + 'api/thermal-limits';

    constructor(private http: HttpClient) {}

    create(thermalLimit: IThermalLimit): Observable<EntityResponseType> {
        return this.http.post<IThermalLimit>(this.resourceUrl, thermalLimit, { observe: 'response' });
    }

    update(thermalLimit: IThermalLimit): Observable<EntityResponseType> {
        return this.http.put<IThermalLimit>(this.resourceUrl, thermalLimit, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IThermalLimit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IThermalLimit[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
