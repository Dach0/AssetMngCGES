import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IGroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';
import { GroundSticksDriveService } from './ground-sticks-drive.service';

@Component({
    selector: 'jhi-ground-sticks-drive-update',
    templateUrl: './ground-sticks-drive-update.component.html'
})
export class GroundSticksDriveUpdateComponent implements OnInit {
    private _groundSticksDrive: IGroundSticksDrive;
    isSaving: boolean;

    constructor(private groundSticksDriveService: GroundSticksDriveService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ groundSticksDrive }) => {
            this.groundSticksDrive = groundSticksDrive;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.groundSticksDrive.id !== undefined) {
            this.subscribeToSaveResponse(this.groundSticksDriveService.update(this.groundSticksDrive));
        } else {
            this.subscribeToSaveResponse(this.groundSticksDriveService.create(this.groundSticksDrive));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGroundSticksDrive>>) {
        result.subscribe((res: HttpResponse<IGroundSticksDrive>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get groundSticksDrive() {
        return this._groundSticksDrive;
    }

    set groundSticksDrive(groundSticksDrive: IGroundSticksDrive) {
        this._groundSticksDrive = groundSticksDrive;
    }
}
