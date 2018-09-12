/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VoltageLevelDetailComponent } from 'app/entities/voltage-level/voltage-level-detail.component';
import { VoltageLevel } from 'app/shared/model/voltage-level.model';

describe('Component Tests', () => {
    describe('VoltageLevel Management Detail Component', () => {
        let comp: VoltageLevelDetailComponent;
        let fixture: ComponentFixture<VoltageLevelDetailComponent>;
        const route = ({ data: of({ voltageLevel: new VoltageLevel(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VoltageLevelDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VoltageLevelDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VoltageLevelDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.voltageLevel).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
