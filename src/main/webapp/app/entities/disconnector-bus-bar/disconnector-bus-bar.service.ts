import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';

type EntityResponseType = HttpResponse<IDisconnectorBusBar>;
type EntityArrayResponseType = HttpResponse<IDisconnectorBusBar[]>;

@Injectable({ providedIn: 'root' })
export class DisconnectorBusBarService {
    private resourceUrl = SERVER_API_URL + 'api/disconnector-bus-bars';

    constructor(private http: HttpClient) {}

    create(disconnectorBusBar: IDisconnectorBusBar): Observable<EntityResponseType> {
        return this.http.post<IDisconnectorBusBar>(this.resourceUrl, disconnectorBusBar, { observe: 'response' });
    }

    update(disconnectorBusBar: IDisconnectorBusBar): Observable<EntityResponseType> {
        return this.http.put<IDisconnectorBusBar>(this.resourceUrl, disconnectorBusBar, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDisconnectorBusBar>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDisconnectorBusBar[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
