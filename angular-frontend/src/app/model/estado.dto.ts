import { CidadeDTO } from "./cidade.dto";

export interface EstadoDTO {
    id: String,
    nome: String,
    cidades: CidadeDTO[]
}