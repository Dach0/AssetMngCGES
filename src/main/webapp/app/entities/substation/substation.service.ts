import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISubstation } from 'app/shared/model/substation.model';

type EntityResponseType = HttpResponse<ISubstation>;
type EntityArrayResponseType = HttpResponse<ISubstation[]>;

@Injectable({ providedIn: 'root' })
export class SubstationService {
    private resourceUrl = SERVER_API_URL + 'api/substations';

    constructor(private http: HttpClient) {}

    create(substation: ISubstation): Observable<EntityResponseType> {
        return this.http.post<ISubstation>(this.resourceUrl, substation, { observe: 'response' });
    }

    update(substation: ISubstation): Observable<EntityResponseType> {
        return this.http.put<ISubstation>(this.resourceUrl, substation, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISubstation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISubstation[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
