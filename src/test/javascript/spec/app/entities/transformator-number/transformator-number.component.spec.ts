/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TransformatorNumberComponent } from 'app/entities/transformator-number/transformator-number.component';
import { TransformatorNumberService } from 'app/entities/transformator-number/transformator-number.service';
import { TransformatorNumber } from 'app/shared/model/transformator-number.model';

describe('Component Tests', () => {
    describe('TransformatorNumber Management Component', () => {
        let comp: TransformatorNumberComponent;
        let fixture: ComponentFixture<TransformatorNumberComponent>;
        let service: TransformatorNumberService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TransformatorNumberComponent],
                providers: []
            })
                .overrideTemplate(TransformatorNumberComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransformatorNumberComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransformatorNumberService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TransformatorNumber(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.transformatorNumbers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
