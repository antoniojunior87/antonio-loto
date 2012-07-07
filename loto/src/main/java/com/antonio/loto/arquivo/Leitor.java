package com.antonio.loto.arquivo;

import com.antonio.loto.Resultado;
import com.antonio.loto.arquivo.html.Body;
import com.antonio.loto.arquivo.html.Tr;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * @author Junior
 */
public class Leitor {

    public static Body ler(InputStream input) throws FileNotFoundException, IOException {

        File arq = new File("src/main/resources/tempfile");
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
        int primeiro = str.indexOf("</tr>");
        int utimo = str.lastIndexOf("</tr>");

        StringBuilder builder = new StringBuilder();
        builder.append("<body>");
        builder.append(str.substring(primeiro + 5, utimo));
        builder.append("</tr>");
        builder.append("</body>");
        return (Body) xStream.fromXML(builder.toString());
    }

    public static File gravaArquivoDeURL(String stringUrl, String pathLocal) {
        try {
            System.setProperty("http.maxRedirects", "40");
            //Encapsula a URL num objeto java.net.URL
            URL url = new URL(stringUrl);
//            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.30 (KHTML, like Gecko) Ubuntu/11.04 Chromium/12.0.742.112 Chrome/12.0.742.112 Safari/534.30");
            try {
                /*System.out.println("Response code = " + */ connection.getResponseCode();//);
            } catch (java.net.ProtocolException ex) {
                throw new Exception("Não foi possivel baixar o arquivo");
            }

            //Queremos o arquivo local com o mesmo nome descrito na URL
            //Lembrando que o URL.getPath() ira retornar a estrutura 
            //completa de diretorios e voce deve tratar esta String
            //caso nao deseje preservar esta estrutura no seu disco local.
            String nomeArquivoLocal = url.getPath();
            if (!nomeArquivoLocal.isEmpty()) {
                String[] path = nomeArquivoLocal.trim().split("/");
                nomeArquivoLocal = path[path.length - 1];
            }
            //Cria streams de leitura (este metodo ja faz a conexao)...
            InputStream is = url.openStream();
            //... e de escrita.
            FileOutputStream fos = new FileOutputStream(pathLocal + nomeArquivoLocal);
            //Le e grava byte a byte. Voce pode (e deve) usar buffers para
            //melhor performance (BufferedReader).
            int umByte = 0;
            while ((umByte = is.read()) != -1) {
                fos.write(umByte);
            }
            //Nao se esqueca de sempre fechar as streams apos seu uso!
            is.close();
            fos.close();
            //apos criar o arquivo fisico, retorna referencia para o mesmo
            return new File(pathLocal + nomeArquivoLocal);
        } catch (Exception e) {
            //Lembre-se de tratar bem suas excecoes, ou elas tambem lhe tratarão mal!
            //Aqui so vamos mostrar o stack no stderr.
            e.printStackTrace();
        }
        return null;
    }

    public static List<Resultado> obterListaResultado(Body resultado) {
        List<Resultado> retorno = new ArrayList<Resultado>();
        for (Tr tr : resultado.getLinhas()) {
            retorno.add(new Resultado(tr.getColunas().toArray()));
        }
        return retorno;
    }
}
