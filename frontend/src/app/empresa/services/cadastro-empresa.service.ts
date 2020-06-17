import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { empresa } from '../models/empresa.model';
import { Observable } from 'rxjs';
import { environment as env } from '../../../environments/environment';

@Injectable()
export class CadastroEmpresaService {
  private readonly PATH: string = "empresa";

  constructor(private http: HttpClient ) { }

  cadastrarEmpresa(empresa: empresa ):Observable<any>{
    return this.http.post(env.baseApiUrl+this.PATH, empresa);
  }
}
