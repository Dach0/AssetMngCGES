/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ManufacturerComponent } from 'app/entities/manufacturer/manufacturer.component';
import { ManufacturerService } from 'app/entities/manufacturer/manufacturer.service';
import { Manufacturer } from 'app/shared/model/manufacturer.model';

describe('Component Tests', () => {
    describe('Manufacturer Management Component', () => {
        let comp: ManufacturerComponent;
        let fixture: ComponentFixture<ManufacturerComponent>;
        let service: ManufacturerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ManufacturerComponent],
                providers: []
            })
                .overrideTemplate(ManufacturerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ManufacturerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ManufacturerService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Manufacturer(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.manufacturers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
