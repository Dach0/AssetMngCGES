import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IElementCondition } from 'app/shared/model/element-condition.model';

type EntityResponseType = HttpResponse<IElementCondition>;
type EntityArrayResponseType = HttpResponse<IElementCondition[]>;

@Injectable({ providedIn: 'root' })
export class ElementConditionService {
    private resourceUrl = SERVER_API_URL + 'api/element-conditions';

    constructor(private http: HttpClient) {}

    create(elementCondition: IElementCondition): Observable<EntityResponseType> {
        return this.http.post<IElementCondition>(this.resourceUrl, elementCondition, { observe: 'response' });
    }

    update(elementCondition: IElementCondition): Observable<EntityResponseType> {
        return this.http.put<IElementCondition>(this.resourceUrl, elementCondition, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IElementCondition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IElementCondition[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
