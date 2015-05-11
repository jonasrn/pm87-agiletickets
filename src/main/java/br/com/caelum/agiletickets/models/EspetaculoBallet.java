package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public class EspetaculoBallet extends EspetaculoCalcula {


	public BigDecimal cacular(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		if (sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {

			preco = getPrecoSessao(sessao, 0.50, 0.20);

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = preco.add(sessao.getPreco().multiply(
						BigDecimal.valueOf(0.10)));
			}
			return preco;
		} else {
			return null;
		}

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
