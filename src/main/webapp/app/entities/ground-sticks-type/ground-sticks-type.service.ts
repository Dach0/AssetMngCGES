import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGroundSticksType } from 'app/shared/model/ground-sticks-type.model';

type EntityResponseType = HttpResponse<IGroundSticksType>;
type EntityArrayResponseType = HttpResponse<IGroundSticksType[]>;

@Injectable({ providedIn: 'root' })
export class GroundSticksTypeService {
    private resourceUrl = SERVER_API_URL + 'api/ground-sticks-types';

    constructor(private http: HttpClient) {}

    create(groundSticksType: IGroundSticksType): Observable<EntityResponseType> {
        return this.http.post<IGroundSticksType>(this.resourceUrl, groundSticksType, { observe: 'response' });
    }

    update(groundSticksType: IGroundSticksType): Observable<EntityResponseType> {
        return this.http.put<IGroundSticksType>(this.resourceUrl, groundSticksType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGroundSticksType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGroundSticksType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
