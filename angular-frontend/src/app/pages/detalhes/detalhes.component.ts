import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { API_CONFIG } from 'src/app/config/api.config';
import { ProdutoDTO } from 'src/app/model/produto.dto';
import { CarrinhoService } from 'src/app/services/domain/carrinho.service';
import { ProdutoService } from 'src/app/services/domain/produto.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-detalhes',
  templateUrl: './detalhes.component.html',
  styleUrls: ['./detalhes.component.css']
})
export class DetalhesComponent implements OnInit {

  item : ProdutoDTO;
  especificacoes : String [];
  imagemAtual : number = 0;

  constructor(public produtoService : ProdutoService , public routerParam : ActivatedRoute, public carrinhoService : CarrinhoService,
    public storage : StorageService, public router : Router) { }

  ngOnInit(): void {
    const id = this.routerParam.snapshot.queryParams['id'];
    if (id != undefined) {
        this.produtoService.findById(id).subscribe(response => {
          this.item = response;
          this.item.imagem = `${API_CONFIG.bucketBaseUrl}/Produtos/prod-${this.item.id}.jpg`;
          this.especificacoes = this.item.descricao.split(';');
          console.log(this.especificacoes);
        })
    }
  }

  changeImage(indice : number) {
    this.imagemAtual = indice;
  }

  getImage() {
    if(this.imagemAtual == 0) {
      return this.item.imagem;
    }
    else {
      return 'https://s3-sa-east-1.amazonaws.com/curso.spring.ionic.henrique/Produtos/White.jpg';
    } 
  }

  addCarrinho() {
    if (this.storage.getLocalUser() != null) {
      this.carrinhoService.addProduto(this.item);
      this.router.navigateByUrl('carrinho'); 
    }
    else{
      window.alert("é necessario estar logado!")
      this.router.navigateByUrl('login');  
    }
}
}
