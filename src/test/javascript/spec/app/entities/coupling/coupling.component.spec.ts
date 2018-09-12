/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { CouplingComponent } from 'app/entities/coupling/coupling.component';
import { CouplingService } from 'app/entities/coupling/coupling.service';
import { Coupling } from 'app/shared/model/coupling.model';

describe('Component Tests', () => {
    describe('Coupling Management Component', () => {
        let comp: CouplingComponent;
        let fixture: ComponentFixture<CouplingComponent>;
        let service: CouplingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [CouplingComponent],
                providers: []
            })
                .overrideTemplate(CouplingComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CouplingComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CouplingService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Coupling(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.couplings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
