/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { GroundSticksDriveDetailComponent } from 'app/entities/ground-sticks-drive/ground-sticks-drive-detail.component';
import { GroundSticksDrive } from 'app/shared/model/ground-sticks-drive.model';

describe('Component Tests', () => {
    describe('GroundSticksDrive Management Detail Component', () => {
        let comp: GroundSticksDriveDetailComponent;
        let fixture: ComponentFixture<GroundSticksDriveDetailComponent>;
        const route = ({ data: of({ groundSticksDrive: new GroundSticksDrive(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [GroundSticksDriveDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GroundSticksDriveDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroundSticksDriveDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.groundSticksDrive).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
