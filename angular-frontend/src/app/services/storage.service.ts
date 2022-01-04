import { EventEmitter, Injectable } from "@angular/core";
import { STORAGE_KEYS } from "../config/storage_keys.config";
import { Carrinho } from "../model/carrinho";
import { LocalUser } from "../model/local_user";

@Injectable()
export class StorageService {

    emitEvent = new EventEmitter<String>();

    getLocalUser() : LocalUser {
        this.localStorageExpires();
        let usr = localStorage.getItem(STORAGE_KEYS.localUser);
        if (usr == null) {
            return null;
        }
        else {
            return JSON.parse(usr);
        }
    }

    setLocalUser(obj : LocalUser) {
        if (obj == null) {
            localStorage.removeItem(STORAGE_KEYS.localUser);
        }
        else {
            localStorage.setItem(STORAGE_KEYS.localUser, JSON.stringify(obj));
            this.emitEvent.emit(obj.email);
        }
    }

    getCarrinho() : Carrinho {
        let car = localStorage.getItem(STORAGE_KEYS.carrinho);
        if (car == null) {
            return null
        }
        else {
            return JSON.parse(car);
        }
    }

    setCarrinho(obj : Carrinho) {   
        if (obj == null) {
            localStorage.removeItem(STORAGE_KEYS.carrinho);
        }
        else {
            localStorage.setItem(STORAGE_KEYS.carrinho, JSON.stringify(obj));
        }
    }

    localStorageExpires() {
        let toRemove = [],                     
        currentDate = new Date().getTime(); 

        for (let i = 0, j = localStorage.length; i < j; i++) {
            let key = localStorage.key(i),
            itemValue = localStorage.getItem(key);
            
            if (itemValue && /^\{(.*?)\}$/.test(itemValue)) {
                let current = JSON.parse(itemValue);
            
                if (current.expires && current.expires <= currentDate) {
                    toRemove.push(key);
                }
            }
        }

        for (var i = toRemove.length - 1; i >= 0; i--) {
            localStorage.removeItem(toRemove[i]);
        }
    }
}   