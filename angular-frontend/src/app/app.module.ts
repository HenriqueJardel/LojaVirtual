import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConteinerComponent } from './components/template/conteiner/conteiner.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { HeaderComponent } from './components/template/header/header.component';
import { FooterComponent } from './components/template/footer/footer.component';
import { AuthService } from './services/auth.service';
import { StorageService } from './services/storage.service';
import { ClienteService } from './services/domain/cliente.service';
import { ProdutoService } from './services/domain/produto.service';
import { CarrinhoService } from './services/domain/carrinho.service';
import { PedidoService } from './services/domain/pedido.service';
import { HomeComponent } from './pages/home/home.component';
import { ProdutosComponent } from './pages/produtos/produtos.component';
import { LoginComponent } from './pages/login/login.component';
import { DetalhesComponent } from './pages/detalhes/detalhes.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { CarrinhoComponent } from './pages/carrinho/carrinho.component';
import { SobreComponent } from './pages/sobre/sobre.component';
import { EstadoService } from './services/domain/estado.service';
import { AlertService } from './services/alert.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ConteinerComponent,
    HomeComponent,
    ProdutosComponent,
    LoginComponent,
    DetalhesComponent,
    PerfilComponent,
    CarrinhoComponent,
    SobreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthService, 
    StorageService,
    ClienteService,
    ProdutoService,
    CarrinhoService,
    PedidoService,
    EstadoService,
    AlertService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
