import { Component, OnInit, EventEmitter } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Login } from '../model/login.model'
import { Router } from '@angular/router';
import { AlertModalService } from 'src/app/shared/alert-modal.service';
import { LoginService } from '../services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private alertService: AlertModalService,
  ) { }

  ngOnInit(): void {
    this.gerarForm();
  }

  gerarForm() {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  logar() {
    if (this.form.invalid) {
      const msg: string = "Dados inválidos";
      this.alertService.showAlertDanger(msg);
      return;
    }
    const login: Login = this.form.value;
    this.loginService.logar(login)
      .subscribe(
        data => {
          localStorage['token'] = data['data']['token'];
          this.router.navigate(['/home'])
        },
        err => {
          let msg: string = "Tente novamente em instantes";
          if (err['status'] == 401) {
            msg = "Email ou Senha inválido(s)."
          }
          this.alertService.showAlertDanger(msg);
        }
      )
  }

}
