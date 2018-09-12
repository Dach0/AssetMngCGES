import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITconnection } from 'app/shared/model/tconnection.model';

type EntityResponseType = HttpResponse<ITconnection>;
type EntityArrayResponseType = HttpResponse<ITconnection[]>;

@Injectable({ providedIn: 'root' })
export class TconnectionService {
    private resourceUrl = SERVER_API_URL + 'api/tconnections';

    constructor(private http: HttpClient) {}

    create(tconnection: ITconnection): Observable<EntityResponseType> {
        return this.http.post<ITconnection>(this.resourceUrl, tconnection, { observe: 'response' });
    }

    update(tconnection: ITconnection): Observable<EntityResponseType> {
        return this.http.put<ITconnection>(this.resourceUrl, tconnection, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITconnection>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITconnection[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
