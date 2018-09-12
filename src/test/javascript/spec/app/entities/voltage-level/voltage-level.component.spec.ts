/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { VoltageLevelComponent } from 'app/entities/voltage-level/voltage-level.component';
import { VoltageLevelService } from 'app/entities/voltage-level/voltage-level.service';
import { VoltageLevel } from 'app/shared/model/voltage-level.model';

describe('Component Tests', () => {
    describe('VoltageLevel Management Component', () => {
        let comp: VoltageLevelComponent;
        let fixture: ComponentFixture<VoltageLevelComponent>;
        let service: VoltageLevelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [VoltageLevelComponent],
                providers: []
            })
                .overrideTemplate(VoltageLevelComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VoltageLevelComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VoltageLevelService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new VoltageLevel(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.voltageLevels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
