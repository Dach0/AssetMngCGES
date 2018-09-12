/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { TconnectionComponent } from 'app/entities/tconnection/tconnection.component';
import { TconnectionService } from 'app/entities/tconnection/tconnection.service';
import { Tconnection } from 'app/shared/model/tconnection.model';

describe('Component Tests', () => {
    describe('Tconnection Management Component', () => {
        let comp: TconnectionComponent;
        let fixture: ComponentFixture<TconnectionComponent>;
        let service: TconnectionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [TconnectionComponent],
                providers: []
            })
                .overrideTemplate(TconnectionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TconnectionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TconnectionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Tconnection(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.tconnections[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
