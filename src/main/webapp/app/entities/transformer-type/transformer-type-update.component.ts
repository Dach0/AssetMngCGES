import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITransformerType } from 'app/shared/model/transformer-type.model';
import { TransformerTypeService } from './transformer-type.service';

@Component({
    selector: 'jhi-transformer-type-update',
    templateUrl: './transformer-type-update.component.html'
})
export class TransformerTypeUpdateComponent implements OnInit {
    private _transformerType: ITransformerType;
    isSaving: boolean;

    constructor(private transformerTypeService: TransformerTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transformerType }) => {
            this.transformerType = transformerType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.transformerType.id !== undefined) {
            this.subscribeToSaveResponse(this.transformerTypeService.update(this.transformerType));
        } else {
            this.subscribeToSaveResponse(this.transformerTypeService.create(this.transformerType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITransformerType>>) {
        result.subscribe((res: HttpResponse<ITransformerType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get transformerType() {
        return this._transformerType;
    }

    set transformerType(transformerType: ITransformerType) {
        this._transformerType = transformerType;
    }
}
