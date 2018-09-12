/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { OhlComponent } from 'app/entities/ohl/ohl.component';
import { OhlService } from 'app/entities/ohl/ohl.service';
import { Ohl } from 'app/shared/model/ohl.model';

describe('Component Tests', () => {
    describe('Ohl Management Component', () => {
        let comp: OhlComponent;
        let fixture: ComponentFixture<OhlComponent>;
        let service: OhlService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [OhlComponent],
                providers: []
            })
                .overrideTemplate(OhlComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OhlComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OhlService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Ohl(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.ohls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
