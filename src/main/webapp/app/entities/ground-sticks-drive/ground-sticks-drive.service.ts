import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';

type EntityResponseType = HttpResponse<IGroundSticksDrive>;
type EntityArrayResponseType = HttpResponse<IGroundSticksDrive[]>;

@Injectable({ providedIn: 'root' })
export class GroundSticksDriveService {
    private resourceUrl = SERVER_API_URL + 'api/ground-sticks-drives';

    constructor(private http: HttpClient) {}

    create(groundSticksDrive: IGroundSticksDrive): Observable<EntityResponseType> {
        return this.http.post<IGroundSticksDrive>(this.resourceUrl, groundSticksDrive, { observe: 'response' });
    }

    update(groundSticksDrive: IGroundSticksDrive): Observable<EntityResponseType> {
        return this.http.put<IGroundSticksDrive>(this.resourceUrl, groundSticksDrive, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGroundSticksDrive>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGroundSticksDrive[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
