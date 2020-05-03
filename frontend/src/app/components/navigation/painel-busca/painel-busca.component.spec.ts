import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PainelBuscaComponent } from './painel-busca.component';

describe('PainelBuscaComponent', () => {
  let component: PainelBuscaComponent;
  let fixture: ComponentFixture<PainelBuscaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PainelBuscaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PainelBuscaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
