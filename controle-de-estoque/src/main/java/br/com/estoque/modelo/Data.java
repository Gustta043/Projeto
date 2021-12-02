package br.com.estoque.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'as' HH:mm:ss ");
	private static Date dataAtual = new Date(System.currentTimeMillis());
	
	public static String getDataAtual() {
		return formatter.format(dataAtual);
	}
}
