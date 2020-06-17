import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuarioComponent } from './components/usuario.component';

const CadastroUsuario: Routes = [{
    path: 'usuario', component: UsuarioComponent, children:[{path: '', component: UsuarioComponent}]
}
];

@NgModule({
    imports: [RouterModule.forChild(CadastroUsuario)],
    exports: [RouterModule]
})

export class UsuarioRoutingModule {
}