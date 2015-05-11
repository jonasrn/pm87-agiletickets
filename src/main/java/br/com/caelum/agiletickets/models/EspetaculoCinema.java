package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public class EspetaculoCinema {
	


	public BigDecimal cacular(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			preco = getPrecoSessao(sessao, 0.05, 0.10);
			return preco;
		}
		return null;
	}

	private static BigDecimal getPrecoSessao(Sessao sessao, double percentual, double reajuste) {
		BigDecimal preco;
		if(sessao.getIngressosDisponiveis() / sessao.getTotalIngressos().doubleValue() <= percentual) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(reajuste)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}
}