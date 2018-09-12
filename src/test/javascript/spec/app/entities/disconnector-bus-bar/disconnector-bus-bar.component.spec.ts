/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorBusBarComponent } from 'app/entities/disconnector-bus-bar/disconnector-bus-bar.component';
import { DisconnectorBusBarService } from 'app/entities/disconnector-bus-bar/disconnector-bus-bar.service';
import { DisconnectorBusBar } from 'app/shared/model/disconnector-bus-bar.model';

describe('Component Tests', () => {
    describe('DisconnectorBusBar Management Component', () => {
        let comp: DisconnectorBusBarComponent;
        let fixture: ComponentFixture<DisconnectorBusBarComponent>;
        let service: DisconnectorBusBarService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorBusBarComponent],
                providers: []
            })
                .overrideTemplate(DisconnectorBusBarComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DisconnectorBusBarComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorBusBarService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DisconnectorBusBar(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.disconnectorBusBars[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
