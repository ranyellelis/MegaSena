package br.com.mega;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;


public class Sorteio {

	private TrataDados dados;
	
	public Sorteio(TrataDados dados){
		this.dados = dados;
	}
	
	public ArrayList<Integer> getBolas() {
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		for (int i = 0; i <= 60; i++) {
			retorno.add(Integer.valueOf(i));
		}
		return retorno;
	}
	
	public Aposta sorteiaBolasPadrao(){
		ArrayList<Integer> sorteio = new ArrayList<Integer>();
		for (int i = 0; sorteio.size() < 6; i++) {
			int dezena = retiraBola().intValue();
			if (!sorteio.contains(Integer.valueOf(dezena)) && dezena !=0 ) {
				sorteio.add(dezena);
			}
		}
		Collections.sort(sorteio);  
		Aposta aposta = montaAposta(sorteio);
		return aposta;
	}
	
	public Aposta sorteiaBolasMaisSorteadas(){
		ArrayList<Integer> sorteio = new ArrayList<Integer>();
		for (int i = 0; sorteio.size() < 6; i++) {
			int dezenaSorteada = retiraBola().intValue();
			if (!sorteio.contains(Integer.valueOf(dezenaSorteada))) {
				if(estaNosMaisJogados(sorteio, dezenaSorteada) && dezenaSorteada !=0 ){
					sorteio.add(dezenaSorteada);
				}
			}
		}
		Collections.sort(sorteio);  
		Aposta aposta = montaAposta(sorteio);
		return aposta;
	}
	
	private boolean estaNosMaisJogados(ArrayList<Integer> sorteio, Integer dezenaSorteada){
		for (Integer maisJogado : this.dados.getNumerosMaisJogados()) {
			if(maisJogado.equals(dezenaSorteada)){
				return true;
			}
		}
		return false;
	}
	
	
	public Aposta sorteiaBolasMenosSorteadas(){
		ArrayList<Integer> sorteio = new ArrayList<Integer>();
		for (int i = 0; sorteio.size() < 6; i++) {
			int dezenaSorteada = retiraBola().intValue();
			if (!sorteio.contains(Integer.valueOf(dezenaSorteada))) {
				if(estaNosMenosJogados(sorteio, dezenaSorteada) && dezenaSorteada !=0 ){
					sorteio.add(dezenaSorteada);
				}
			}
		}
		Collections.sort(sorteio);  
		Aposta aposta = montaAposta(sorteio);
		return aposta;
	}
	
	private boolean estaNosMenosJogados(ArrayList<Integer> sorteio, Integer dezenaSorteada){
		for (Integer maisJogado : this.dados.getNumerosMenosJogados()) {
			if(maisJogado.equals(dezenaSorteada)){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public Aposta montaAposta(ArrayList<Integer> sorteio){
		Aposta aposta = new Aposta();
		aposta.setNumSorteio(000);
		aposta.setDez1(sorteio.get(0));
		aposta.setDez2(sorteio.get(1));
		aposta.setDez3(sorteio.get(2));
		aposta.setDez4(sorteio.get(3));
		aposta.setDez5(sorteio.get(4));
		aposta.setDez6(sorteio.get(5));
		return aposta;
	}

	private Integer retiraBola() {
		Random r = new Random();
		return Integer.valueOf(r.nextInt(60));
	}
	
	public void mostraResultado(Aposta a, String tipoAposta) {
		StringBuilder r = new StringBuilder();
		r.append(a.getDez1());
		r.append("|");
		r.append(a.getDez2());
		r.append("|");
		r.append(a.getDez3());
		r.append("|");
		r.append(a.getDez4());
		r.append("|");
		r.append(a.getDez5());
		r.append("|");
		r.append(a.getDez6());
		System.out.println(r.toString());
		JOptionPane.showMessageDialog(null, tipoAposta+" "+r.toString());
	}
	
	public void mostraNumerosMaisJogadosPorDezenaCompleto(){
		System.out.println("Números mais jogados, ranking ordenado:");
		exibeDivisoria();
		this.dados.mostraOsMaisJogados();
		exibeDivisoria();
	}
	
	public void mostraApostaNormal(){
		String tipoAposta = "Aposta 'Comum'"; 
		System.out.println(tipoAposta);
		Aposta a = sorteiaBolasPadrao();
		mostraResultado(a, tipoAposta);
		dados.verificaSeJogoJaFoiApostado(a);
		exibeDivisoria();
	}
	
	public void mostraApostaInteligente(){
		String tipoAposta = "Aposta 'Sortudo'"; 
		System.out.println(tipoAposta);
		Aposta a = sorteiaBolasMaisSorteadas();
		mostraResultado(a, tipoAposta);
		dados.verificaSeJogoJaFoiApostado(a);
		exibeDivisoria();
	}
	
	public void mostraApostaAzarona(){
		String tipoAposta = "Aposta 'Azarão'";
		System.out.println(tipoAposta);
		Aposta a = sorteiaBolasMenosSorteadas();
		mostraResultado(a, tipoAposta);
		dados.verificaSeJogoJaFoiApostado(a);
		exibeDivisoria();
	}
	
	private void exibeDivisoria(){
		System.out.println("-----------------------------------");
	}
	
	public void mostraNumerosMaisJogadosFiltrados(){
		System.out.println("Números disponíveis para a aposta 'Sortudo', baseada nos "+ 
				dados.getQtd() +" números mais sorteados de cada dezena");
		System.out.println(dados.getNumerosMaisJogados().toString());
		exibeDivisoria();
	}
	
	public void mostraNumerosMenosJogadosFiltrados(){
		System.out.println("Números disponíveis para a aposta 'Azarão', baseada nos "+ 
					dados.getQtd() +" números menos sorteados de cada dezena");
		System.out.println(dados.getNumerosMenosJogados().toString());
		exibeDivisoria();
	}
}