import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDisconnectorType } from 'app/shared/model/disconnector-type.model';

type EntityResponseType = HttpResponse<IDisconnectorType>;
type EntityArrayResponseType = HttpResponse<IDisconnectorType[]>;

@Injectable({ providedIn: 'root' })
export class DisconnectorTypeService {
    private resourceUrl = SERVER_API_URL + 'api/disconnector-types';

    constructor(private http: HttpClient) {}

    create(disconnectorType: IDisconnectorType): Observable<EntityResponseType> {
        return this.http.post<IDisconnectorType>(this.resourceUrl, disconnectorType, { observe: 'response' });
    }

    update(disconnectorType: IDisconnectorType): Observable<EntityResponseType> {
        return this.http.put<IDisconnectorType>(this.resourceUrl, disconnectorType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDisconnectorType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDisconnectorType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
