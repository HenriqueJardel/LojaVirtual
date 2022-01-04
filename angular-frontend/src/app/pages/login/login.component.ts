import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { credenciaisDTO } from 'src/app/model/credenciais.dto';
import { AuthService } from 'src/app/services/auth.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ClienteService } from 'src/app/services/domain/cliente.service';
import { EstadoService } from 'src/app/services/domain/estado.service';
import { EstadoDTO } from 'src/app/model/estado.dto';
import { CidadeDTO } from 'src/app/model/cidade.dto';
import { Cadastro } from 'src/app/model/cadastro';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  tipo : number = 1;
  cidadeId : Number = 0;
  estados : EstadoDTO[];
  cidades: CidadeDTO[];
  cadastro : Cadastro;
  creds : credenciaisDTO = {
    email: "",
    senha: ""
  };

  loginEmail = new FormGroup({
    formEmail: new FormControl('',[
      Validators.required,
      Validators.email,
      Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")])
  });

  cadastroForm = new FormGroup({
    nome: new FormControl('henrique', [Validators.required, Validators.minLength(3),Validators.maxLength(100)]),
    sobreNome: new FormControl('jardel',[Validators.required, Validators.minLength(3),Validators.maxLength(100)]),
    cpfOuCnpj: new FormControl('05673201117',[Validators.required, Validators.minLength(11),Validators.maxLength(14)]),
    email: new FormControl('henrique.jt2016@gmail.com', [Validators.required, Validators.email,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
    senha: new FormControl('12345678',[Validators.required,Validators.minLength(8),Validators.maxLength(25)]),
    logradouro: new FormControl('Rua da rua', Validators.required),
    bairro: new FormControl('Bairro do bairro', Validators.required),
    numero: new FormControl('100',Validators.required),
    cep: new FormControl('79013454'),
    complemento: new FormControl(''),
    telefone1: new FormControl('67992066976', Validators.required),
    telefone2: new FormControl('')
    }) 
  

  constructor(private auth: AuthService , private router: Router, private clienteService : ClienteService, 
    private estadoService : EstadoService, private alert : AlertService) {
  }
  
  ngOnInit(): void {
    this.estadoService.findAll().subscribe(response => {
      this.estados = response;
    });
  }

  Login() {
    this.auth.authenticate(this.creds).subscribe(response => {
        this.auth.successfulLogin(response.headers.get("Authorization"),10);
        this.router.navigateByUrl('');
    });
  }

  doLogin() {
    if(!this.loginEmail.invalid) {
      this.Login();
    }
    else {
      window.alert('E-mail ou senha invalidos!')
    }
  }

  cadastrar() {
    if (!this.cadastroForm.invalid && this.cidadeId != 0) {
      if (this.cpfOuCnpj.value?.length == 14) {
        this.tipo = 2;
      }
      
      this.cadastro = {
        nome: this.Nome.value,
        sobreNome: this.Sobre.value,
        email: this.emailCadastro.value,
        senha: this.Senha.value,
        cpfOuCnpj: this.cpfOuCnpj.value,
        tipo: this.tipo,
        telefone1: this.Telefone.value,
        telefone2: this.Telefone2.value,
        logradouro: this.Logradouro.value,
        bairro: this.Bairro.value,
        numero: this.Numero.value,
        complemento: this.Complemento.value,
        cep: this.Cep.value,
        cidadeId: this.cidadeId
      }

      this.clienteService.Insert(this.cadastro).subscribe(response => {
        this.creds.email = this.cadastro.email;
        this.creds.senha = this.cadastro.senha;
        this.Login();
      });
    }
    else {
      this.alert.Error("Verifique se todos os campos estão preenchidos!")
    }
  }

  selecionarEstado(indice : number) {
    this.cidades = this.estados[indice].cidades;
  }

  selecionarCidade(id) {
    this.cidadeId = parseInt(id); 
  }

  get emailLogin(){
    return this.loginEmail;
  }

  get emailCadastro() {
    return this.cadastroForm.get('email');
  }

  get Nome() {
    return this.cadastroForm.get('nome');
  }

  get Sobre() {
    return this.cadastroForm.get('sobreNome');
  }

  get cpfOuCnpj() {
    return this.cadastroForm.get('cpfOuCnpj');
  }

  get Senha() {
    return this.cadastroForm.get('senha');
  }

  get Logradouro() {
    return this.cadastroForm.get('logradouro');
  }

  get Bairro() {
    return this.cadastroForm.get('bairro');
  }

  get Numero() {
    return this.cadastroForm.get('numero');
  }

  get Complemento() {
    return this.cadastroForm.get('complemento');
  }

  get Cep() {
    return this.cadastroForm.get('cep');
  }

  get Telefone() {
    return this.cadastroForm.get('telefone1');
  }

  get Telefone2() {
    return this.cadastroForm.get('telefone2');
  }
  
}
