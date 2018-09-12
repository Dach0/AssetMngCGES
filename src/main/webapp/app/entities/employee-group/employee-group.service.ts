import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmployeeGroup } from 'app/shared/model/employee-group.model';

type EntityResponseType = HttpResponse<IEmployeeGroup>;
type EntityArrayResponseType = HttpResponse<IEmployeeGroup[]>;

@Injectable({ providedIn: 'root' })
export class EmployeeGroupService {
    private resourceUrl = SERVER_API_URL + 'api/employee-groups';

    constructor(private http: HttpClient) {}

    create(employeeGroup: IEmployeeGroup): Observable<EntityResponseType> {
        return this.http.post<IEmployeeGroup>(this.resourceUrl, employeeGroup, { observe: 'response' });
    }

    update(employeeGroup: IEmployeeGroup): Observable<EntityResponseType> {
        return this.http.put<IEmployeeGroup>(this.resourceUrl, employeeGroup, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEmployeeGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEmployeeGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
