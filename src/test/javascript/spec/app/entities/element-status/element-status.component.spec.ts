/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ElementStatusComponent } from 'app/entities/element-status/element-status.component';
import { ElementStatusService } from 'app/entities/element-status/element-status.service';
import { ElementStatus } from 'app/shared/model/element-status.model';

describe('Component Tests', () => {
    describe('ElementStatus Management Component', () => {
        let comp: ElementStatusComponent;
        let fixture: ComponentFixture<ElementStatusComponent>;
        let service: ElementStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ElementStatusComponent],
                providers: []
            })
                .overrideTemplate(ElementStatusComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ElementStatusComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElementStatusService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ElementStatus(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.elementStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
