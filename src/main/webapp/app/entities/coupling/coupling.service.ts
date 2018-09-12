import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICoupling } from 'app/shared/model/coupling.model';

type EntityResponseType = HttpResponse<ICoupling>;
type EntityArrayResponseType = HttpResponse<ICoupling[]>;

@Injectable({ providedIn: 'root' })
export class CouplingService {
    private resourceUrl = SERVER_API_URL + 'api/couplings';

    constructor(private http: HttpClient) {}

    create(coupling: ICoupling): Observable<EntityResponseType> {
        return this.http.post<ICoupling>(this.resourceUrl, coupling, { observe: 'response' });
    }

    update(coupling: ICoupling): Observable<EntityResponseType> {
        return this.http.put<ICoupling>(this.resourceUrl, coupling, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICoupling>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICoupling[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
