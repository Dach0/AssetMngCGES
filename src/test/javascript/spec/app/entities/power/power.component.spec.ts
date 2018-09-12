/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { PowerComponent } from 'app/entities/power/power.component';
import { PowerService } from 'app/entities/power/power.service';
import { Power } from 'app/shared/model/power.model';

describe('Component Tests', () => {
    describe('Power Management Component', () => {
        let comp: PowerComponent;
        let fixture: ComponentFixture<PowerComponent>;
        let service: PowerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [PowerComponent],
                providers: []
            })
                .overrideTemplate(PowerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PowerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PowerService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Power(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.powers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
