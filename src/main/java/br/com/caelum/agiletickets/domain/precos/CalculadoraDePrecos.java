package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.EspetaculoCalcula;
import br.com.caelum.agiletickets.models.EspetaculoCinema;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {

		
		BigDecimal preco;
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			preco = getPrecoSessao(sessao, 0.05, 0.10);
			
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			
			preco = getPrecoSessao(sessao, 0.50, 0.20);
		
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			
			preco = getPrecoSessao(sessao, 0.50, 0.20);

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade)); 

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