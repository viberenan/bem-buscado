import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login.component';
import { LogarComponent } from './components/logar.component';

const LoginRoutes: Routes = [{
    path: 'login', component: LogarComponent, children: [{path: '', component: LoginComponent}]   
}
];

@NgModule({
    imports: [RouterModule.forChild(LoginRoutes)],
    exports: [RouterModule]
})

export class LoginRoutingModule {
}
