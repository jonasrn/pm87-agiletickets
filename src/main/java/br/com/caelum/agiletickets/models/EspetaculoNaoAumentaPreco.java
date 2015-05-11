package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public class EspetaculoNaoAumentaPreco extends EspetaculoCalcula{

	public BigDecimal cacular(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		preco = sessao.getPreco();
		return preco;
	}

}
