/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonio.loto.arquivo.html;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.List;

/**
 *
 * @author Junior
 */
@XStreamAlias("tr")
public class Tr {

    private List<Object> colunas;

    public List<Object> getColunas() {
        return colunas;
    }

    public void setColunas(List<Object> colunas) {
        this.colunas = colunas;
    }
}
