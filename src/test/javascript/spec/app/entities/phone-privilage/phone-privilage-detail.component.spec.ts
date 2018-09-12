/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { PhonePrivilageDetailComponent } from 'app/entities/phone-privilage/phone-privilage-detail.component';
import { PhonePrivilage } from 'app/shared/model/phone-privilage.model';

describe('Component Tests', () => {
    describe('PhonePrivilage Management Detail Component', () => {
        let comp: PhonePrivilageDetailComponent;
        let fixture: ComponentFixture<PhonePrivilageDetailComponent>;
        const route = ({ data: of({ phonePrivilage: new PhonePrivilage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [PhonePrivilageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PhonePrivilageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PhonePrivilageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.phonePrivilage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
