import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IQualification } from 'app/shared/model/qualification.model';
import { QualificationService } from './qualification.service';

@Component({
    selector: 'jhi-qualification-update',
    templateUrl: './qualification-update.component.html'
})
export class QualificationUpdateComponent implements OnInit {
    private _qualification: IQualification;
    isSaving: boolean;

    constructor(private qualificationService: QualificationService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ qualification }) => {
            this.qualification = qualification;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.qualification.id !== undefined) {
            this.subscribeToSaveResponse(this.qualificationService.update(this.qualification));
        } else {
            this.subscribeToSaveResponse(this.qualificationService.create(this.qualification));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IQualification>>) {
        result.subscribe((res: HttpResponse<IQualification>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get qualification() {
        return this._qualification;
    }

    set qualification(qualification: IQualification) {
        this._qualification = qualification;
    }
}
