package com.antonio.loto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ @NamedQuery(name = "findAllResultadoMega", query = "SELECT c FROM ResultadoMega c") })
public class ResultadoMega {

	@Id
	private Integer concurso;
	@Temporal(TemporalType.DATE)
	private Date dataSorteio;
	@Column
	private Integer dezena1;
	@Column
	private Integer dezena2;
	@Column
	private Integer dezena3;
	@Column
	private Integer dezena4;
	@Column
	private Integer dezena5;
	@Column
	private Integer dezena6;
	@Column
	private Double arrecadacaoTotal;
	@Column
	private Integer ganhadoresSena;
	@Column
	private String cidade;
	@Column
	private String uf;
	@Column
	private Double rateioSena;
	@Column
	private Integer ganhadoresQuina;
	@Column
	private Double rateioQuina;
	@Column
	private Integer ganhadoresQuadra;
	@Column
	private Double rateioQuadra;
	@Column
	private String acumulado;
	@Column
	private Double valorAcumulado;
	@Column
	private Double estimativaPremio;
	@Column
	private Double acumuladoMegaVirada;

	public ResultadoMega() {
	}

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}

	public Date getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}

	public Integer getDezena1() {
		return dezena1;
	}

	public void setDezena1(Integer dezena1) {
		this.dezena1 = dezena1;
	}

	public Integer getDezena2() {
		return dezena2;
	}

	public void setDezena2(Integer dezena2) {
		this.dezena2 = dezena2;
	}

	public Integer getDezena3() {
		return dezena3;
	}

	public void setDezena3(Integer dezena3) {
		this.dezena3 = dezena3;
	}

	public Integer getDezena4() {
		return dezena4;
	}

	public void setDezena4(Integer dezena4) {
		this.dezena4 = dezena4;
	}

	public Integer getDezena5() {
		return dezena5;
	}

	public void setDezena5(Integer dezena5) {
		this.dezena5 = dezena5;
	}

	public Integer getDezena6() {
		return dezena6;
	}

	public void setDezena6(Integer dezena6) {
		this.dezena6 = dezena6;
	}

	public Double getArrecadacaoTotal() {
		return arrecadacaoTotal;
	}

	public void setArrecadacaoTotal(Double arrecadacaoTotal) {
		this.arrecadacaoTotal = arrecadacaoTotal;
	}

	public Integer getGanhadoresSena() {
		return ganhadoresSena;
	}

	public void setGanhadoresSena(Integer ganhadoresSena) {
		this.ganhadoresSena = ganhadoresSena;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Double getRateioSena() {
		return rateioSena;
	}

	public void setRateioSena(Double rateioSena) {
		this.rateioSena = rateioSena;
	}

	public Integer getGanhadoresQuina() {
		return ganhadoresQuina;
	}

	public void setGanhadoresQuina(Integer ganhadoresQuina) {
		this.ganhadoresQuina = ganhadoresQuina;
	}

	public Double getRateioQuina() {
		return rateioQuina;
	}

	public void setRateioQuina(Double rateioQuina) {
		this.rateioQuina = rateioQuina;
	}

	public Integer getGanhadoresQuadra() {
		return ganhadoresQuadra;
	}

	public void setGanhadoresQuadra(Integer ganhadoresQuadra) {
		this.ganhadoresQuadra = ganhadoresQuadra;
	}

	public Double getRateioQuadra() {
		return rateioQuadra;
	}

	public void setRateioQuadra(Double rateioQuadra) {
		this.rateioQuadra = rateioQuadra;
	}

	public String getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(String acumulado) {
		this.acumulado = acumulado;
	}

	public Double getValorAcumulado() {
		return valorAcumulado;
	}

	public void setValorAcumulado(Double valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}

	public Double getEstimativaPremio() {
		return estimativaPremio;
	}

	public void setEstimativaPremio(Double estimativaPremio) {
		this.estimativaPremio = estimativaPremio;
	}

	public Double getAcumuladoMegaVirada() {
		return acumuladoMegaVirada;
	}

	public void setAcumuladoMegaVirada(Double acumuladoMegaVirada) {
		this.acumuladoMegaVirada = acumuladoMegaVirada;
	}

}
