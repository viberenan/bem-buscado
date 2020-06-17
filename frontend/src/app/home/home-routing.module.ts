import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';


const home: Routes = [{
    path: 'home', component: HomeComponent, children: [{ path: '', component: HomeComponent }]
}
];

@NgModule({
    imports: [RouterModule.forChild(home)],
    exports: [RouterModule]
})

export class HomeRoutingModule {
}
