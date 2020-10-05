package com.pilates;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pilates.models.Categoria;
import com.pilates.models.Cidade;
import com.pilates.models.Cliente;
import com.pilates.models.Endereco;
import com.pilates.models.Estado;
import com.pilates.models.ItemPedido;
import com.pilates.models.Pagamento;
import com.pilates.models.PagamentoComBoleto;
import com.pilates.models.PagamentoComCartao;
import com.pilates.models.Pedido;
import com.pilates.models.Produto;
import com.pilates.models.enums.EstadoPagamento;
import com.pilates.models.enums.TipoCliente;
import com.pilates.repositories.CategoriaRepository;
import com.pilates.repositories.CidadeRepository;
import com.pilates.repositories.ClienteRepository;
import com.pilates.repositories.EnderecoRepository;
import com.pilates.repositories.EstadoRepository;
import com.pilates.repositories.ItemPedidoRepository;
import com.pilates.repositories.PagamentoRepository;
import com.pilates.repositories.PedidoRepository;
import com.pilates.repositories.ProdutoRepository;

@SpringBootApplication
public class PilatesApplication implements CommandLineRunner {

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
	
	public static void main(String[] args) {
		SpringApplication.run(PilatesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	Categoria categoria1 = new Categoria(null, "Informática");
	Categoria categoria2 = new Categoria(null, "Escritório");
	categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
	
	Produto produto1 = new Produto(null, "Computador", 2000.00);
	Produto produto2 = new Produto(null, "Impressora", 800.00);
	Produto produto3 = new Produto(null, "Mouse", 80.00);	
	produto1.getCategorias().addAll(Arrays.asList(categoria1));
	produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
	produto3.getCategorias().addAll(Arrays.asList(categoria1));		
	produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));	
	
	Estado estado1 = new Estado(null, "São Paulo");
	Estado estado2 = new Estado(null, "Minas Gerais");
	estadoRepository.saveAll(Arrays.asList(estado1, estado2));
	
	Cidade cidade1 = new Cidade(null, "Assis", estado1);
	Cidade cidade2 = new Cidade(null, "Capitólio", estado2);
	Cidade cidade3 = new Cidade(null, "Ouro Preto", estado2);
	cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
	
	Cliente cliente1 = new 	Cliente(null, "Andrei Gomes", "andrei_sps@hotmail.com", "40806958880", TipoCliente.PESSOAFISICA);
	cliente1.getTelefones().addAll(Arrays.asList("988224834", "997141840"));
	clienteRepository.saveAll(Arrays.asList(cliente1));
	
	Endereco endereco1 = new Endereco(null, "Rua Duque de Caxias", "441", "", "Centro", "19800000", cliente1, cidade3);
	Endereco endereco2 = new Endereco(null, "Rua Duque de Caxias", "441", "", "Centro", "19800000", cliente1, cidade2);
	enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
	Pedido pedido2 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco2);
	
	Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
	pedido1.setPagamento(pagamento1);
	
	Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("30/09/2017 10:32"), null);
	pedido2.setPagamento(pagamento2);
	
	pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
	pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
	
	ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1.00, 2000.00);
	ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 2.00, 80.00);
	ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 0.00, 100.00, 800.00);
	
	pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
	pedido2.getItens().addAll(Arrays.asList(itemPedido3));
	
	itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
			
	}

}
