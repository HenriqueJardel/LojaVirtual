import { Injectable } from "@angular/core";
import { credenciaisDTO } from "../model/credenciais.dto";
import { HttpClient } from '@angular/common/http'
import { API_CONFIG } from "../config/api.config";
import { LocalUser } from "../model/local_user";
import { StorageService } from "./storage.service";
import { JwtHelperService } from "@auth0/angular-jwt";
import { catchError } from "rxjs/operators";
import { EMPTY, Observable } from "rxjs";
import { AlertService } from "./alert.service";

@Injectable()
export class AuthService {

    helper : JwtHelperService = new JwtHelperService();

    constructor(public http : HttpClient, public storage : StorageService, public alertService : AlertService) {
    }

    authenticate (creds : credenciaisDTO) {
        return this.http.post(`${API_CONFIG.baseUrl}/login`,creds,{
            observe: 'response',
            responseType: 'text'
        }).pipe(catchError((e) => this.errorHandler(e)));
    }

    successfulLogin(authorization : String, expires : number) {
        let tok = authorization.substring(7);
        let user : LocalUser = {
            token : tok,
            email: this.helper.decodeToken(tok).sub,
            expires: new Date().getTime() + (60000 * expires)
        }
        this.storage.setLocalUser(user);
    }

    logout() {
        this.storage.setLocalUser(null);
    }

    errorHandler(e: any) : Observable<any> {
        this.alertService.Error("E-mail ou senha incorreta!");
        return EMPTY;
    }
}