import { TestBed } from '@angular/core/testing';

import { ConsumerEditProfileService } from './consumer-edit-profile.service';

describe('ConsumerEditProfileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConsumerEditProfileService = TestBed.get(ConsumerEditProfileService);
    expect(service).toBeTruthy();
  });
});
