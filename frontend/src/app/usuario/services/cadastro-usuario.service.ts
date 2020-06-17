import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { usuario } from '../model/usuario.model';
import { environment as env } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable()
export class CadastroUsuarioService {

  private readonly PATH: string = 'usuario';

  constructor(private http: HttpClient) { }

  cadastrarUsuario(usuario: usuario): Observable<any> {
    return this.http.post(env.baseUrl + this.PATH, usuario);
  }
}
