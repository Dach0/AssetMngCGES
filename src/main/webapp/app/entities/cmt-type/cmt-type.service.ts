import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICmtType } from 'app/shared/model/cmt-type.model';

type EntityResponseType = HttpResponse<ICmtType>;
type EntityArrayResponseType = HttpResponse<ICmtType[]>;

@Injectable({ providedIn: 'root' })
export class CmtTypeService {
    private resourceUrl = SERVER_API_URL + 'api/cmt-types';

    constructor(private http: HttpClient) {}

    create(cmtType: ICmtType): Observable<EntityResponseType> {
        return this.http.post<ICmtType>(this.resourceUrl, cmtType, { observe: 'response' });
    }

    update(cmtType: ICmtType): Observable<EntityResponseType> {
        return this.http.put<ICmtType>(this.resourceUrl, cmtType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICmtType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICmtType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
