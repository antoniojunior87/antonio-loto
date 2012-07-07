package com.antonio.loto;

import com.antonio.loto.arquivo.Leitor;
import com.antonio.loto.arquivo.html.Body;
import com.antonio.loto.arquivo.html.Tr;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws ZipException, IOException {
        ZipFile zip = new ZipFile(Leitor.gravaArquivoDeURL("http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_megase.zip", "src/main/resources/"));
//        ZipFile zip = new ZipFile(new File("src/main/resources/D_megase.zip"));
        Enumeration e = zip.entries();
        while (e.hasMoreElements()) {
            ZipEntry arq = (ZipEntry) e.nextElement();
            if (arq.getName().endsWith(".HTM")) {
                try {
                    InputStream in = zip.getInputStream(arq);
                    Body resultado = Leitor.ler(in);
                    List<Resultado> retorno = Leitor.obterListaResultado(resultado);
                    for (Resultado result : retorno) {
                        System.out.println(result.toString());
                    }
                } finally {
                    zip.close();
                }
                break;
            }
        }
    }
}
