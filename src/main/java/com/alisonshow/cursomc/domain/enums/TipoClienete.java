package com.alisonshow.cursomc.domain.enums;

public enum TipoClienete {
	
	PESSOAFISICA(1, "Pessoa fisíca"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int codigo;
	private String descricao;
	
	private TipoClienete(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	
	public String getDescricao() {
		return descricao;
	}

	
	public static TipoClienete ToEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (TipoClienete x : TipoClienete.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("O ID: " + cod + "não é um ID válido");
	}
	

}
