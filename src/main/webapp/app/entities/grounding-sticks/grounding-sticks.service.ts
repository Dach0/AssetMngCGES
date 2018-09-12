import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGroundingSticks } from 'app/shared/model/grounding-sticks.model';

type EntityResponseType = HttpResponse<IGroundingSticks>;
type EntityArrayResponseType = HttpResponse<IGroundingSticks[]>;

@Injectable({ providedIn: 'root' })
export class GroundingSticksService {
    private resourceUrl = SERVER_API_URL + 'api/grounding-sticks';

    constructor(private http: HttpClient) {}

    create(groundingSticks: IGroundingSticks): Observable<EntityResponseType> {
        return this.http.post<IGroundingSticks>(this.resourceUrl, groundingSticks, { observe: 'response' });
    }

    update(groundingSticks: IGroundingSticks): Observable<EntityResponseType> {
        return this.http.put<IGroundingSticks>(this.resourceUrl, groundingSticks, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGroundingSticks>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGroundingSticks[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
