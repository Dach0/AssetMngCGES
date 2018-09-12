import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICircuitBreakerType } from 'app/shared/model/circuit-breaker-type.model';

type EntityResponseType = HttpResponse<ICircuitBreakerType>;
type EntityArrayResponseType = HttpResponse<ICircuitBreakerType[]>;

@Injectable({ providedIn: 'root' })
export class CircuitBreakerTypeService {
    private resourceUrl = SERVER_API_URL + 'api/circuit-breaker-types';

    constructor(private http: HttpClient) {}

    create(circuitBreakerType: ICircuitBreakerType): Observable<EntityResponseType> {
        return this.http.post<ICircuitBreakerType>(this.resourceUrl, circuitBreakerType, { observe: 'response' });
    }

    update(circuitBreakerType: ICircuitBreakerType): Observable<EntityResponseType> {
        return this.http.put<ICircuitBreakerType>(this.resourceUrl, circuitBreakerType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICircuitBreakerType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICircuitBreakerType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
