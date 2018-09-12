import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICorrectionFactor } from 'app/shared/model/correction-factor.model';
import { CorrectionFactorService } from './correction-factor.service';

@Component({
    selector: 'jhi-correction-factor-update',
    templateUrl: './correction-factor-update.component.html'
})
export class CorrectionFactorUpdateComponent implements OnInit {
    private _correctionFactor: ICorrectionFactor;
    isSaving: boolean;

    constructor(private correctionFactorService: CorrectionFactorService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ correctionFactor }) => {
            this.correctionFactor = correctionFactor;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.correctionFactor.id !== undefined) {
            this.subscribeToSaveResponse(this.correctionFactorService.update(this.correctionFactor));
        } else {
            this.subscribeToSaveResponse(this.correctionFactorService.create(this.correctionFactor));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICorrectionFactor>>) {
        result.subscribe((res: HttpResponse<ICorrectionFactor>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get correctionFactor() {
        return this._correctionFactor;
    }

    set correctionFactor(correctionFactor: ICorrectionFactor) {
        this._correctionFactor = correctionFactor;
    }
}
