package com.antonio.loto.arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.antonio.loto.Resultado;
import com.antonio.loto.arquivo.html.Body;
import com.antonio.loto.arquivo.html.Tr;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * @author Junior
 */
public class Leitor {

	public static Body ler(InputStream input) throws FileNotFoundException, IOException {

		File arq = File.createTempFile("resultados", "html");
		OutputStream out = new FileOutputStream(arq);
		byte buf[] = new byte[1024];
		int len;
		while ((len = input.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		out.close();
		input.close();

		XStream xStream = new XStream(new DomDriver()) {
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						try {
							return definedIn != Object.class || realClass(fieldName) != null;
						} catch (CannotResolveClassException cnrce) {
							return false;
						}
					}
				};
			}
		};
		xStream.alias("body", Body.class);
		xStream.alias("tr", Tr.class);
		xStream.alias("td", String.class);
		xStream.addImplicitCollection(Body.class, "linhas");
		xStream.addImplicitCollection(Tr.class, "colunas");

		String str = FileUtils.readFileToString(arq);
		str = str.replaceAll(" bgcolor=#D9E6F4", "");
		str = str.replaceAll("&nbsp", "");
		int primeiro = str.indexOf("</tr>");
		int utimo = str.lastIndexOf("</tr>");

		StringBuilder builder = new StringBuilder();
		builder.append("<body>");
		builder.append(str.substring(primeiro + 5, utimo));
		builder.append("</tr>");
		builder.append("</body>");
		return (Body) xStream.fromXML(builder.toString());
	}

	public static File gravaArquivoDeURL(String stringUrl) {
		try {

			HttpGet httpget = new HttpGet(stringUrl);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpget);
			InputStream is = response.getEntity().getContent();

			File saida = File.createTempFile("mega", "sena");
			saida.createNewFile();
			FileOutputStream fos = new FileOutputStream(saida);

			int umByte = 0;
			while ((umByte = is.read()) != -1) {
				fos.write(umByte);
			}

			// Nao se esqueca de sempre fechar as streams apos seu uso!
			is.close();
			fos.close();
			// apos criar o arquivo fisico, retorna referencia para o mesmo
			return saida;
		} catch (Exception e) {
			// Lembre-se de tratar bem suas excecoes, ou elas tambem lhe
			// tratarão mal!
			// Aqui so vamos mostrar o stack no stderr.
			e.printStackTrace();
		}
		return null;
	}

	public static List<Resultado> obterListaResultado(Body resultado) {
		List<Resultado> retorno = new ArrayList<Resultado>();
		for (Tr tr : resultado.getLinhas()) {
			if (tr.getColunas().size() == 21)
				retorno.add(new Resultado(tr.getColunas().toArray()));
		}
		return retorno;
	}
}
