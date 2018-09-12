import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISurgeArrester } from 'app/shared/model/surge-arrester.model';

type EntityResponseType = HttpResponse<ISurgeArrester>;
type EntityArrayResponseType = HttpResponse<ISurgeArrester[]>;

@Injectable({ providedIn: 'root' })
export class SurgeArresterService {
    private resourceUrl = SERVER_API_URL + 'api/surge-arresters';

    constructor(private http: HttpClient) {}

    create(surgeArrester: ISurgeArrester): Observable<EntityResponseType> {
        return this.http.post<ISurgeArrester>(this.resourceUrl, surgeArrester, { observe: 'response' });
    }

    update(surgeArrester: ISurgeArrester): Observable<EntityResponseType> {
        return this.http.put<ISurgeArrester>(this.resourceUrl, surgeArrester, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISurgeArrester>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISurgeArrester[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
