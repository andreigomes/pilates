package com.pilates.models.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa física"),
	PESSOAJURIDICA(2, "Pessoa jurídica");
	
	private int id;
	private String descricao;
	
	private TipoCliente(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	//class function
	public static TipoCliente toEnum(Integer id) {
		
		if (id == null) {
			return null;
		}
		
		for (TipoCliente tipoCliente : TipoCliente.values()) {
			if (id == tipoCliente.getId()) {
				return tipoCliente;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
	
}
