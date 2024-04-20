package br.edu.cesarschool.cc.poo.ac.utils;

public class StringUtils {
	private StringUtils(String valor) {
		
	}
	
	public static boolean isVaziaOuNula(String valor) {
		if (valor == null || valor.trim().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
}
