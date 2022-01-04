import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { EnderecoDTO } from 'src/app/model/endereco.dto';
import { PedidoDTO } from 'src/app/model/pedido.dto';
import { ClienteService } from 'src/app/services/domain/cliente.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  constructor(public formBuilder : FormBuilder, public clienteService : ClienteService, public storage : StorageService) { }

  aba : number = 0;
  enderecos : EnderecoDTO[];
  cliente : ClienteDTO;
  alterarDados : FormGroup;
  
  ngOnInit(): void {
    if(this.storage.getLocalUser() != null) {
    this.clienteService.findByEmail(this.storage.getLocalUser().email).subscribe(response => {
      this.cliente = response as ClienteDTO;
      this.enderecos = response['enderecos'];

      /*
      this.alterarDados = this.formBuilder.group({
        nome: new FormControl(this.cliente.nome,[]),
        sobreNome: new FormControl(this.cliente.sobreNome,[]),
        cpfOuCnpj: new FormControl(this.cliente.cpfOuCnpj,[]),
        email: new FormControl(this.cliente.email,[]),
        logradouro: new FormControl(this.enderecos[0].logradouro,[]),
        numero: new FormControl(this.enderecos[0].numero,[]),
        bairro: new FormControl(this.enderecos[0].bairro,[]),
        complemento: new FormControl(this.enderecos[0].complemento,[]),
        cep: new FormControl(this.enderecos[0].cep,[]),
        cidade: new FormControl(this.enderecos[0].cidade.nome,[]),
        estado: new FormControl(this.enderecos[0].cidade.estado.nome,[])
      })
      */
      console.log(this.enderecos[0].id)
      });

    }
  }
  
  pedidos : Object[] = [
    {
      id: "1",
      data: "30/09/2015",
      valorTotal: 2195.00
    },
    {
      id: "2",
      data: "30/09/2019",
      valorTotal: 9595.95
    },
    {
      id: "5",
      data: "30/09/2019",
      valorTotal: 9595.95
    },
    {
      id: "6",
      data: "30/09/2019",
      valorTotal: 9595.95
    },
    {
      id: "7",
      data: "30/09/2019",
      valorTotal: 9595.95
    },
    {
      id: "8",
      data: "30/09/2019",
      valorTotal: 9595.95
    },
    {
      id: "9",
      data: "30/09/2019",
      valorTotal: 9595.95
    },
    {
      id: "10",
      data: "30/09/2019",
      valorTotal: 9595.95
    }
  ]

  selecionarAba(indice : number) {
    this.aba = indice;
  }

  selecionarEndereco(indice : number) {
    /*
    this.alterarDados.get('logradouro').setValue(this.enderecos[indice].logradouro);
    this.alterarDados.get('numero').setValue(this.enderecos[indice].numero);
    this.alterarDados.get('bairro').setValue(this.enderecos[indice].bairro);
    this.alterarDados.get('complemento').setValue(this.enderecos[indice].complemento);
    this.alterarDados.get('cep').setValue(this.enderecos[indice].cep);
    this.alterarDados.get('cidade').setValue(this.enderecos[indice].cidade.nome);
    this.alterarDados.get('estado').setValue(this.enderecos[indice].cidade.estado.nome);
    */
  }
  
}
