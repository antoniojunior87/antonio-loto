package com.antonio.loto;

import java.text.SimpleDateFormat;
import java.util.List;

import com.antonio.loto.entity.ResultadoMega;

public class App {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws Exception {

		Servico service = new Servico();
		service.atualizarBase();
		List<ResultadoMega> lista = service.listarTodos();
		// List<ResultadoMega> lista =
		// service.listarPorPeriodo(SDF.parse("01/01/2016"),
		// SDF.parse("01/05/2016"));
		System.out.println("Resultados: " + lista.size());
		// System.out.println(lista);
		int[] valores = numerosMaisSorteados(lista);
		Grafico.plotarGrafico(valores);
	}

	private static int[] numerosMaisSorteados(List<ResultadoMega> lista) {

		int histograma[] = new int[61];

		for (ResultadoMega resultadoMega : lista) {
			histograma[resultadoMega.getDezena1().intValue()]++;
			histograma[resultadoMega.getDezena2().intValue()]++;
			histograma[resultadoMega.getDezena3().intValue()]++;
			histograma[resultadoMega.getDezena4().intValue()]++;
			histograma[resultadoMega.getDezena5().intValue()]++;
			histograma[resultadoMega.getDezena6().intValue()]++;
		}

		for (int i = 1; i < 61; i++) {
			System.out.println(String.format("[%2d] - \t%8d - \t%s", i, histograma[i], print(histograma[i])));
		}

		return histograma;
	}

	private static String print(int count) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < count; i++) {
			b.append("+");
		}
		return b.toString();
	}
}
