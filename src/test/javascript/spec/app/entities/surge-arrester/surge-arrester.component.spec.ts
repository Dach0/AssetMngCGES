/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SurgeArresterComponent } from 'app/entities/surge-arrester/surge-arrester.component';
import { SurgeArresterService } from 'app/entities/surge-arrester/surge-arrester.service';
import { SurgeArrester } from 'app/shared/model/surge-arrester.model';

describe('Component Tests', () => {
    describe('SurgeArrester Management Component', () => {
        let comp: SurgeArresterComponent;
        let fixture: ComponentFixture<SurgeArresterComponent>;
        let service: SurgeArresterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SurgeArresterComponent],
                providers: []
            })
                .overrideTemplate(SurgeArresterComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SurgeArresterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurgeArresterService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SurgeArrester(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.surgeArresters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
