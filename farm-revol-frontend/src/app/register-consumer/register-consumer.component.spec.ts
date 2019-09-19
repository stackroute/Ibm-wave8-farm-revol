import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterConsumerComponent } from './register-consumer.component';

describe('RegisterConsumerComponent', () => {
  let component: RegisterConsumerComponent;
  let fixture: ComponentFixture<RegisterConsumerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterConsumerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterConsumerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
