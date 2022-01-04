import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { API_CONFIG } from "src/app/config/api.config";
import { Cadastro } from "src/app/model/cadastro";
import { StorageService } from "../storage.service";


@Injectable()
export class ClienteService {

    constructor (public http : HttpClient, public storage: StorageService) {

    }

    findByEmail(email : String) {
        let token = this.storage.getLocalUser().token;
        let authHeader = new HttpHeaders({'Authorization' : 'Bearer ' + token});
        return this.http.get(`${API_CONFIG.baseUrl}/clientes/email?value=${email}`,{'headers' : authHeader});
    }

    Insert(obj : Cadastro) {
        return this.http.post(`${API_CONFIG.baseUrl}/clientes`, obj, {
            observe: 'response',
            responseType: 'text'
        })
    }
}