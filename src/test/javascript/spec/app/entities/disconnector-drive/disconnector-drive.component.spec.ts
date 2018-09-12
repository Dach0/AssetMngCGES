/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AssetMenagementCgesTestModule } from '../../../test.module';
import { DisconnectorDriveComponent } from 'app/entities/disconnector-drive/disconnector-drive.component';
import { DisconnectorDriveService } from 'app/entities/disconnector-drive/disconnector-drive.service';
import { DisconnectorDrive } from 'app/shared/model/disconnector-drive.model';

describe('Component Tests', () => {
    describe('DisconnectorDrive Management Component', () => {
        let comp: DisconnectorDriveComponent;
        let fixture: ComponentFixture<DisconnectorDriveComponent>;
        let service: DisconnectorDriveService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AssetMenagementCgesTestModule],
                declarations: [DisconnectorDriveComponent],
                providers: []
            })
                .overrideTemplate(DisconnectorDriveComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DisconnectorDriveComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DisconnectorDriveService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DisconnectorDrive(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.disconnectorDrives[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
