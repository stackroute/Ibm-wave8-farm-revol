import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmereditprofileComponent } from './farmereditprofile.component';

describe('FarmereditprofileComponent', () => {
  let component: FarmereditprofileComponent;
  let fixture: ComponentFixture<FarmereditprofileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FarmereditprofileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmereditprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
