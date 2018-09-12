import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICircuitBreaker } from 'app/shared/model/circuit-breaker.model';

type EntityResponseType = HttpResponse<ICircuitBreaker>;
type EntityArrayResponseType = HttpResponse<ICircuitBreaker[]>;

@Injectable({ providedIn: 'root' })
export class CircuitBreakerService {
    private resourceUrl = SERVER_API_URL + 'api/circuit-breakers';

    constructor(private http: HttpClient) {}

    create(circuitBreaker: ICircuitBreaker): Observable<EntityResponseType> {
        return this.http.post<ICircuitBreaker>(this.resourceUrl, circuitBreaker, { observe: 'response' });
    }

    update(circuitBreaker: ICircuitBreaker): Observable<EntityResponseType> {
        return this.http.put<ICircuitBreaker>(this.resourceUrl, circuitBreaker, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICircuitBreaker>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICircuitBreaker[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
