import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioComponent } from './components/usuario.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';
import {CadastroUsuarioService} from './services/cadastro-usuario.service';
import { RouterModule } from '@angular/router';
import { UsuarioRoutingModule } from './usuario-routing.module';



@NgModule({
  declarations: [UsuarioComponent],
  imports: [
    CommonModule,
    ModalModule.forRoot(),
    ReactiveFormsModule,
    UsuarioRoutingModule,
    RouterModule
  ],
  providers: [
    CadastroUsuarioService
  ]
})
export class UsuarioModule { }
