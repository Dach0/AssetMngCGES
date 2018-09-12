/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundingSticksComponent } from 'app/entities/grounding-sticks/grounding-sticks.component';
import { GroundingSticksService } from 'app/entities/grounding-sticks/grounding-sticks.service';
import { GroundingSticks } from 'app/shared/model/grounding-sticks.model';

describe('Component Tests', () => {
    describe('GroundingSticks Management Component', () => {
        let comp: GroundingSticksComponent;
        let fixture: ComponentFixture<GroundingSticksComponent>;
        let service: GroundingSticksService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundingSticksComponent],
                providers: []
            })
                .overrideTemplate(GroundingSticksComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroundingSticksComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundingSticksService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new GroundingSticks(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.groundingSticks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
