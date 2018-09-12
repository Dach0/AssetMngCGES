import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICorrectionFactor } from 'app/shared/model/correction-factor.model';

type EntityResponseType = HttpResponse<ICorrectionFactor>;
type EntityArrayResponseType = HttpResponse<ICorrectionFactor[]>;

@Injectable({ providedIn: 'root' })
export class CorrectionFactorService {
    private resourceUrl = SERVER_API_URL + 'api/correction-factors';

    constructor(private http: HttpClient) {}

    create(correctionFactor: ICorrectionFactor): Observable<EntityResponseType> {
        return this.http.post<ICorrectionFactor>(this.resourceUrl, correctionFactor, { observe: 'response' });
    }

    update(correctionFactor: ICorrectionFactor): Observable<EntityResponseType> {
        return this.http.put<ICorrectionFactor>(this.resourceUrl, correctionFactor, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICorrectionFactor>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICorrectionFactor[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
