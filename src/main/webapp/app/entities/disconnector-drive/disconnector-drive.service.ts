import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDisconnectorDrive } from 'app/shared/model/disconnector-drive.model';

type EntityResponseType = HttpResponse<IDisconnectorDrive>;
type EntityArrayResponseType = HttpResponse<IDisconnectorDrive[]>;

@Injectable({ providedIn: 'root' })
export class DisconnectorDriveService {
    private resourceUrl = SERVER_API_URL + 'api/disconnector-drives';

    constructor(private http: HttpClient) {}

    create(disconnectorDrive: IDisconnectorDrive): Observable<EntityResponseType> {
        return this.http.post<IDisconnectorDrive>(this.resourceUrl, disconnectorDrive, { observe: 'response' });
    }

    update(disconnectorDrive: IDisconnectorDrive): Observable<EntityResponseType> {
        return this.http.put<IDisconnectorDrive>(this.resourceUrl, disconnectorDrive, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDisconnectorDrive>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDisconnectorDrive[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
