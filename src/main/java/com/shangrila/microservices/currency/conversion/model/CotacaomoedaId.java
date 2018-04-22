package com.shangrila.microservices.currency.conversion.model;

import java.io.Serializable;

import lombok.Value;

@Value
public class CotacaomoedaId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int codMoeda;
	private String data;

	public int getCodMoeda() {
		return codMoeda;
	}

	public void setCodMoeda(int codMoeda) {
		this.codMoeda = codMoeda;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
