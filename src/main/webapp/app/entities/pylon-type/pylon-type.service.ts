import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPylonType } from 'app/shared/model/pylon-type.model';

type EntityResponseType = HttpResponse<IPylonType>;
type EntityArrayResponseType = HttpResponse<IPylonType[]>;

@Injectable({ providedIn: 'root' })
export class PylonTypeService {
    private resourceUrl = SERVER_API_URL + 'api/pylon-types';

    constructor(private http: HttpClient) {}

    create(pylonType: IPylonType): Observable<EntityResponseType> {
        return this.http.post<IPylonType>(this.resourceUrl, pylonType, { observe: 'response' });
    }

    update(pylonType: IPylonType): Observable<EntityResponseType> {
        return this.http.put<IPylonType>(this.resourceUrl, pylonType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPylonType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPylonType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
