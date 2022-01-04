import { ItemPedidoDTO } from "./Item-pedido.dto";
import { PagamentoDTO } from "./pagamento.dto";
import { RefDTO } from "./Ref.dto";

export interface PedidoDTO {
    cliente: RefDTO,
    enderecoDeEntrega: RefDTO,
    pagamento: PagamentoDTO,
    itens: ItemPedidoDTO[]
}