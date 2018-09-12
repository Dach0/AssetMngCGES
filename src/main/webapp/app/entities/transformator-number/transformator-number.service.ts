import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITransformatorNumber } from 'app/shared/model/transformator-number.model';

type EntityResponseType = HttpResponse<ITransformatorNumber>;
type EntityArrayResponseType = HttpResponse<ITransformatorNumber[]>;

@Injectable({ providedIn: 'root' })
export class TransformatorNumberService {
    private resourceUrl = SERVER_API_URL + 'api/transformator-numbers';

    constructor(private http: HttpClient) {}

    create(transformatorNumber: ITransformatorNumber): Observable<EntityResponseType> {
        return this.http.post<ITransformatorNumber>(this.resourceUrl, transformatorNumber, { observe: 'response' });
    }

    update(transformatorNumber: ITransformatorNumber): Observable<EntityResponseType> {
        return this.http.put<ITransformatorNumber>(this.resourceUrl, transformatorNumber, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITransformatorNumber>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITransformatorNumber[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
