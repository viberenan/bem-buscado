import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { usuario } from './usuario.model';
import { CadastroUsuarioService } from './cadastro-usuario.service';
import { AlertModalService } from '../shared/alert-modal.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private alertService: AlertModalService,
    private CadastrousuarioService: CadastroUsuarioService,
  ) { }

  ngOnInit(): void {
    this.gerarForm();
  }

  gerarForm() {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  cadastrarUsuario() {
    if (this.form.invalid) {
      return;
    }
    const usuario: usuario = this.form.value;
    this.CadastrousuarioService.cadastrarUsuario(usuario)
      .subscribe(
        data => {
          const msg: string = "Cadastro realizado com sucesso.";
          this.alertService.showAlertSuccess(msg);
          this.router.navigate(['/login']);
        },
        err => {
          let msg: string = "Tente novamente em instantes.";
          if (err.status == 400) {
            msg = err.error.errors.join(' ');
          }
          this.alertService.showAlertDanger(msg);
        }
      );
    return false;
  }
}
