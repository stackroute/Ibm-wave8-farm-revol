import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFarmerOrdersComponent } from './view-farmer-orders.component';

describe('ViewFarmerOrdersComponent', () => {
  let component: ViewFarmerOrdersComponent;
  let fixture: ComponentFixture<ViewFarmerOrdersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFarmerOrdersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFarmerOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
