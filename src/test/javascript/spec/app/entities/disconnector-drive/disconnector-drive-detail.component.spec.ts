/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorDriveDetailComponent } from 'app/entities/disconnector-drive/disconnector-drive-detail.component';
import { DisconnectorDrive } from 'app/shared/model/disconnector-drive.model';

describe('Component Tests', () => {
    describe('DisconnectorDrive Management Detail Component', () => {
        let comp: DisconnectorDriveDetailComponent;
        let fixture: ComponentFixture<DisconnectorDriveDetailComponent>;
        const route = ({ data: of({ disconnectorDrive: new DisconnectorDrive(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorDriveDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DisconnectorDriveDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DisconnectorDriveDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.disconnectorDrive).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
