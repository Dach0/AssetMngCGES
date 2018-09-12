import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFacilityMaintainingCo } from 'app/shared/model/facility-maintaining-co.model';

type EntityResponseType = HttpResponse<IFacilityMaintainingCo>;
type EntityArrayResponseType = HttpResponse<IFacilityMaintainingCo[]>;

@Injectable({ providedIn: 'root' })
export class FacilityMaintainingCoService {
    private resourceUrl = SERVER_API_URL + 'api/facility-maintaining-cos';

    constructor(private http: HttpClient) {}

    create(facilityMaintainingCo: IFacilityMaintainingCo): Observable<EntityResponseType> {
        return this.http.post<IFacilityMaintainingCo>(this.resourceUrl, facilityMaintainingCo, { observe: 'response' });
    }

    update(facilityMaintainingCo: IFacilityMaintainingCo): Observable<EntityResponseType> {
        return this.http.put<IFacilityMaintainingCo>(this.resourceUrl, facilityMaintainingCo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFacilityMaintainingCo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFacilityMaintainingCo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
