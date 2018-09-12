import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IService } from 'app/shared/model/service.model';

type EntityResponseType = HttpResponse<IService>;
type EntityArrayResponseType = HttpResponse<IService[]>;

@Injectable({ providedIn: 'root' })
export class ServiceService {
    private resourceUrl = SERVER_API_URL + 'api/services';

    constructor(private http: HttpClient) {}

    create(service: IService): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(service);
        return this.http
            .post<IService>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(service: IService): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(service);
        return this.http
            .put<IService>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IService>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IService[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(service: IService): IService {
        const copy: IService = Object.assign({}, service, {
            servicedFrom: service.servicedFrom != null && service.servicedFrom.isValid() ? service.servicedFrom.format(DATE_FORMAT) : null,
            servicedTo: service.servicedTo != null && service.servicedTo.isValid() ? service.servicedTo.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.servicedFrom = res.body.servicedFrom != null ? moment(res.body.servicedFrom) : null;
        res.body.servicedTo = res.body.servicedTo != null ? moment(res.body.servicedTo) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((service: IService) => {
            service.servicedFrom = service.servicedFrom != null ? moment(service.servicedFrom) : null;
            service.servicedTo = service.servicedTo != null ? moment(service.servicedTo) : null;
        });
        return res;
    }
}
