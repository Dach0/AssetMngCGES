/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { SurgeArresterTypeComponent } from 'app/entities/surge-arrester-type/surge-arrester-type.component';
import { SurgeArresterTypeService } from 'app/entities/surge-arrester-type/surge-arrester-type.service';
import { SurgeArresterType } from 'app/shared/model/surge-arrester-type.model';

describe('Component Tests', () => {
    describe('SurgeArresterType Management Component', () => {
        let comp: SurgeArresterTypeComponent;
        let fixture: ComponentFixture<SurgeArresterTypeComponent>;
        let service: SurgeArresterTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [SurgeArresterTypeComponent],
                providers: []
            })
                .overrideTemplate(SurgeArresterTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SurgeArresterTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurgeArresterTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SurgeArresterType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.surgeArresterTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
