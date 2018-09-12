import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICurrentMeasuringTransformer } from 'app/shared/model/current-measuring-transformer.model';

type EntityResponseType = HttpResponse<ICurrentMeasuringTransformer>;
type EntityArrayResponseType = HttpResponse<ICurrentMeasuringTransformer[]>;

@Injectable({ providedIn: 'root' })
export class CurrentMeasuringTransformerService {
    private resourceUrl = SERVER_API_URL + 'api/current-measuring-transformers';

    constructor(private http: HttpClient) {}

    create(currentMeasuringTransformer: ICurrentMeasuringTransformer): Observable<EntityResponseType> {
        return this.http.post<ICurrentMeasuringTransformer>(this.resourceUrl, currentMeasuringTransformer, { observe: 'response' });
    }

    update(currentMeasuringTransformer: ICurrentMeasuringTransformer): Observable<EntityResponseType> {
        return this.http.put<ICurrentMeasuringTransformer>(this.resourceUrl, currentMeasuringTransformer, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICurrentMeasuringTransformer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICurrentMeasuringTransformer[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
