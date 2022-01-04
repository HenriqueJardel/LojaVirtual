import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { ClienteService } from 'src/app/services/domain/cliente.service';
import { StorageService } from 'src/app/services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  

  constructor(public storage : StorageService, public clientService : ClienteService, public router : Router) { 
  }

  cliente : ClienteDTO;
  queryField = new FormControl();
  sugetionArray : String[];
  logado : boolean = false;
 
  Sugestion : String[] = [
  "Processador Intel",
  "Processador Intel Core i3",
  "Processador Intel Core i5",
  "Processador Intel Core i7",
  "Processador Intel Core i9",
  "Processador AMD",
  "Processador AMD Ryzen 5",
  "Processador AMD Ryzen 7",
  "Memoria Corsair Vengeance 4GB",
  "Memoria Crucial Ballistix 8GB",
  "Memoria G.Skill Trident Z RGB 16GB",
  "Memoria Team Group T-Force RGB 8GB",
  "Placa de Video NVIDIA",
  "Placa de Video ASUS",
  "Placa de Video EVGA",
  "Placa de Video Asrock Phantom Gaming D Radeon RX570",
  "Placa de Vídeo Asus Dual AMD Radeon RX 5700",
  "Placa de Vídeo MSI NVIDIA GeForce GTX 1660 Ti",
  "Placa de Vídeo EVGA NVIDIA GeForce GTX 1650 KO ULTRA",
  "Placa Mãe Biostar",
  "Placa Mãe Gigabyte",
  "Placa Mãe MSI",
  "Placa Mae Biostar Racing Z490GTN DDR4 Socket",
  "Placa Mae AsRock Z490 Taichi DDR4 Socket LGA1200",
  "Placa Mae Gigabyte B550 Gaming X DDR4 Socket AM4",
  "HD Seagate",
  "HD Toshiba",
  "HD WD",
  "HD Seagate SkyHawk 10TB 3.5Sata III 6GB/s",
  "HD WD Purple 3TB 3.5 Sata III 6GB/s",
  "HD Toshiba P300 1TB 3.5 Sata III 6GB/s",
  "Monitor Samsung 31,5'' LED Full HD Curvo Preto",
  "Monitor Gamer Asus Tuf 31,5'' 1ms 144Hz",
  "Monitor Acer 21.5 Pol. LED Full HD 5ms"
  ];
  
  ngOnInit(): void {
    if (this.storage.getLocalUser() != null) {
      this.logado = true;
      this.clientService.findByEmail(this.storage.getLocalUser().email).subscribe(response => {
        this.cliente = response as ClienteDTO;
      })
    }
      
    this.queryField.valueChanges.subscribe(response => {
      this.sugetionArray = this.Sugestion.filter(data => {
        return data.toLocaleLowerCase().startsWith(response.toLocaleLowerCase());
      })
    })
      
    this.storage.emitEvent.subscribe(email => {
      this.clientService.findByEmail(email).subscribe(response => {
        this.cliente = response as ClienteDTO;
        this.logado = true;
      });
    });
  }

  Search() {
    if (this.queryField.value.length != null) {
      this.router.navigateByUrl('produtos?nome=' + this.queryField.value); 
    }
  }
}

