/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ElementConditionDetailComponent } from 'app/entities/element-condition/element-condition-detail.component';
import { ElementCondition } from 'app/shared/model/element-condition.model';

describe('Component Tests', () => {
    describe('ElementCondition Management Detail Component', () => {
        let comp: ElementConditionDetailComponent;
        let fixture: ComponentFixture<ElementConditionDetailComponent>;
        const route = ({ data: of({ elementCondition: new ElementCondition(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ElementConditionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ElementConditionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ElementConditionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.elementCondition).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
