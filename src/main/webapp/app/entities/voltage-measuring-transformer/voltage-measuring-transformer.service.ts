import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVoltageMeasuringTransformer } from 'app/shared/model/voltage-measuring-transformer.model';

type EntityResponseType = HttpResponse<IVoltageMeasuringTransformer>;
type EntityArrayResponseType = HttpResponse<IVoltageMeasuringTransformer[]>;

@Injectable({ providedIn: 'root' })
export class VoltageMeasuringTransformerService {
    private resourceUrl = SERVER_API_URL + 'api/voltage-measuring-transformers';

    constructor(private http: HttpClient) {}

    create(voltageMeasuringTransformer: IVoltageMeasuringTransformer): Observable<EntityResponseType> {
        return this.http.post<IVoltageMeasuringTransformer>(this.resourceUrl, voltageMeasuringTransformer, { observe: 'response' });
    }

    update(voltageMeasuringTransformer: IVoltageMeasuringTransformer): Observable<EntityResponseType> {
        return this.http.put<IVoltageMeasuringTransformer>(this.resourceUrl, voltageMeasuringTransformer, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVoltageMeasuringTransformer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVoltageMeasuringTransformer[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
