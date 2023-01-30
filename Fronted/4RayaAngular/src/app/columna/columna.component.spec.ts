import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColumnaComponent } from './columna.component';

describe('ColumnaComponent', () => {
  let component: ColumnaComponent;
  let fixture: ComponentFixture<ColumnaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ColumnaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColumnaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
