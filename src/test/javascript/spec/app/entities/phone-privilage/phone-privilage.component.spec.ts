/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { PhonePrivilageComponent } from 'app/entities/phone-privilage/phone-privilage.component';
import { PhonePrivilageService } from 'app/entities/phone-privilage/phone-privilage.service';
import { PhonePrivilage } from 'app/shared/model/phone-privilage.model';

describe('Component Tests', () => {
    describe('PhonePrivilage Management Component', () => {
        let comp: PhonePrivilageComponent;
        let fixture: ComponentFixture<PhonePrivilageComponent>;
        let service: PhonePrivilageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [PhonePrivilageComponent],
                providers: []
            })
                .overrideTemplate(PhonePrivilageComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PhonePrivilageComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhonePrivilageService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PhonePrivilage(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.phonePrivilages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
