import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { API_CONFIG } from "src/app/config/api.config";
import { ProdutoDTO } from "src/app/model/produto.dto";

@Injectable()
export class ProdutoService {

    constructor(public http: HttpClient) {

    }

    findByCategoria(id : String) {
        return this.http.get(`${API_CONFIG.baseUrl}/produtos/?categorias=${id}`);
    }

    findByName(nome : String, page : number) {
        return this.http.get(`${API_CONFIG.baseUrl}/produtos/?categorias=1,2,3,4,5,6,7&nome=${nome}&page=${page}`);
    }

    findById(id : String) {
        return this.http.get<ProdutoDTO>(`${API_CONFIG.baseUrl}/produtos/${id}`);
    }
}