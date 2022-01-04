import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarrinhoComponent } from './pages/carrinho/carrinho.component';
import { DetalhesComponent } from './pages/detalhes/detalhes.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { ProdutosComponent } from './pages/produtos/produtos.component';
import { SobreComponent } from './pages/sobre/sobre.component';

const routes: Routes = [
  {
  path: "",
  component: HomeComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path:"produtos",
    component: ProdutosComponent
  },
  {
    path:"detalhes",
    component: DetalhesComponent
  },
  {
    path:"carrinho",
    component: CarrinhoComponent
  },
  {
    path:"perfil",
    component: PerfilComponent
  },
  {
    path:"sobre",
    component: SobreComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
