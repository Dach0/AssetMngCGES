import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICircuitBreakerDrive } from 'app/shared/model/circuit-breaker-drive.model';

type EntityResponseType = HttpResponse<ICircuitBreakerDrive>;
type EntityArrayResponseType = HttpResponse<ICircuitBreakerDrive[]>;

@Injectable({ providedIn: 'root' })
export class CircuitBreakerDriveService {
    private resourceUrl = SERVER_API_URL + 'api/circuit-breaker-drives';

    constructor(private http: HttpClient) {}

    create(circuitBreakerDrive: ICircuitBreakerDrive): Observable<EntityResponseType> {
        return this.http.post<ICircuitBreakerDrive>(this.resourceUrl, circuitBreakerDrive, { observe: 'response' });
    }

    update(circuitBreakerDrive: ICircuitBreakerDrive): Observable<EntityResponseType> {
        return this.http.put<ICircuitBreakerDrive>(this.resourceUrl, circuitBreakerDrive, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICircuitBreakerDrive>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICircuitBreakerDrive[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
