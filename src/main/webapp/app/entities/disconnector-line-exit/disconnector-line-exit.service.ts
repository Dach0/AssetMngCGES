import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';

type EntityResponseType = HttpResponse<IDisconnectorLineExit>;
type EntityArrayResponseType = HttpResponse<IDisconnectorLineExit[]>;

@Injectable({ providedIn: 'root' })
export class DisconnectorLineExitService {
    private resourceUrl = SERVER_API_URL + 'api/disconnector-line-exits';

    constructor(private http: HttpClient) {}

    create(disconnectorLineExit: IDisconnectorLineExit): Observable<EntityResponseType> {
        return this.http.post<IDisconnectorLineExit>(this.resourceUrl, disconnectorLineExit, { observe: 'response' });
    }

    update(disconnectorLineExit: IDisconnectorLineExit): Observable<EntityResponseType> {
        return this.http.put<IDisconnectorLineExit>(this.resourceUrl, disconnectorLineExit, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDisconnectorLineExit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDisconnectorLineExit[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
