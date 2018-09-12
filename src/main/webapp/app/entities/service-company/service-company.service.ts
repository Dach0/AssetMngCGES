import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IServiceCompany } from 'app/shared/model/service-company.model';

type EntityResponseType = HttpResponse<IServiceCompany>;
type EntityArrayResponseType = HttpResponse<IServiceCompany[]>;

@Injectable({ providedIn: 'root' })
export class ServiceCompanyService {
    private resourceUrl = SERVER_API_URL + 'api/service-companies';

    constructor(private http: HttpClient) {}

    create(serviceCompany: IServiceCompany): Observable<EntityResponseType> {
        return this.http.post<IServiceCompany>(this.resourceUrl, serviceCompany, { observe: 'response' });
    }

    update(serviceCompany: IServiceCompany): Observable<EntityResponseType> {
        return this.http.put<IServiceCompany>(this.resourceUrl, serviceCompany, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IServiceCompany>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IServiceCompany[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
