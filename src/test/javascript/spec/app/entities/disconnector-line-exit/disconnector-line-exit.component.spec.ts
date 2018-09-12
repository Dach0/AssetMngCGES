/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorLineExitComponent } from 'app/entities/disconnector-line-exit/disconnector-line-exit.component';
import { DisconnectorLineExitService } from 'app/entities/disconnector-line-exit/disconnector-line-exit.service';
import { DisconnectorLineExit } from 'app/shared/model/disconnector-line-exit.model';

describe('Component Tests', () => {
    describe('DisconnectorLineExit Management Component', () => {
        let comp: DisconnectorLineExitComponent;
        let fixture: ComponentFixture<DisconnectorLineExitComponent>;
        let service: DisconnectorLineExitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorLineExitComponent],
                providers: []
            })
                .overrideTemplate(DisconnectorLineExitComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DisconnectorLineExitComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorLineExitService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DisconnectorLineExit(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.disconnectorLineExits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
