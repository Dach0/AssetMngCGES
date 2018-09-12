import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPhonePrivilage } from 'app/shared/model/phone-privilage.model';

type EntityResponseType = HttpResponse<IPhonePrivilage>;
type EntityArrayResponseType = HttpResponse<IPhonePrivilage[]>;

@Injectable({ providedIn: 'root' })
export class PhonePrivilageService {
    private resourceUrl = SERVER_API_URL + 'api/phone-privilages';

    constructor(private http: HttpClient) {}

    create(phonePrivilage: IPhonePrivilage): Observable<EntityResponseType> {
        return this.http.post<IPhonePrivilage>(this.resourceUrl, phonePrivilage, { observe: 'response' });
    }

    update(phonePrivilage: IPhonePrivilage): Observable<EntityResponseType> {
        return this.http.put<IPhonePrivilage>(this.resourceUrl, phonePrivilage, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPhonePrivilage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPhonePrivilage[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
