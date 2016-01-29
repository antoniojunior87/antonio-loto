package com.antonio.loto;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.antonio.loto.arquivo.Leitor;
import com.antonio.loto.arquivo.html.Body;
import com.antonio.loto.dao.GenericDao;
import com.antonio.loto.entity.ResultadoMega;

public class AtualizadorBaseService {

	private static final String URL = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_megase.zip";

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/mm/yyyy");

	private GenericDao dao;

	private DecimalFormat df = new DecimalFormat();
	private DecimalFormatSymbols sfs = new DecimalFormatSymbols();

	public AtualizadorBaseService() {
		sfs.setDecimalSeparator(',');
		df.setDecimalFormatSymbols(sfs);
	}

	private GenericDao getDao() {
		if (dao == null) {
			dao = new GenericDao();
		}
		return dao;
	}

	public void atualizarBase() throws Exception {

		ZipFile zip = new ZipFile(Leitor.gravaArquivoDeURL(URL));

		Enumeration<?> e = zip.entries();

		while (e.hasMoreElements()) {
			ZipEntry arq = (ZipEntry) e.nextElement();
			if (arq.getName().endsWith(".HTM")) {
				try {
					InputStream in = zip.getInputStream(arq);
					Body resultado = Leitor.ler(in);
					List<Resultado> resultados = Leitor.obterListaResultado(resultado);
					List<ResultadoMega> resultadosMega = new ArrayList<ResultadoMega>();
					for (Resultado result : resultados) {
						resultadosMega.add(converterResultado(result));
					}
					getDao().salvarTodos(resultadosMega);
				} finally {
					zip.close();
				}
				break;
			}
		}
	}

	private ResultadoMega converterResultado(Resultado result) throws Exception {
		ResultadoMega retorno = new ResultadoMega();

		retorno.setConcurso(Integer.valueOf(result.getConcurso()));
		retorno.setDataSorteio(SDF.parse(result.getDataSorteio()));
		retorno.setDezena1(Integer.valueOf(result.getDezena1()));
		retorno.setDezena2(Integer.valueOf(result.getDezena2()));
		retorno.setDezena3(Integer.valueOf(result.getDezena3()));
		retorno.setDezena4(Integer.valueOf(result.getDezena4()));
		retorno.setDezena5(Integer.valueOf(result.getDezena5()));
		retorno.setDezena6(Integer.valueOf(result.getDezena6()));
		retorno.setArrecadacaoTotal(df.parse((result.getArrecadacaoTotal())).doubleValue());
		retorno.setGanhadoresSena(Integer.valueOf(result.getGanhadoresSena()));
		retorno.setCidade(result.getCidade());
		retorno.setUf(result.getUf());
		retorno.setRateioSena(df.parse((result.getRateioSena())).doubleValue());
		retorno.setGanhadoresQuina(Integer.valueOf(result.getGanhadoresQuina()));
		retorno.setRateioQuina(df.parse((result.getRateioQuina())).doubleValue());
		retorno.setGanhadoresQuadra(Integer.valueOf(result.getGanhadoresQuadra()));
		retorno.setRateioQuadra(df.parse((result.getRateioQuadra())).doubleValue());
		retorno.setAcumulado(result.getAcumulado());
		retorno.setValorAcumulado(df.parse((result.getValorAcumulado())).doubleValue());
		retorno.setEstimativaPremio(df.parse((result.getEstimativaPremio())).doubleValue());
		retorno.setAcumuladoMegaVirada(df.parse((result.getAcumuladoMegaVirada())).doubleValue());

		return retorno;
	}
}
