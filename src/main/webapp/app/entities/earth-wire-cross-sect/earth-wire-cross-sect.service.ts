import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';

type EntityResponseType = HttpResponse<IEarthWireCrossSect>;
type EntityArrayResponseType = HttpResponse<IEarthWireCrossSect[]>;

@Injectable({ providedIn: 'root' })
export class EarthWireCrossSectService {
    private resourceUrl = SERVER_API_URL + 'api/earth-wire-cross-sects';

    constructor(private http: HttpClient) {}

    create(earthWireCrossSect: IEarthWireCrossSect): Observable<EntityResponseType> {
        return this.http.post<IEarthWireCrossSect>(this.resourceUrl, earthWireCrossSect, { observe: 'response' });
    }

    update(earthWireCrossSect: IEarthWireCrossSect): Observable<EntityResponseType> {
        return this.http.put<IEarthWireCrossSect>(this.resourceUrl, earthWireCrossSect, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEarthWireCrossSect>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEarthWireCrossSect[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
