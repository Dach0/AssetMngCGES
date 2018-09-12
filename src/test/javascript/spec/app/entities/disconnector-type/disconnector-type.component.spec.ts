/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorTypeComponent } from 'app/entities/disconnector-type/disconnector-type.component';
import { DisconnectorTypeService } from 'app/entities/disconnector-type/disconnector-type.service';
import { DisconnectorType } from 'app/shared/model/disconnector-type.model';

describe('Component Tests', () => {
    describe('DisconnectorType Management Component', () => {
        let comp: DisconnectorTypeComponent;
        let fixture: ComponentFixture<DisconnectorTypeComponent>;
        let service: DisconnectorTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorTypeComponent],
                providers: []
            })
                .overrideTemplate(DisconnectorTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DisconnectorTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DisconnectorType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.disconnectorTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
