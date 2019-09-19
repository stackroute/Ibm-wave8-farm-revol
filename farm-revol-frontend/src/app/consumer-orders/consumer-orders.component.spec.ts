import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerOrdersComponent } from './consumer-orders.component';

describe('ConsumerOrdersComponent', () => {
  let component: ConsumerOrdersComponent;
  let fixture: ComponentFixture<ConsumerOrdersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsumerOrdersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsumerOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
