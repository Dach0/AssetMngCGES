/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ElementConditionComponent } from 'app/entities/element-condition/element-condition.component';
import { ElementConditionService } from 'app/entities/element-condition/element-condition.service';
import { ElementCondition } from 'app/shared/model/element-condition.model';

describe('Component Tests', () => {
    describe('ElementCondition Management Component', () => {
        let comp: ElementConditionComponent;
        let fixture: ComponentFixture<ElementConditionComponent>;
        let service: ElementConditionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ElementConditionComponent],
                providers: []
            })
                .overrideTemplate(ElementConditionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ElementConditionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElementConditionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ElementCondition(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.elementConditions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
