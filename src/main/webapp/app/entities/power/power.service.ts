import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPower } from 'app/shared/model/power.model';

type EntityResponseType = HttpResponse<IPower>;
type EntityArrayResponseType = HttpResponse<IPower[]>;

@Injectable({ providedIn: 'root' })
export class PowerService {
    private resourceUrl = SERVER_API_URL + 'api/powers';

    constructor(private http: HttpClient) {}

    create(power: IPower): Observable<EntityResponseType> {
        return this.http.post<IPower>(this.resourceUrl, power, { observe: 'response' });
    }

    update(power: IPower): Observable<EntityResponseType> {
        return this.http.put<IPower>(this.resourceUrl, power, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPower>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPower[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
