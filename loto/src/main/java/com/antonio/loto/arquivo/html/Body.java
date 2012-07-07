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
@XStreamAlias("body")
public class Body {

    private List<Tr> linhas;

    public List<Tr> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Tr> linhas) {
        this.linhas = linhas;
    }
}
