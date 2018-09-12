import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITransmissionRatio } from 'app/shared/model/transmission-ratio.model';

type EntityResponseType = HttpResponse<ITransmissionRatio>;
type EntityArrayResponseType = HttpResponse<ITransmissionRatio[]>;

@Injectable({ providedIn: 'root' })
export class TransmissionRatioService {
    private resourceUrl = SERVER_API_URL + 'api/transmission-ratios';

    constructor(private http: HttpClient) {}

    create(transmissionRatio: ITransmissionRatio): Observable<EntityResponseType> {
        return this.http.post<ITransmissionRatio>(this.resourceUrl, transmissionRatio, { observe: 'response' });
    }

    update(transmissionRatio: ITransmissionRatio): Observable<EntityResponseType> {
        return this.http.put<ITransmissionRatio>(this.resourceUrl, transmissionRatio, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITransmissionRatio>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITransmissionRatio[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
