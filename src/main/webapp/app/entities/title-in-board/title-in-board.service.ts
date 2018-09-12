import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITitleInBoard } from 'app/shared/model/title-in-board.model';

type EntityResponseType = HttpResponse<ITitleInBoard>;
type EntityArrayResponseType = HttpResponse<ITitleInBoard[]>;

@Injectable({ providedIn: 'root' })
export class TitleInBoardService {
    private resourceUrl = SERVER_API_URL + 'api/title-in-boards';

    constructor(private http: HttpClient) {}

    create(titleInBoard: ITitleInBoard): Observable<EntityResponseType> {
        return this.http.post<ITitleInBoard>(this.resourceUrl, titleInBoard, { observe: 'response' });
    }

    update(titleInBoard: ITitleInBoard): Observable<EntityResponseType> {
        return this.http.put<ITitleInBoard>(this.resourceUrl, titleInBoard, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITitleInBoard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITitleInBoard[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
