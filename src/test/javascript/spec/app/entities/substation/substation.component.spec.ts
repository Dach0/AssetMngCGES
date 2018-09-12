/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SubstationComponent } from 'app/entities/substation/substation.component';
import { SubstationService } from 'app/entities/substation/substation.service';
import { Substation } from 'app/shared/model/substation.model';

describe('Component Tests', () => {
    describe('Substation Management Component', () => {
        let comp: SubstationComponent;
        let fixture: ComponentFixture<SubstationComponent>;
        let service: SubstationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SubstationComponent],
                providers: []
            })
                .overrideTemplate(SubstationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SubstationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubstationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Substation(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.substations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
