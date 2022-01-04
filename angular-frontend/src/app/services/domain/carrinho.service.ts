import { Injectable } from "@angular/core";
import { Carrinho } from "src/app/model/carrinho";
import { ProdutoDTO } from "src/app/model/produto.dto";
import { StorageService } from "../storage.service";


@Injectable()
export class CarrinhoService {
    constructor(public storage : StorageService) {

    }

    criarOuLimparCarrinho() : Carrinho {
        let carrinho : Carrinho = {itens: []}
        this.storage.setCarrinho(carrinho);
        return carrinho;
    }

    getCarrinho() : Carrinho {
        let carrinho : Carrinho = this.storage.getCarrinho();
        if (carrinho == null) {
            carrinho = this.criarOuLimparCarrinho();
        }
        return carrinho;
    }

    addProduto(produto : ProdutoDTO) : Carrinho{
        let carrinho = this.getCarrinho();
        let pos = carrinho.itens.findIndex(x => {
            x.produto.id == produto.id
        });
        
        if (pos == -1) {
            carrinho.itens.push({quantidade: 1, produto: produto});
        }
        
        this.storage.setCarrinho(carrinho);
        return carrinho;
    }
}