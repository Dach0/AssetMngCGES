import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDisconnectorDrive } from 'app/shared/model/disconnector-drive.model';
import { DisconnectorDriveService } from './disconnector-drive.service';

@Component({
    selector: 'jhi-disconnector-drive-update',
    templateUrl: './disconnector-drive-update.component.html'
})
export class DisconnectorDriveUpdateComponent implements OnInit {
    private _disconnectorDrive: IDisconnectorDrive;
    isSaving: boolean;

    constructor(private disconnectorDriveService: DisconnectorDriveService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ disconnectorDrive }) => {
            this.disconnectorDrive = disconnectorDrive;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.disconnectorDrive.id !== undefined) {
            this.subscribeToSaveResponse(this.disconnectorDriveService.update(this.disconnectorDrive));
        } else {
            this.subscribeToSaveResponse(this.disconnectorDriveService.create(this.disconnectorDrive));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDisconnectorDrive>>) {
        result.subscribe((res: HttpResponse<IDisconnectorDrive>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get disconnectorDrive() {
        return this._disconnectorDrive;
    }

    set disconnectorDrive(disconnectorDrive: IDisconnectorDrive) {
        this._disconnectorDrive = disconnectorDrive;
    }
}
