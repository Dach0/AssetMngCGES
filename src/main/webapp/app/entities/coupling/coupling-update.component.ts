import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICoupling } from 'app/shared/model/coupling.model';
import { CouplingService } from './coupling.service';

@Component({
    selector: 'jhi-coupling-update',
    templateUrl: './coupling-update.component.html'
})
export class CouplingUpdateComponent implements OnInit {
    private _coupling: ICoupling;
    isSaving: boolean;

    constructor(private couplingService: CouplingService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ coupling }) => {
            this.coupling = coupling;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.coupling.id !== undefined) {
            this.subscribeToSaveResponse(this.couplingService.update(this.coupling));
        } else {
            this.subscribeToSaveResponse(this.couplingService.create(this.coupling));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICoupling>>) {
        result.subscribe((res: HttpResponse<ICoupling>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get coupling() {
        return this._coupling;
    }

    set coupling(coupling: ICoupling) {
        this._coupling = coupling;
    }
}
