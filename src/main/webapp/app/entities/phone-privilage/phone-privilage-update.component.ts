import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPhonePrivilage } from 'app/shared/model/phone-privilage.model';
import { PhonePrivilageService } from './phone-privilage.service';

@Component({
    selector: 'jhi-phone-privilage-update',
    templateUrl: './phone-privilage-update.component.html'
})
export class PhonePrivilageUpdateComponent implements OnInit {
    private _phonePrivilage: IPhonePrivilage;
    isSaving: boolean;

    constructor(private phonePrivilageService: PhonePrivilageService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ phonePrivilage }) => {
            this.phonePrivilage = phonePrivilage;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.phonePrivilage.id !== undefined) {
            this.subscribeToSaveResponse(this.phonePrivilageService.update(this.phonePrivilage));
        } else {
            this.subscribeToSaveResponse(this.phonePrivilageService.create(this.phonePrivilage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPhonePrivilage>>) {
        result.subscribe((res: HttpResponse<IPhonePrivilage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get phonePrivilage() {
        return this._phonePrivilage;
    }

    set phonePrivilage(phonePrivilage: IPhonePrivilage) {
        this._phonePrivilage = phonePrivilage;
    }
}
