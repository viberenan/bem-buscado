import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioComponent } from './usuario.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';
import {CadastroUsuarioService} from './cadastro-usuario.service';



@NgModule({
  declarations: [UsuarioComponent],
  imports: [
    CommonModule,
    ModalModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [
    CadastroUsuarioService
  ]
})
export class UsuarioModule { }
