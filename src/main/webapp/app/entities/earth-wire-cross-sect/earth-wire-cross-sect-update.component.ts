import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEarthWireCrossSect } from 'app/shared/model/earth-wire-cross-sect.model';
import { EarthWireCrossSectService } from './earth-wire-cross-sect.service';

@Component({
    selector: 'jhi-earth-wire-cross-sect-update',
    templateUrl: './earth-wire-cross-sect-update.component.html'
})
export class EarthWireCrossSectUpdateComponent implements OnInit {
    private _earthWireCrossSect: IEarthWireCrossSect;
    isSaving: boolean;

    constructor(private earthWireCrossSectService: EarthWireCrossSectService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ earthWireCrossSect }) => {
            this.earthWireCrossSect = earthWireCrossSect;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.earthWireCrossSect.id !== undefined) {
            this.subscribeToSaveResponse(this.earthWireCrossSectService.update(this.earthWireCrossSect));
        } else {
            this.subscribeToSaveResponse(this.earthWireCrossSectService.create(this.earthWireCrossSect));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEarthWireCrossSect>>) {
        result.subscribe((res: HttpResponse<IEarthWireCrossSect>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get earthWireCrossSect() {
        return this._earthWireCrossSect;
    }

    set earthWireCrossSect(earthWireCrossSect: IEarthWireCrossSect) {
        this._earthWireCrossSect = earthWireCrossSect;
    }
}
