package com.pilates;

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
import com.pilates.models.Produto;
import com.pilates.models.enums.TipoCliente;
import com.pilates.repositories.CategoriaRepository;
import com.pilates.repositories.CidadeRepository;
import com.pilates.repositories.ClienteRepository;
import com.pilates.repositories.EnderecoRepository;
import com.pilates.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(PilatesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	Categoria categoria1 = new Categoria(null, "Informática");
	Categoria categoria2 = new Categoria(null, "Escritório");
	
	Produto produto1 = new Produto(null, "Computador", 2000.00);
	Produto produto2 = new Produto(null, "Impressora", 800.00);
	Produto produto3 = new Produto(null, "Mouse", 80.00);

	//categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
	//categoria1.getProdutos().addAll(Arrays.asList(produto2));
	
	
	produto1.getCategorias().addAll(Arrays.asList(categoria1));
	produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
	produto3.getCategorias().addAll(Arrays.asList(categoria1));	
	
	categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
	produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
	
	Estado estado1 = new Estado(null, "São Paulo");
	Estado estado2 = new Estado(null, "Minas Gerais");
	
	Cidade cidade1 = new Cidade(null, "Assis", estado1);
	Cidade cidade2 = new Cidade(null, "Capitólio", estado2);
	Cidade cidade3 = new Cidade(null, "Ouro Preto", estado2);
	
	estadoRepository.saveAll(Arrays.asList(estado1, estado2));
	cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
	
	Cliente cliente1 = new 	Cliente(null, "Andrei Gomes", "andrei_sps@hotmail.com", "40806958880", TipoCliente.PESSOAFISICA);
	cliente1.getTelefones().addAll(Arrays.asList("988224834", "997141840"));
	
	Endereco endereco1 = new Endereco(null, "Rua Duque de Caxias", "441", "", "Centro", "19800000", cliente1, cidade3);
	Endereco endereco2 = new Endereco(null, "Rua Duque de Caxias", "441", "", "Centro", "19800000", cliente1, cidade2);
	
	clienteRepository.saveAll(Arrays.asList(cliente1));
	enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
	
	}

}
