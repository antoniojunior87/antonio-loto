package com.antonio.loto;

import com.antonio.loto.entity.ResultadoMega;

/**
 * @author Junior
 */
public class Resultado {

	// 19 campos
	private String concurso;
	private String dataSorteio;
	private String dezena1;
	private String dezena2;
	private String dezena3;
	private String dezena4;
	private String dezena5;
	private String dezena6;
	private String arrecadacaoTotal;
	private String ganhadoresSena;
	private String cidade;
	private String uf;
	private String rateioSena;
	private String ganhadoresQuina;
	private String rateioQuina;
	private String ganhadoresQuadra;
	private String rateioQuadra;
	private String acumulado;
	private String valorAcumulado;
	private String estimativaPremio;
	private String acumuladoMegaVirada;

	public Resultado(Object[] str) {
		this(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12],
				str[13], str[14], str[15], str[16], str[17], str[18], str[19], str[20]);
	}

	public Resultado(Object concurso, Object dataSorteio, Object dezena1, Object dezena2, Object dezena3,
			Object dezena4, Object dezena5, Object dezena6, Object arrecadacaoTotal, Object ganhadoresSena,
			Object cidade, Object uf, Object rateioSena, Object ganhadoresQuina, Object rateioQuina,
			Object ganhadoresQuadra, Object rateioQuadra, Object acumulado, Object valorAcumulado,
			Object estimativaPremio, Object acumuladoMegaVirada) {
		this.concurso = (String) concurso;
		this.dataSorteio = (String) dataSorteio;
		this.dezena1 = (String) dezena1;
		this.dezena2 = (String) dezena2;
		this.dezena3 = (String) dezena3;
		this.dezena4 = (String) dezena4;
		this.dezena5 = (String) dezena5;
		this.dezena6 = (String) dezena6;
		this.arrecadacaoTotal = (String) arrecadacaoTotal;
		this.ganhadoresSena = (String) ganhadoresSena;
		this.cidade = (String) cidade;
		this.uf = (String) uf;
		this.rateioSena = (String) rateioSena;
		this.ganhadoresQuina = (String) ganhadoresQuina;
		this.rateioQuina = (String) rateioQuina;
		this.ganhadoresQuadra = (String) ganhadoresQuadra;
		this.rateioQuadra = (String) rateioQuadra;
		this.acumulado = (String) acumulado;
		this.valorAcumulado = (String) valorAcumulado;
		this.estimativaPremio = (String) estimativaPremio;
		this.acumuladoMegaVirada = (String) acumuladoMegaVirada;
	}

	public String getConcurso() {
		return concurso;
	}

	public String getDataSorteio() {
		return dataSorteio;
	}

	public String getDezena1() {
		return dezena1;
	}

	public String getDezena2() {
		return dezena2;
	}

	public String getDezena3() {
		return dezena3;
	}

	public String getDezena4() {
		return dezena4;
	}

	public String getDezena5() {
		return dezena5;
	}

	public String getDezena6() {
		return dezena6;
	}

	public String getArrecadacaoTotal() {
		return arrecadacaoTotal;
	}

	public String getGanhadoresSena() {
		return ganhadoresSena;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	public String getRateioSena() {
		return rateioSena;
	}

	public String getGanhadoresQuina() {
		return ganhadoresQuina;
	}

	public String getRateioQuina() {
		return rateioQuina;
	}

	public String getGanhadoresQuadra() {
		return ganhadoresQuadra;
	}

	public String getRateioQuadra() {
		return rateioQuadra;
	}

	public String getAcumulado() {
		return acumulado;
	}

	public String getValorAcumulado() {
		return valorAcumulado;
	}

	public String getEstimativaPremio() {
		return estimativaPremio;
	}

	public String getAcumuladoMegaVirada() {
		return acumuladoMegaVirada;
	}

	@Override
	public String toString() {
		return "Resultado [concurso=" + concurso + ", dataSorteio=" + dataSorteio + ", dezena1=" + dezena1
				+ ", dezena2=" + dezena2 + ", dezena3=" + dezena3 + ", dezena4=" + dezena4 + ", dezena5=" + dezena5
				+ ", dezena6=" + dezena6 + ", arrecadacaoTotal=" + arrecadacaoTotal + ", ganhadoresSena="
				+ ganhadoresSena + ", cidade=" + cidade + ", uf=" + uf + ", rateioSena=" + rateioSena
				+ ", ganhadoresQuina=" + ganhadoresQuina + ", rateioQuina=" + rateioQuina + ", ganhadoresQuadra="
				+ ganhadoresQuadra + ", rateioQuadra=" + rateioQuadra + ", acumulado=" + acumulado + ", valorAcumulado="
				+ valorAcumulado + ", estimativaPremio=" + estimativaPremio + ", acumuladoMegaVirada="
				+ acumuladoMegaVirada + "]";
	}

	public ResultadoMega obterResultadoMega() {
		ResultadoMega r = new ResultadoMega();

		r.setConcurso(Integer.valueOf(concurso));

		return r;
	}

}
