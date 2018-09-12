import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IElementCondition } from 'app/shared/model/element-condition.model';
import { ElementConditionService } from './element-condition.service';

@Component({
    selector: 'jhi-element-condition-update',
    templateUrl: './element-condition-update.component.html'
})
export class ElementConditionUpdateComponent implements OnInit {
    private _elementCondition: IElementCondition;
    isSaving: boolean;

    constructor(private elementConditionService: ElementConditionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ elementCondition }) => {
            this.elementCondition = elementCondition;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.elementCondition.id !== undefined) {
            this.subscribeToSaveResponse(this.elementConditionService.update(this.elementCondition));
        } else {
            this.subscribeToSaveResponse(this.elementConditionService.create(this.elementCondition));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IElementCondition>>) {
        result.subscribe((res: HttpResponse<IElementCondition>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get elementCondition() {
        return this._elementCondition;
    }

    set elementCondition(elementCondition: IElementCondition) {
        this._elementCondition = elementCondition;
    }
}
