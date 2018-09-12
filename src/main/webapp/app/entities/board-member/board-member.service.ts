import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBoardMember } from 'app/shared/model/board-member.model';

type EntityResponseType = HttpResponse<IBoardMember>;
type EntityArrayResponseType = HttpResponse<IBoardMember[]>;

@Injectable({ providedIn: 'root' })
export class BoardMemberService {
    private resourceUrl = SERVER_API_URL + 'api/board-members';

    constructor(private http: HttpClient) {}

    create(boardMember: IBoardMember): Observable<EntityResponseType> {
        return this.http.post<IBoardMember>(this.resourceUrl, boardMember, { observe: 'response' });
    }

    update(boardMember: IBoardMember): Observable<EntityResponseType> {
        return this.http.put<IBoardMember>(this.resourceUrl, boardMember, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBoardMember>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBoardMember[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
