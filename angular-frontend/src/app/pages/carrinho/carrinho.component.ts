import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { API_CONFIG } from 'src/app/config/api.config';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { EnderecoDTO } from 'src/app/model/endereco.dto';
import { Item } from 'src/app/model/item';
import { PedidoDTO } from 'src/app/model/pedido.dto';
import { AlertService } from 'src/app/services/alert.service';
import { CarrinhoService } from 'src/app/services/domain/carrinho.service';
import { ClienteService } from 'src/app/services/domain/cliente.service';
import { PedidoService } from 'src/app/services/domain/pedido.service';
import { ProdutoService } from 'src/app/services/domain/produto.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {

  constructor(public carrinho : CarrinhoService , public produtoService : ProdutoService, 
              public router : Router, public storage : StorageService, public clienteService: ClienteService,
              public pedidoService: PedidoService, public alert : AlertService) {}

  items : Item[];
  total : number = 0;
  cliente : ClienteDTO;
  enderecos : EnderecoDTO [];
  pedido : PedidoDTO;
  etapa : number = 0;
  pagamento : number = 0;
  parcelas : number = 0;
  enderecoSelecionado : number = -1;
  
  ngOnInit(): void {
    
    if (this.storage.getLocalUser() != null) {
      this.clienteService.findByEmail(this.storage.getLocalUser().email).subscribe(response => {
        this.cliente = response as ClienteDTO;
        this.enderecos = response['enderecos'];
        let carrinho = this.carrinho.getCarrinho();
        this.items = carrinho.itens;
        this.valorTotal();
      });
    }
    else {
      this.router.navigateByUrl("login");
    }
  }

  loadImageUrls() {
    for (let i = 0; i <this.items.length; i++){
      let item = this.items[i];
      this.produtoService.findById(item.produto.id).subscribe(response => {
          item.produto.imagem = `${API_CONFIG.bucketBaseUrl}/Produtos/prod-${item.produto.id}.jpg`;
      })
    }
  }

  valorTotal() {
    this.total = 0;
    for (let i = 0 ; i < this.items.length; i++) {
      this.total = this.total + (this.items[i].produto.preco * this.items[i].quantidade);
    }
  }

  Limpar() {
    this.carrinho.criarOuLimparCarrinho();
    this.voltarIncio();
  }

  voltarIncio(){ 
    this.router.navigateByUrl("");
  }

  Navegar(indice : number) {
    if(this.items.length > 0 && this.etapa <= 3) {
      this.etapa += indice;
    }
    else {
      this.alert.Error("Você não possui itens no seu carrinho!")
    }
  }

  selecionarEndereco(indice : number) {
    this.enderecoSelecionado = indice;
  }

  selecionarPagamento(indice : number) {
    if (this.pagamento != indice) {
      this.pagamento = indice;
    }
  }

  selecionarParcelas(indice : number) {
    this.parcelas = indice;
  }

  aumetarQuantidade(index: number, indice : number) {
    this.items[index].quantidade += indice;
    
    if(this.items[index].quantidade == 0) {
      this.items[index].quantidade++;
    }
    
    this.valorTotal();
  }

  finalizar() {
    if (this.pagamento == 0) {
      this.pedido = {
        cliente: {
          id: this.cliente.id
        },
        enderecoDeEntrega: {
          id: this.enderecos[this.enderecoSelecionado].id
        },
        pagamento: {
          numeroDeParcelas: this.parcelas,
          '@type': "pagamentoComCartao"
        },
        itens: this.items.map(x => {
          return {quantidade: x.quantidade, produto: {id: x.produto.id}}
        })
      }
    }
    else {
      this.pedido = {
        cliente: {
          id: this.cliente.id
        },
        enderecoDeEntrega: {
          id: this.enderecos[this.enderecoSelecionado].id
        },
        pagamento: {
          '@type': "pagamentoComBoleto"
        },
        itens: this.items.map(x => {
          return {quantidade: x.quantidade, produto: {id: x.produto.id}}
        })
      }
    }
    
    this.pedidoService.Insert(this.pedido).subscribe(response => {
      this.Navegar(1);
    },
    error => {
        if (error.status == 403) {
            window.alert('erro!')
        }
    })
  }
  
}
