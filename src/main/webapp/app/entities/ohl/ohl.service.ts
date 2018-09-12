import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOhl } from 'app/shared/model/ohl.model';

type EntityResponseType = HttpResponse<IOhl>;
type EntityArrayResponseType = HttpResponse<IOhl[]>;

@Injectable({ providedIn: 'root' })
export class OhlService {
    private resourceUrl = SERVER_API_URL + 'api/ohls';

    constructor(private http: HttpClient) {}

    create(ohl: IOhl): Observable<EntityResponseType> {
        return this.http.post<IOhl>(this.resourceUrl, ohl, { observe: 'response' });
    }

    update(ohl: IOhl): Observable<EntityResponseType> {
        return this.http.put<IOhl>(this.resourceUrl, ohl, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOhl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOhl[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
