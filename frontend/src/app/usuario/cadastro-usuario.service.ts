import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { usuario } from './usuario.model';
import { Observable } from 'rxjs';

@Injectable()
export class CadastroUsuarioService {

  constructor(private http: HttpClient) { }

  cadastrarUsuario(usuario: usuario): Observable<any> {
    return this.http.post("http://localhost:8080/usuario/", usuario);
  }
}
