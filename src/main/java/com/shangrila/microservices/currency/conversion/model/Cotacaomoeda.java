package com.shangrila.microservices.currency.conversion.model;

import org.springframework.data.annotation.Id;

import lombok.Data;


@Data
public class Cotacaomoeda {
	
	@Id
	private CotacaomoedaId id;
	
	private String tipo;
	
	private String siglaMoeda;

	private String taxaCompra;

	private String TaxaVenda;

	private String paridadeCompra;

	private String paridadeVenda;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTaxaCompra() {
		return taxaCompra;
	}

	public void setTaxaCompra(String taxaCompra) {
		this.taxaCompra = taxaCompra;
	}

	public String getTaxaVenda() {
		return TaxaVenda;
	}

	public void setTaxaVenda(String taxaVenda) {
		TaxaVenda = taxaVenda;
	}

	public String getParidadeCompra() {
		return paridadeCompra;
	}

	public void setParidadeCompra(String paridadeCompra) {
		this.paridadeCompra = paridadeCompra;
	}

	public String getParidadeVenda() {
		return paridadeVenda;
	}

	public void setParidadeVenda(String paridadeVenda) {
		this.paridadeVenda = paridadeVenda;
	}

	public String getSiglaMoeda() {
		return siglaMoeda;
	}

	public void setSiglaMoeda(String siglaMoeda) {
		this.siglaMoeda = siglaMoeda;
	}

	public CotacaomoedaId getId() {
		return id;
	}

	public void setId(CotacaomoedaId id) {
		this.id = id;
	}

}
