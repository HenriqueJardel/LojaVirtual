package com.jt.springboot2backend;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.jt.springboot2backend.domain.Categoria;
import com.jt.springboot2backend.domain.Cidade;
import com.jt.springboot2backend.domain.Cliente;
import com.jt.springboot2backend.domain.Endereco;
import com.jt.springboot2backend.domain.Estado;
import com.jt.springboot2backend.domain.ItemPedido;
import com.jt.springboot2backend.domain.Pagamento;
import com.jt.springboot2backend.domain.PagamentoComBoleto;
import com.jt.springboot2backend.domain.PagamentoComCartao;
import com.jt.springboot2backend.domain.Pedido;
import com.jt.springboot2backend.domain.Produto;
import com.jt.springboot2backend.domain.enums.EstadoPagamento;
import com.jt.springboot2backend.domain.enums.Perfil;
import com.jt.springboot2backend.domain.enums.TipoCliente;

import com.jt.springboot2backend.repositories.CategoriaRepository;
import com.jt.springboot2backend.repositories.CidadeRepository;
import com.jt.springboot2backend.repositories.ClienteRepository;
import com.jt.springboot2backend.repositories.EnderecoRepository;
import com.jt.springboot2backend.repositories.EstadoRepository;
import com.jt.springboot2backend.repositories.ItemPedidoRepository;
import com.jt.springboot2backend.repositories.PagamentoRepository;
import com.jt.springboot2backend.repositories.PedidoRepository;
import com.jt.springboot2backend.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class Springboot2BackendApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(Springboot2BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Processadores");
		Categoria cat2 = new Categoria(null, "Memorias");
		Categoria cat3 = new Categoria(null, "Placa de video");
		Categoria cat4 = new Categoria(null,"Placa mae");
		Categoria cat5 = new Categoria(null,"HD");
		Categoria cat6 = new Categoria(null,"SSD");
		Categoria cat7 = new Categoria(null,"Monitores");
		Categoria cat8 = new Categoria(null,"Acessorios");
		
		Produto p1 = new Produto(null, "Processador Intel Celeron G-5900, Cache 2MB, 3.4GHz, LGA 1200",2000.00,
		"Marca: Intel;Número de núcleos: 2;Threads: 2;Frequencia:3.4 GHz;Frequência turbo max: 4.30 GHz;Cache: 2MB;Soquete: FCLGA1200");
		Produto p2 = new Produto(null, "Processador Intel Core i3-10100F, Cache 6MB, 4.30 GHz, LGA 1200",800.00, 
		"Marca: Intel;Número de núcleos: 4;Threads: 8;Frequencia:3,60 GHz;Frequência turbo max: 4.30 GHz;Cache: 6MB;Soquete: FCLGA1200");
		Produto p3 = new Produto(null, "Processador Intel Core i5-9400F, Cache 9MB, 2.9GHz (4.1GHz Max Turbo), LGA 1151",1300.75,
		"Marca: Intel;Número de núcleos: 6;Threads: 6;Frequencia:2,90 GHz;Frequência turbo max: 4.10 GHz;Cache: 9MB;Soquete: FCLGA1151");
		Produto p4 = new Produto(null, "Processador Intel Core i7-10700K, Cache 16MB, 3.8GHz (5.1GHz Max Turbo), LGA 1200",300.00,
		"Marca: Intel;Número de núcleos: 8;Threads: 16;Frequencia:3,80 GHz;Frequência turbo max: 5.10 GHz;Cache: 16MB;Soquete: FCLGA1200");
		Produto p5 = new Produto(null, "Processador Intel Core i9-9900K, 3.6Ghz, 16MB Cache, LGA 1151", 5550.00,
		"Marca: Intel;Número de núcleos: 8;Threads: 16;Frequencia:3,60 GHz;Frequência turbo max: 4.10 GHz;Cache: 16MB;Soquete: FCLGA1151");
		Produto p6 = new Produto(null, "Processador Intel Pentium Gold, 8ª Geração Coffee Lake, Cache 4MB, 3.80GHz, LGA 1151",200.00,
		"Marca: Intel;Número de núcleos: 2;Threads: 2;Frequencia:3,80 GHz;Frequência turbo max: 4.10 GHz;Cache: 4MB;Soquete: FCLGA1151");
		Produto p7 = new Produto(null, "Processador AMD Ryzen 5 1600, Cache 19MB, 3.2GHz (3.6GHz Max Turbo), AM4 ",1200.00,
		"Marca: AMD;Número de núcleos: 6;Threads: 12;Frequencia:3,20 GHz;Frequência turbo max: 3.60 GHz;Cache: 19MB;Soquete: FCLGA1151");
		Produto p8 = new Produto(null, "Processador AMD Ryzen 7 5800X, Cache 36MB, 3.8GHz (4.7GHz Max Turbo), AM4",800.00,
		"Marca: AMD;Número de núcleos: 6;Threads: 12;Frequencia:3,80 GHz;Frequência turbo max: 4.70 GHz;Cache: 36MB;Soquete: FCLGA1151");
		Produto p9 = new Produto(null, "Processador AMD Ryzen 9 3900X Cache 64MB 3.8GHz (4.6GHz Max Turbo) AM4,",100.00,
		"Marca: AMD;Número de núcleos: 12;Threads: 24;Frequencia:3,80 GHz;Frequência turbo max: 4.60 GHz;Cache: 64MB;Soquete: FCLGA1151");

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));

		Produto p10 = new Produto(null,"Memoria Corsair Vengeance 4GB (1x4) DDR3 1600MHz Azul",228.29,
		"Marca: Corsair;Capacidade: 4GB ;Tipo de memoria: DDR3 ;Frequencia: 1600MHz;Formato: DIMM");
		Produto p11 = new Produto(null,"Memoria Crucial Ballistix 8GB (1x8) DDR4 3000Mhz Vermelha",248.87,
		"Marca: Crucial;Capacidade: 8GB ;Tipo de memoria: DDR4 ;Frequencia: 3000MHz;Formato: DIMM");
		Produto p12 = new Produto(null,"Memoria G.Skill Aegis 4GB DDR4 2400Mhz, F4-2400C15S-4GIS",199.95,
		"Marca: G.Skill;Capacidade: 4GB ;Tipo de memoria: DDR4 ;Frequencia: 2400MHz;Formato: DIMM");
		Produto p13 = new Produto(null,"Memoria Corsair Vengeance LPX 8GB (1x8) DDR4 2400MHz Preta",398.05,
		"Marca: Corsair;Capacidade: 8GB ;Tipo de memoria: DDR4 ;Frequencia: 2400MHz;Formato: DIMM");
		Produto p14 = new Produto(null,"Memoria G.Skill Trident Z RGB 16GB (2x8) DDR4 2400MHz Preta,",229.99,
		"Marca: G.Skill;Capacidade: 16GB ;Tipo de memoria: DDR4 ;Frequencia: 2400MHz;Formato: DIMM");
		Produto p15 = new Produto(null,"Memoria Corsair Dominator Platinum RGB 16GB (2x8) DDR4 3000MHz Preto",1082.82,
		"Marca: Corsair;Capacidade: 16GB ;Tipo de memoria: DDR4 ;Frequencia: 3000MHz;Formato: DIMM");
		Produto p16 = new Produto(null,"Memoria Corsair Vengeance RGB PRO 16GB (2x8) DDR4 3600MHz Preta",1129.75,
		"Marca: Corsair;Capacidade: 16GB ;Tipo de memoria: DDR4 ;Frequencia: 3600MHz;Formato: DIMM");
		Produto p17 = new Produto(null,"Memoria Adata XPG Spectrix D60G RGB 16GB (1x16) DDR4 3200MHz",649.02,
		"Marca: Adata XPG;Capacidade: 16GB ;Tipo de memoria: DDR4 ;Frequencia: 3200MHz;Formato: DIMM");
		Produto p18 = new Produto(null,"Memoria Team Group T-Force RGB 8GB  DDR4 2666MHz",309.01,
		"Marca: Team Group T-Force;Capacidade: 8GB ;Tipo de memoria: DDR4 ;Frequencia: 2666MHz;Formato: DIMM");
		
		cat2.getProdutos().addAll(Arrays.asList(p10, p11, p12, p13, p14, p15, p16, p17, p18));

		Produto p19 = new Produto(null,"Placa de Video Asrock Phantom Gaming D Radeon RX570 4G, GDDR5.",1499.90,
		"Marca: ASrock;CPU: AMD Radeon RX570;Memória de video: 4096 MB; Clock: 1176 MHz;Interface de memória:128-Bit");
		Produto p20 = new Produto(null,"Placa de Vídeo MSI NVIDIA GeForce GTX 1770 Ti Gaming X 6G, GDDR6.",2890.95,
		"Marca: MSI;CPU: GeForce GTX 1660;Memória de video: 6144 MB; Clock: 1176 MHz;Interface de memória:256-Bit");
		Produto p21 = new Produto(null,"Placa de Vídeo Asus Dual AMD Radeon RX 5700 XT EVO, 8GB, GDDR6.",2059.90,
		"Marca: AMD;CPU: AMD Radeon RX 5700;Memória de video: 8192 MB; Clock: 1870 MHz;Interface de memória:256-Bit");
		Produto p22 = new Produto(null,"Placa de Vídeo Gigabyte NVIDIA GeForce RTX 2060 Gaming OC Pro 6G, GDDR6.",2636.36,
		"Marca: Gigabyte;CPU: GeForce RTX 2060;Memória de video: 6144 MB; Clock: 1870 MHz;Interface de memória:256-Bit");
		Produto p23 = new Produto(null,"Placa de Vídeo Asus ROG Strix NVIDIA GeForce RTX 2080 Ti 11GB.",8237.54,
		"Marca: Asus;CPU: GeForce RTX 2080;Memória de video: 12208 MB; Clock: 1870 MHz;Interface de memória:256-Bit");
		Produto p24 = new Produto(null,"Placa de Vídeo EVGA NVIDIA GeForce GTX 1650 KO ULTRA, 4GB, GDDR6.",1524.60,
		"Marca: EVGA;CPU: GeForce GTX 1650;Memória de video: 4096 MB; Clock: 1176 MHz;Interface de memória:128-Bit");
		Produto p25 = new Produto(null,"Placa de Vídeo Galax NVIDIA GeForce RTX 2060 6GB, GDDR6.",3779.8,
		"Marca: Galax;CPU: GeForce RTX 2060 6GB;Memória de video: 6144 MB; Clock: 1870 MHz;Interface de memória:256-Bit");
		Produto p26 = new Produto(null,"Placa de Vídeo Gigabyte AMD Radeon RX 570 Gaming, 4GB, GDDR5.",1482.25,
		"Marca: Gigabyte;CPU: AMD Radeon RX 570;Memória de video: 4096 MB; Clock: 1176 MHz;Interface de memória:128-Bit");
		Produto p27 = new Produto(null,"Placa de Video Gigabyte GeForce RTX 2070 Super 8GB Gaming OC 3X 256-bits.",4829.28,
		"Marca: Gigabyte;CPU: GeForce RTX 2070 RX570;Memória de video: 8192 MB; Clock: 1870 MHz;Interface de memória:256-Bit");
		
		cat3.getProdutos().addAll(Arrays.asList(p19, p20, p21, p22, p23, p24, p25, p26, p27));

		Produto p28 = new Produto(null,"Placa Mae Biostar Racing Z490GTN DDR4 Socket LGA1200 Intel Z490",1953.47,
		"Marca: Biostar;Chipset: Intel ® Z490;CPU: Soquete 1200 para processadores de 10º geração;Memoria: 4 x slots DDR4 2133/2400/2666/2800 até 128 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 6 x conectores sata ; LAN:Intel i211-AT 10/100/1000 Mb / s de negociação automática");
		Produto p29 = new Produto(null,"Placa Mae Biostar A520MH DDR4 Socket AM4 Chipset AMD A520",567.04,
		"Marca: Biostar;Chipset: AMD A520;CPU: 	APU / CPU Ryzen / NPU da série A da AMD para o soquete AM4;Memoria: 2 x slots DDR4 2133/2400/2666/2800 até 32 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 4 x conectores sata ; LAN: Realtek RTL8111H - Controlador 10/100/1000Mb/s");
		Produto p30 = new Produto(null,"Placa Mae Biostar Racing B460GTA DDR4 Socket LGA1200 Intel B460",1064.65,
		"Marca: Biostar;Chipset: Intel ® Z490;CPU: Soquete 1200 para processadores de 10º geração;Memoria: 4 x slots DDR4 2133/2400/2666/2800 até 128 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 6 x conectores sata ; LAN:Intel i211-AT 10/100/1000 Mb / s de negociação automática");
		Produto p31 = new Produto(null,"Placa Mae AsRock Z490 Taichi DDR4 Socket LGA1200 Intel Z490",559.59,
		"Marca: AsRock;Chipset: Intel ® Z490;CPU: Soquete 1200 para processadores de 10º geração;Memoria: 4 x slots DDR4 2133/2400/2666/2800 até 128 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 6 x conectores sata ; LAN:Intel i211-AT 10/100/1000 Mb / s de negociação automática");
		Produto p32 = new Produto(null,"Placa Mae Gigabyte B550M Gaming DDR4 Socket AM4 Chipset AMD B550",1010.22,
		"Marca: Gigabyte;Chipset: AMD B550;CPU: APU / CPU Ryzen / NPU da série A da AMD para o soquete AM4;Memoria: 4 x slots DDR4 2133/2400/2666/2800 até 128 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 6 x conectores sata ; LAN:Intel i211-AT 10/100/1000 Mb / s de negociação automática");
		Produto p33 = new Produto(null,"Placa Mae MSI MEG Z490 ACE DDR4 Socket LGA1200 Intel Z490",3396.70,
		"Marca: MSI;Chipset: Intel ® Z490;CPU: Soquete 1200 para processadores de 10º geração;Memoria: 4 x slots DDR4 2133/2400/2666/2800 até 128 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 6 x conectores sata ; LAN: Controlador LAN Intel ® I219V 1G");
		Produto p34 = new Produto(null,"Placa Mae Biostar Racing Z490GTA DDR4 Socket LGA1200 Intel Z490",2010.29,
		"Marca: Biostar;Chipset: Intel ® Z490;CPU: Soquete 1200 para processadores de 10º geração;Memoria: 4 x slots DDR4 2133/2400/2666/2800 até 128 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 6 x conectores sata ; LAN:Intel i211-AT 10/100/1000 Mb / s de negociação automática");
		Produto p35 = new Produto(null,"Placa Mae Gigabyte B550 Gaming X DDR4 Socket AM4 Chipset AMD B550",1420.43,
		"Marca: Gigabyte;Chipset: AMD B550;CPU: APU / CPU Ryzen / NPU da série A da AMD para o soquete AM4;Memoria: 4 x slots DDR4 2133/2400/2666/2800 até 128 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 6 x conectores sata ; LAN:Intel i211-AT 10/100/1000 Mb / s de negociação automática");
		Produto p36 = new Produto(null,"Placa Mae Gigabyte Z490 Aorus Elite AC DDR4 Socket LGA1200 Intel Z490",2215.87,
		"Marca: Gigabyte;Chipset: Intel ® Z490;CPU: Soquete 1200 para processadores de 10º geração ;Memoria: 4 x slots DDR4 2133/2400/2666/2800 até 128 GB de memória;Vídeo integrado: Por modelo de cpu; Armazenamento: 6 x conectores sata ; LAN:Intel i211-AT 10/100/1000 Mb / s de negociação automática");

		cat4.getProdutos().addAll(Arrays.asList(p28, p29, p30, p31, p32, p33, p34 ,p35, p36));

		Produto p37 = new Produto(null,"HD Seagate SkyHawk 10TB 3.5Sata III 6GB/s, ST10000VN0008", 2612.58,
		"Marca: Seagate ;Capacidade: 10TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		Produto p38 = new Produto(null,"HD Toshiba P300 1TB 3.5 Sata III 6GB/s, HDWD110UZSVA",339.78,
		"Marca: Toshiba ;Capacidade: 1TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		Produto p39 = new Produto(null,"HD WD Purple 3TB 3.5 Sata III 6GB/s, WD30PURZASDWAS",930.71,
		"Marca: WD Purple ;Capacidade: 3TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		Produto p40 = new Produto(null,"HD Seagate IronWolf 6TB 3.5 Sata III 6GB/s, ST1055484",1589.89,
		"Marca: Seagate ;Capacidade: 6TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		Produto p41 = new Produto(null,"HD Seagate IronWolf 4TB 3.5 Sata III 6GB/s, ST1000BGH44",1135.36,
		"Marca: Seagate ;Capacidade: 4TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		Produto p42 = new Produto(null,"HD WD Blue 1TB 3.5 Sata III 6GB/s, B1T557745774",328.42,
		"Marca: WD Blue ;Capacidade: 1TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		Produto p43 = new Produto(null,"HD WD Red NAS 1TB 3.5 Sata III 6GB/s, WD1T56787775",555.69,
		"Marca: WD;Capacidade: 1TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		Produto p44 = new Produto(null,"HD WD Red NAS 6TB 3.5 Sata III 6GB/s, WD6T884554A88",1635.28,
		"Marca: WD;Capacidade: 6TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		Produto p45 = new Produto(null,"HD Seagate SkyHawk 6TB 3.5 Sata III 6GB/s, ST105878544",1703.45,
		"Marca: Seagate ;Capacidade: 6TB ;Cache: 256 MB ;Velocidade: 7200 RPM;Tecnologia de gravação: CRM");
		
		cat5.getProdutos().addAll(Arrays.asList(p37, p38, p39, p40, p41, p42, p43 ,p44, p45));

		Produto p46 = new Produto(null,"SSD Duex Black 240GB 2.5 Sata 6GB/s",317.05,
		"Marca: Duex ;Capacidade: 240GB ;Velocidade Leitura: 530 Mb/s;Velocidade Gravação: 490 Mb/s");
		Produto p47 = new Produto(null,"SSD Gigabyte 1TB 2.5 SATA 6GB/s",1419.35,
		"Marca: Gigabytex ;Capacidade: 1TB ;Velocidade Leitura: 530 Mb/s;Velocidade Gravação: 490 Mb/s");
		Produto p48 = new Produto(null,"SSD Afox 120GB 2.5 Sata 6GB/s",226.14,
		"Marca: Afox ;Capacidade: 120GB ;Velocidade Leitura: 530 Mb/s;Velocidade Gravação: 490 Mb/s");
		Produto p49 = new Produto(null,"SSD WD Black SN750 1TB M.2 2280 NVMe",518.55,
		"Marca: WD ;Capacidade: 1TB ;Velocidade Leitura: 530 Mb/s;Velocidade Gravação: 490 Mb/s");
		Produto p50 = new Produto(null,"SSD WD Green 480GB 2.5 Sata III 6GB/s",419.33,
		"Marca: WD;Capacidade: 480GB ;Velocidade Leitura: 530 Mb/s;Velocidade Gravação: 490 Mb/s");
		Produto p51 = new Produto(null,"SSD Gigabyte Aorus RGB AIC 1TB NVMe",2930.70,
		"Marca: Gigabyte ;Capacidade: 1TB ;Velocidade Leitura: 530 Mb/s;Velocidade Gravação: 490 Mb/s");

		cat6.getProdutos().addAll(Arrays.asList(p46, p47, p48, p49, p50, p51));

		Produto p52 = new Produto(null,"Monitor Samsung 31,5'' LED Full HD Curvo Preto, LC32T550FDLXZD",2199.03,
		"Marca: Sansung;Tamanho da tela: 31,5; Proporção da tela: 16:9;Resolução: 1,920 x 1,080;Taxa de Atualização: 75 MHz");
		Produto p53 = new Produto(null,"Monitor Gamer Asus Tuf 31,5'' 1ms 144Hz FreeSync, VG32VQ ",3599.11,
		"Marca: Asus;Tamanho da tela: 31,5; Proporção da tela: 16:9;Resolução: 1,920 x 1,080;Taxa de Atualização: 144 MHz");
		Produto p54 = new Produto(null,"Monitor Acer 21.5 Pol. LED Full HD 5ms, V226HQL",226.14,
		"Marca: Acer;Tamanho da tela: 21,5; Proporção da tela: 16:9;Resolução: 1,920 x 1,080;Taxa de Atualização: 60 MHz");
		Produto p55 = new Produto(null,"Monitor Gamer Acer Nitro XVO Series 28 LED 60Hz 4ms FreeSync",3122.99,
		"Marca: Acer;Tamanho da tela: 28; Proporção da tela: 16:9;Resolução: 1,920 x 1,080;Taxa de Atualização: 60 MHz");
		Produto p56 = new Produto(null,"Monitor Samsung 21.5'' LED 5ms 60Hz FHD HDMI/VGA",815.15,
		"Marca: Sansung;Tamanho da tela: 21,5; Proporção da tela: 16:9;Resolução: 1,920 x 1,080;Taxa de Atualização: 60 MHz");
		Produto p57 = new Produto(null,"Monitor Gamer AOC G2 Series 27 LED 1ms 75Hz IPS VGA/HDMI",1646.61,
		"Marca: AOC;Tamanho da tela: 27; Proporção da tela: 16:9;Resolução: 1,920 x 1,080;Taxa de Atualização: 75 MHz");

		cat7.getProdutos().addAll(Arrays.asList(p52, p53, p54, p55, p56, p57));

		Produto p58 = new Produto(null,"Headset Gamer Gamdias Hebe E2 RGB",210.12,
		"Marca: Gamdias;Cor: Preto ;Compatibilidade:Headset Gamer Gamdias Hebe E2 RGB ;Conector: USB;Tamnho do cabo: 1.8 metros");
		Produto p59 = new Produto(null,"Headset Gamer Audio Technica, ATH-AG1X ",165.92,
		"Marca: Technica;Cor: Preto ;Compatibilidade:PC/ XBOX/ PLAYSTATION/ MOBILE ;Conector: USB;Tamnho do cabo: 1.8 metros");
		Produto p60 = new Produto(null,"Teclado Gaming TESORO Colada Aluminum ",908.98,
		"Marca: TESORO;Cor: Preto ;Compatibilidade:PC/ XBOX/ PLAYSTATION/ MOBILE ;Conector: USB;Tamnho do cabo: 1.8 metros");
		Produto p61 = new Produto(null,"Teclado Gamer Multilaser Multimidia USB",44.20,
		"Marca: Multilaser;Cor: Preto ;Compatibilidade:PC/ XBOX/ PLAYSTATION/ MOBILE ;Conector: USB;Tamnho do cabo: 1.8 metros");
		Produto p62 = new Produto(null,"Mouse Gamer Redragon Griffin RGB ",147.70,
		"Marca: Redragon;Cor: Preto ;Compatibilidade:PC/ XBOX/ PLAYSTATION/ MOBILE ;Conector: USB;Tamnho do cabo: 1.8 metros");
		Produto p63 = new Produto(null,"Mouse Multilaser Fire 2400Dpi ",54.43,
		"Marca: Multilaser;Cor: Preto ;Compatibilidade:PC/ XBOX/ PLAYSTATION/ MOBILE ;Conector: USB;Tamnho do cabo: 1.8 metros");

		cat8.getProdutos().addAll(Arrays.asList(p58, p59, p60, p61, p62, p63));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat1));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p7.getCategorias().addAll(Arrays.asList(cat1));
		p8.getCategorias().addAll(Arrays.asList(cat1));
		p9.getCategorias().addAll(Arrays.asList(cat1));

		p10.getCategorias().addAll(Arrays.asList(cat2));
		p11.getCategorias().addAll(Arrays.asList(cat2));
		p12.getCategorias().addAll(Arrays.asList(cat2));
		p13.getCategorias().addAll(Arrays.asList(cat2));
		p14.getCategorias().addAll(Arrays.asList(cat2));
		p15.getCategorias().addAll(Arrays.asList(cat2));
		p16.getCategorias().addAll(Arrays.asList(cat2));
		p17.getCategorias().addAll(Arrays.asList(cat2));
		p18.getCategorias().addAll(Arrays.asList(cat2));

		p19.getCategorias().addAll(Arrays.asList(cat3));
		p20.getCategorias().addAll(Arrays.asList(cat3));
		p21.getCategorias().addAll(Arrays.asList(cat3));
		p22.getCategorias().addAll(Arrays.asList(cat3));
		p23.getCategorias().addAll(Arrays.asList(cat3));
		p24.getCategorias().addAll(Arrays.asList(cat3));
		p25.getCategorias().addAll(Arrays.asList(cat3));
		p26.getCategorias().addAll(Arrays.asList(cat3));
		p27.getCategorias().addAll(Arrays.asList(cat3));

		p28.getCategorias().addAll(Arrays.asList(cat4));
		p29.getCategorias().addAll(Arrays.asList(cat4));
		p30.getCategorias().addAll(Arrays.asList(cat4));
		p31.getCategorias().addAll(Arrays.asList(cat4));
		p32.getCategorias().addAll(Arrays.asList(cat4));
		p33.getCategorias().addAll(Arrays.asList(cat4));
		p34.getCategorias().addAll(Arrays.asList(cat4));
		p35.getCategorias().addAll(Arrays.asList(cat4));
		p36.getCategorias().addAll(Arrays.asList(cat4));

		p37.getCategorias().addAll(Arrays.asList(cat5));
		p38.getCategorias().addAll(Arrays.asList(cat5));
		p39.getCategorias().addAll(Arrays.asList(cat5));
		p40.getCategorias().addAll(Arrays.asList(cat5));
		p41.getCategorias().addAll(Arrays.asList(cat5));
		p42.getCategorias().addAll(Arrays.asList(cat5));
		p43.getCategorias().addAll(Arrays.asList(cat5));
		p44.getCategorias().addAll(Arrays.asList(cat5));
		p45.getCategorias().addAll(Arrays.asList(cat5));

		p46.getCategorias().addAll(Arrays.asList(cat6));
		p47.getCategorias().addAll(Arrays.asList(cat6));
		p48.getCategorias().addAll(Arrays.asList(cat6));
		p49.getCategorias().addAll(Arrays.asList(cat6));
		p50.getCategorias().addAll(Arrays.asList(cat6));
		p51.getCategorias().addAll(Arrays.asList(cat6));

		p52.getCategorias().addAll(Arrays.asList(cat7));
		p53.getCategorias().addAll(Arrays.asList(cat7));
		p54.getCategorias().addAll(Arrays.asList(cat7));
		p55.getCategorias().addAll(Arrays.asList(cat7));
		p56.getCategorias().addAll(Arrays.asList(cat7));
		p57.getCategorias().addAll(Arrays.asList(cat7));

		
		p58.getCategorias().addAll(Arrays.asList(cat8));
		p59.getCategorias().addAll(Arrays.asList(cat8));
		p60.getCategorias().addAll(Arrays.asList(cat8));
		p61.getCategorias().addAll(Arrays.asList(cat8));
		p62.getCategorias().addAll(Arrays.asList(cat8));
		p63.getCategorias().addAll(Arrays.asList(cat8));


	categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6 ,cat7, cat8));
	produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, 
	p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26 ,p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, 
	p37, p38 ,p39 ,p40 ,p41 ,p42 ,p43 ,p44 ,p45, p46, p47, p48, p49, p50, p51, p52, p53, p54, p55, p56, p57,
	p58, p59, p60, p61, p62, p63));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.save(c1);
		cidadeRepository.save(c2);
		cidadeRepository.save(c3);
		
		Cliente cli1 = new Cliente(null, "Henrique","Jardel Timoteo","henrique.jardelt@gmail.com", "36378912377", TipoCliente.PESSOAFISICA,encoder.encode("12345"));
		cli1.addPerfil(Perfil.ADMIN);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Cliente cli2 = new Cliente(null, "Jane","Cara de bunda","janetimoteo@gmail.com", "75337817086", TipoCliente.PESSOAFISICA,encoder.encode("12345"));
		cli2.getTelefones().addAll(Arrays.asList("27363323", "99124542"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua dos lobos", "0", "Não tem nada", "lokos", "79013454", cli2, c3);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		Pedido ped3 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli2, e3);
		
		PagamentoComCartao pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		PagamentoComCartao pagto3 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, ped3, 10);
		ped3.setPagamento(pagto3);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		cli2.getPedidos().addAll(Arrays.asList(ped3));
				
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2 , pagto3));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		ped3.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
