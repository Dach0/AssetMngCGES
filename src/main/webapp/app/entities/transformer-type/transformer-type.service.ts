import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITransformerType } from 'app/shared/model/transformer-type.model';

type EntityResponseType = HttpResponse<ITransformerType>;
type EntityArrayResponseType = HttpResponse<ITransformerType[]>;

@Injectable({ providedIn: 'root' })
export class TransformerTypeService {
    private resourceUrl = SERVER_API_URL + 'api/transformer-types';

    constructor(private http: HttpClient) {}

    create(transformerType: ITransformerType): Observable<EntityResponseType> {
        return this.http.post<ITransformerType>(this.resourceUrl, transformerType, { observe: 'response' });
    }

    update(transformerType: ITransformerType): Observable<EntityResponseType> {
        return this.http.put<ITransformerType>(this.resourceUrl, transformerType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITransformerType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITransformerType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
