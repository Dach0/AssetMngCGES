import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISurgeArresterType } from 'app/shared/model/surge-arrester-type.model';

type EntityResponseType = HttpResponse<ISurgeArresterType>;
type EntityArrayResponseType = HttpResponse<ISurgeArresterType[]>;

@Injectable({ providedIn: 'root' })
export class SurgeArresterTypeService {
    private resourceUrl = SERVER_API_URL + 'api/surge-arrester-types';

    constructor(private http: HttpClient) {}

    create(surgeArresterType: ISurgeArresterType): Observable<EntityResponseType> {
        return this.http.post<ISurgeArresterType>(this.resourceUrl, surgeArresterType, { observe: 'response' });
    }

    update(surgeArresterType: ISurgeArresterType): Observable<EntityResponseType> {
        return this.http.put<ISurgeArresterType>(this.resourceUrl, surgeArresterType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISurgeArresterType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISurgeArresterType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
