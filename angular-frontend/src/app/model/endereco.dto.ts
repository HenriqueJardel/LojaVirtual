import { CidadeDTO } from "./cidade.dto";

export interface EnderecoDTO {
    id: String,
    logradouro: String, 
    numero: String,
    bairro: String,
    complemento: String,
    cep: String,
    cidade: CidadeDTO
}