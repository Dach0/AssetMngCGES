/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { QualificationComponent } from 'app/entities/qualification/qualification.component';
import { QualificationService } from 'app/entities/qualification/qualification.service';
import { Qualification } from 'app/shared/model/qualification.model';

describe('Component Tests', () => {
    describe('Qualification Management Component', () => {
        let comp: QualificationComponent;
        let fixture: ComponentFixture<QualificationComponent>;
        let service: QualificationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [QualificationComponent],
                providers: []
            })
                .overrideTemplate(QualificationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(QualificationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QualificationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Qualification(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.qualifications[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
