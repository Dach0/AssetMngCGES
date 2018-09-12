/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { PylonTypeComponent } from 'app/entities/pylon-type/pylon-type.component';
import { PylonTypeService } from 'app/entities/pylon-type/pylon-type.service';
import { PylonType } from 'app/shared/model/pylon-type.model';

describe('Component Tests', () => {
    describe('PylonType Management Component', () => {
        let comp: PylonTypeComponent;
        let fixture: ComponentFixture<PylonTypeComponent>;
        let service: PylonTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [PylonTypeComponent],
                providers: []
            })
                .overrideTemplate(PylonTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PylonTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PylonTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PylonType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.pylonTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
