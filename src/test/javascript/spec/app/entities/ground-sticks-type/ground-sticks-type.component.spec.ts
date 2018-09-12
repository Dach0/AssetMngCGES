/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundSticksTypeComponent } from 'app/entities/ground-sticks-type/ground-sticks-type.component';
import { GroundSticksTypeService } from 'app/entities/ground-sticks-type/ground-sticks-type.service';
import { GroundSticksType } from 'app/shared/model/ground-sticks-type.model';

describe('Component Tests', () => {
    describe('GroundSticksType Management Component', () => {
        let comp: GroundSticksTypeComponent;
        let fixture: ComponentFixture<GroundSticksTypeComponent>;
        let service: GroundSticksTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundSticksTypeComponent],
                providers: []
            })
                .overrideTemplate(GroundSticksTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroundSticksTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroundSticksTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new GroundSticksType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.groundSticksTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
