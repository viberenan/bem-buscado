import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CnpjValidator } from 'src/app/shared/validator/cnpj.validator';
import { empresa } from '../models/empresa.model';
import { CadastroEmpresaService } from '../services/cadastro-empresa.service';
import { AlertModalService } from 'src/app/shared/alert-modal.service';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {

  form: FormGroup;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private router: Router,
    private alertService: AlertModalService,
    private cadastroEmpresaService: CadastroEmpresaService,
  ) { }

  ngOnInit(): void {
    this.gerarForm();
  }

  gerarForm() {
    this.form = this.fb.group({
      razao_social: ['', [Validators.required]],
      cpnj: ['', [Validators.required, CnpjValidator]],
      cep: [null, [Validators.required]],
      endereco: [null, [Validators.required]],
      numero: ['', [Validators.required]],
      bairro: [null, [Validators.required]],
      cidade: [null, [Validators.required]],
      uf: [null, [Validators.required]],
      contato: ['', [Validators.required]],
      tipo_tel: [null, [Validators.required]]
    });
  }

  pesquisacep(cep) {
    cep = cep.replace(/\D/g, '');
    if (cep != "") {
      var validacep = /^[0-9]{8}$/;
      if (validacep.test(cep)) {
        this.http.get(`https://viacep.com.br/ws/${cep}/json`)
          .subscribe(data => this.popularDadosForm(data));
      }
    }
  }

  popularDadosForm(data) {
    this.form.patchValue({
      uf: data.uf,
      cep: data.cep,
      endereco: data.logradouro,
      bairro: data.bairro,
      cidade: data.localidade
    })
  }

  cadastrarEmpresa() {
    if (this.form.invalid) {
      return;
    }
    const empresa: empresa = this.form.value;
    this.cadastroEmpresaService.cadastrarEmpresa(empresa)
    .subscribe(
      data=>{
        const msg: string = "Cadastro Realizado com sucesso.";
        this.alertService.showAlertSuccess(msg);
        this.router.navigate(['/login']);
      },
      err => {
        let msg: string = "Tente novamente em instantes.";
        if (err.status == 400){
          msg = err.error.errros.join(' ');
        } 
        this.alertService.showAlertDanger(msg);
      }
    );
    return false;
  }

}




