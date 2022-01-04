import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { API_CONFIG } from "src/app/config/api.config";
import { PedidoDTO } from "src/app/model/pedido.dto";
import { StorageService } from "../storage.service";

@Injectable()
export class PedidoService {

    constructor (public http : HttpClient , public storage : StorageService) {

    }
    
    Insert(obj : PedidoDTO) {
        let token = this.storage.getLocalUser().token;
        let authHeader = new HttpHeaders({'Authorization' : 'Bearer ' + token});
        return this.http.post(`${API_CONFIG.baseUrl}/pedidos`, obj,{
            'headers' : authHeader,
            observe: 'response',
            responseType: 'text'
        })
    }
}