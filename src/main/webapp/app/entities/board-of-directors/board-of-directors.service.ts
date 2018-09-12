import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBoardOfDirectors } from 'app/shared/model/board-of-directors.model';

type EntityResponseType = HttpResponse<IBoardOfDirectors>;
type EntityArrayResponseType = HttpResponse<IBoardOfDirectors[]>;

@Injectable({ providedIn: 'root' })
export class BoardOfDirectorsService {
    private resourceUrl = SERVER_API_URL + 'api/board-of-directors';

    constructor(private http: HttpClient) {}

    create(boardOfDirectors: IBoardOfDirectors): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(boardOfDirectors);
        return this.http
            .post<IBoardOfDirectors>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(boardOfDirectors: IBoardOfDirectors): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(boardOfDirectors);
        return this.http
            .put<IBoardOfDirectors>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBoardOfDirectors>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBoardOfDirectors[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(boardOfDirectors: IBoardOfDirectors): IBoardOfDirectors {
        const copy: IBoardOfDirectors = Object.assign({}, boardOfDirectors, {
            startDate:
                boardOfDirectors.startDate != null && boardOfDirectors.startDate.isValid()
                    ? boardOfDirectors.startDate.format(DATE_FORMAT)
                    : null,
            endDate:
                boardOfDirectors.endDate != null && boardOfDirectors.endDate.isValid() ? boardOfDirectors.endDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
        res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((boardOfDirectors: IBoardOfDirectors) => {
            boardOfDirectors.startDate = boardOfDirectors.startDate != null ? moment(boardOfDirectors.startDate) : null;
            boardOfDirectors.endDate = boardOfDirectors.endDate != null ? moment(boardOfDirectors.endDate) : null;
        });
        return res;
    }
}
