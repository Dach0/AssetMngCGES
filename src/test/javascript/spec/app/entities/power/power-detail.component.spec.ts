/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { PowerDetailComponent } from 'app/entities/power/power-detail.component';
import { Power } from 'app/shared/model/power.model';

describe('Component Tests', () => {
    describe('Power Management Detail Component', () => {
        let comp: PowerDetailComponent;
        let fixture: ComponentFixture<PowerDetailComponent>;
        const route = ({ data: of({ power: new Power(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [PowerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PowerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PowerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.power).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
