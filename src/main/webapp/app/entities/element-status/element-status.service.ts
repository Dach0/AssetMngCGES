import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IElementStatus } from 'app/shared/model/element-status.model';

type EntityResponseType = HttpResponse<IElementStatus>;
type EntityArrayResponseType = HttpResponse<IElementStatus[]>;

@Injectable({ providedIn: 'root' })
export class ElementStatusService {
    private resourceUrl = SERVER_API_URL + 'api/element-statuses';

    constructor(private http: HttpClient) {}

    create(elementStatus: IElementStatus): Observable<EntityResponseType> {
        return this.http.post<IElementStatus>(this.resourceUrl, elementStatus, { observe: 'response' });
    }

    update(elementStatus: IElementStatus): Observable<EntityResponseType> {
        return this.http.put<IElementStatus>(this.resourceUrl, elementStatus, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IElementStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IElementStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
