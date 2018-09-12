/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { ElementStatusDetailComponent } from 'app/entities/element-status/element-status-detail.component';
import { ElementStatus } from 'app/shared/model/element-status.model';

describe('Component Tests', () => {
    describe('ElementStatus Management Detail Component', () => {
        let comp: ElementStatusDetailComponent;
        let fixture: ComponentFixture<ElementStatusDetailComponent>;
        const route = ({ data: of({ elementStatus: new ElementStatus(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [ElementStatusDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ElementStatusDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ElementStatusDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.elementStatus).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
