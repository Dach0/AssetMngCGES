import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVmtType } from 'app/shared/model/vmt-type.model';

type EntityResponseType = HttpResponse<IVmtType>;
type EntityArrayResponseType = HttpResponse<IVmtType[]>;

@Injectable({ providedIn: 'root' })
export class VmtTypeService {
    private resourceUrl = SERVER_API_URL + 'api/vmt-types';

    constructor(private http: HttpClient) {}

    create(vmtType: IVmtType): Observable<EntityResponseType> {
        return this.http.post<IVmtType>(this.resourceUrl, vmtType, { observe: 'response' });
    }

    update(vmtType: IVmtType): Observable<EntityResponseType> {
        return this.http.put<IVmtType>(this.resourceUrl, vmtType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVmtType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVmtType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
