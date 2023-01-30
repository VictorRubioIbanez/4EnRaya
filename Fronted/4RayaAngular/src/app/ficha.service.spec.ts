import { TestBed } from '@angular/core/testing';

import { FichaService } from './ficha.service';

describe('FichaService', () => {
  let service: FichaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FichaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
