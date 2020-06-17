import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmpresaComponent } from './components/empresa.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { EmpresaRoutingModule } from './empresa-routing.module';
import { ModalModule } from 'ngx-bootstrap/modal';
import { RouterModule } from '@angular/router';
import { CadastroEmpresaService } from './services/cadastro-empresa.service';


@NgModule({
  declarations: [EmpresaComponent],
  imports: [
    HttpClientModule,
    ReactiveFormsModule,
    EmpresaRoutingModule,
    ModalModule.forRoot(),
    CommonModule, 
    RouterModule
  ],
  providers: [
    CadastroEmpresaService
  ]
})

export class EmpresaModule { }
