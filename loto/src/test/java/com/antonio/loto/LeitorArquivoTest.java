package com.antonio.loto;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.antonio.loto.arquivo.Leitor;
import com.antonio.loto.arquivo.html.Body;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class LeitorArquivoTest extends TestCase {

	public void testService() throws Exception {
		AtualizadorBaseService service = new AtualizadorBaseService();
		service.atualizarBase();
	}

	public void testApp() {

		try {
			ZipFile zip = new ZipFile(
					Leitor.gravaArquivoDeURL("http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_megase.zip"));
			assertNotNull(zip);
			assertTrue(zip.size() > 0);
			Enumeration e = zip.entries();
			Body resultado = null;
			List<Resultado> retorno = null;
			while (e.hasMoreElements()) {
				ZipEntry arq = (ZipEntry) e.nextElement();
				if (arq.getName().endsWith(".HTM")) {
					try {
						InputStream in = zip.getInputStream(arq);
						resultado = Leitor.ler(in);
						retorno = Leitor.obterListaResultado(resultado);
						for (Resultado result : retorno) {
							System.out.println(result.toString());
						}
					} finally {
						zip.close();
					}
					break;
				}
			}
			assertNotNull(resultado);
			assertNotNull(retorno);
			assertTrue(retorno.size() > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail();
		}
	}
}
