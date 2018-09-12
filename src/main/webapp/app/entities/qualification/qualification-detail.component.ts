import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQualification } from 'app/shared/model/qualification.model';

@Component({
    selector: 'jhi-qualification-detail',
    templateUrl: './qualification-detail.component.html'
})
export class QualificationDetailComponent implements OnInit {
    qualification: IQualification;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ qualification }) => {
            this.qualification = qualification;
        });
    }

    previousState() {
        window.history.back();
    }
}
