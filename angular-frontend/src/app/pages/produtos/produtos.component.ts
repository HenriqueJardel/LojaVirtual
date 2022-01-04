import { Component, OnInit } from '@angular/core';
import { API_CONFIG } from 'src/app/config/api.config';
import { ProdutoDTO } from 'src/app/model/produto.dto';
import { ProdutoService } from 'src/app/services/domain/produto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CarrinhoService } from 'src/app/services/domain/carrinho.service';
import { StorageService } from 'src/app/services/storage.service';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  constructor(public service : ProdutoService, public routerParam : ActivatedRoute, 
              public carrinhoService : CarrinhoService, public router : Router,
              public storage : StorageService, public alert : AlertService) { 
  }
  
  items : ProdutoDTO[];
  pages : number [];
  current : number = 0;
  currentName : String;

  ngOnInit(): void {
    
    const id = this.routerParam.snapshot.queryParams['id'];
    if (id != undefined) {
      this.service.findByCategoria(id).subscribe(response => {
        this.items = response['content'];
        this.pages = this.counter(response['totalPages']);
        this.loadImageUrls();
      })
    }

    this.routerParam.queryParams.subscribe(response => {
      this.currentName = response.nome;
      if (response.nome != undefined) {
        this.service.findByName(response.nome, this.current).subscribe(response => {
          this.items = response['content'];
          this.pages = this.counter(response['totalPages']);
          this.loadImageUrls();
        })
      }
    })
  }

  loadImageUrls() {
    for (let i = 0; i <this.items.length; i++){
      let item = this.items[i];
      this.service.findById(item.id).subscribe(response => {
          item.imagem = `${API_CONFIG.bucketBaseUrl}/Produtos/prod-${item.id}.jpg`;
      })
    }
  }

  addCarrinho(produto : ProdutoDTO) {
      if (this.storage.getLocalUser() != null) {
        this.carrinhoService.addProduto(produto);
        this.alert.Sucess("Item colocado no carrinho!")
      }
      else{
        this.router.navigateByUrl('login');  
      }
  }

  comprar(produto : ProdutoDTO) {
    this.addCarrinho(produto)
    this.router.navigateByUrl('carrinho');
  }

  counter(i : number) {
    return new Array(i);
  }

  Pagination(page : number) {
    if (this.pages.length > 1) {
      if (page == null) {
        if (this.current < this.pages.length - 1)
          this.current++;
      }
      else if (page == -1) {
        if (this.current > 0)
          this.current--;
      }
      else {
        this.current = page;
      }
      this.service.findByName(this.currentName, this.current).subscribe(response => {
        this.items = response['content'];
        this.loadImageUrls();
      });
    }
  }
}
