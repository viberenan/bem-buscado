import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmpresaComponent } from './components/empresa.component';

const LoginRoutes: Routes = [{
    path: 'empresa', component: EmpresaComponent, children: [{path: '', component: EmpresaComponent}]   
}
];

@NgModule({
    imports: [RouterModule.forChild(LoginRoutes)],
    exports: [RouterModule]
})

export class EmpresaRoutingModule {
}
