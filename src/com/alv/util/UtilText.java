package com.alv.util;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;

public class UtilText {
	
	public static String DB_MAYUSCULAS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	//public static String CHARS_MATRIX = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZavcdefghijklmnñopqrstuvwxyz0123456789";
	//public static String CHARS_MATRIX = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZavcdefghijklmnñopqrstuvwxyz0123456789";
	public static String CHARS_MATRIX = "0 1";
	public static int DIGITO_VERIFICACION = 0;

	public static Set<String> obtenerVariables(String content, String token) {
		int longitud = content.length();
		String twoToken = token + token;
		Set<String> variables = new HashSet<String>();
		String variable = "";
		for (int i = 0; i < longitud - 3; i++) {

			variable = "";
			if (token.equals(String.valueOf(content.charAt(i)))
					&& token.equals(String.valueOf(content.charAt(i + 1)))) {
				variable = variable + twoToken;

				i = i + 2;
				while ((!token.equals(String.valueOf(content.charAt(i)))
						|| !token.equals(String.valueOf(content.charAt(i + 1)))) && i < longitud) {

					variable = variable + content.charAt(i);

					i++;
				}
				variable = variable + content.charAt(i);
				variable = variable + token;
				// System.out.println("*** " + variable);
				variables.add(variable);
				i = i + 2;
			}
		}
		return variables;
	}

	public static String getLabel(String nombre) {

		StringBuilder cons = new StringBuilder();
		boolean encontro = false;
		for (int i = 0; i < nombre.length(); i++) {
			encontro = false;
			for (int j = 0; j < DB_MAYUSCULAS.length(); j++) {
				if (String.valueOf(nombre.charAt(i)).equals(String.valueOf(DB_MAYUSCULAS.charAt(j)))) {
					encontro = true;
					break;
				}
			}
			if (encontro) {
				cons.append(" " + String.valueOf(nombre.charAt(i)).toUpperCase());
			} else {
				cons.append(String.valueOf(nombre.charAt(i)));
			}

		}

		return cons.toString();
	}

	public static String capitalizeName(String name) {
		String temp = name.toLowerCase();
		StringBuilder cons = new StringBuilder();
		temp = temp.replace("_", " ");
		boolean capitalizar = false;
		for (int i = 0; i < temp.length(); i++) {
			if (capitalizar || i == 0) {
				cons.append(String.valueOf(temp.charAt(i)).toUpperCase());
				capitalizar = false;
			} else {
				if (String.valueOf(temp.charAt(i)).equals(" ")) {
					capitalizar = true;
				} else {
					cons.append(temp.charAt(i));
				}
			}
		}

		return cons.toString();
	}
	public static String toVariableCase(String name) {
		name = capitalizeName(name);
		name = name.substring(0,1).toLowerCase() + name.substring(1);
		return name;
	}
	
	public static String getTabs(int len) {
		StringBuilder cons = new StringBuilder();
		for (int i = 0; i < len; i++) {
			cons.append("\t");
		}
		return cons.toString();
	}
	
	public static String paddingComplete(String text, int length, String toFill ) {
		
		StringBuilder cons = new StringBuilder();
		cons.append(text);
		for(int i = text.length() ; i < length ; i++ ) {
			cons.append(toFill);
		}
		
		return cons.toString();
		
	}
	
	public static String converVarToSQLNotation(String nameVar) {
		
		StringBuilder cons = new StringBuilder();
		cons.append(nameVar.charAt(0));
		for (int i = 1; i < nameVar.length(); i++) {
			
			if(DB_MAYUSCULAS.contains((String.valueOf(nameVar.charAt(i))))) {
				cons.append("_"+nameVar.charAt(i));
			} else {
				cons.append(nameVar.charAt(i));	
			}
			
				
		}
		
		return cons.toString().toUpperCase();
		
	}
	public static String TranslateToHTMLMultilina(String texto, int largoRenglon) {
		StringBuilder cons = new StringBuilder();
		
		String[] words = texto.split(" ");
		
		StringBuilder row = new StringBuilder();
		for (String word : words) {
			String auxiliar = row.toString();
			
			if((auxiliar.length() + word.length())  >= largoRenglon) {
				if(auxiliar.length()!=0) {
					cons.append(row.toString() + "<br />");
				}
				row = new StringBuilder();
				if(word.length()> largoRenglon) {
					while(word.length() > largoRenglon) {						
						row.append(word.substring(0,largoRenglon) + "<br/>");
						word = word.substring(largoRenglon,word.length());						
					}
					row.append(word + "<br/>");
				} else {
					row.append(word);	
				}
				
				
			} else {
				row.append(" " + word);
			}
		}
		cons.append(row.toString());
		
		
		return "<html>"+cons.toString()+"</html>";
		
	}
	public static String TokenDiario() {
		Calendar calendario = Calendar.getInstance();
		int anio =calendario.get(Calendar.YEAR);
		int mes =calendario.get(Calendar.MONTH)+1;
		int dia = calendario.get(Calendar.DAY_OF_MONTH);		
		return (String.valueOf(anio))+(mes<10 ? "0"+String.valueOf(mes) : String.valueOf(mes))+(dia<10 ? "0"+String.valueOf(dia) : String.valueOf(dia));		
	}
	public static String Token() {
		Calendar calendario = Calendar.getInstance();
		int anio =calendario.get(Calendar.YEAR);
		int mes =calendario.get(Calendar.MONTH)+1;
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		int minuto = calendario.get(Calendar.MINUTE);
		int segundo = calendario.get(Calendar.SECOND);
		int milisegundo = calendario.get(Calendar.MILLISECOND);
		DIGITO_VERIFICACION++;
		int digito = DIGITO_VERIFICACION;
		
		return (String.valueOf(anio))+(mes<10 ? "0"+String.valueOf(mes) : String.valueOf(mes))+(dia<10 ? "0"+String.valueOf(dia) : String.valueOf(dia))
				+(dia<10 ? "0"+String.valueOf(dia) : String.valueOf(dia))
				+(minuto<10 ? "0"+String.valueOf(minuto) : String.valueOf(minuto))
				+(segundo<10 ? "0"+String.valueOf(segundo) : String.valueOf(segundo))
				+(String.valueOf(milisegundo))
				+(String.valueOf(digito));
	}
	
	public static String getMatrixString(int length) {
		
		Random rand = new Random();
		StringBuilder cons = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int val= (int)(rand.nextFloat()*((float)CHARS_MATRIX.length()));
			cons.append(CHARS_MATRIX.substring(val, val+1));
		}
		
		return cons.toString();
	}
	public static String getSpaces(int len) {
		StringBuilder cons = new StringBuilder();
		for (int i = 0; i < len; i++) {
			cons.append("  ");
		}
		return cons.toString();
	}
	
	
	public static boolean isNullOrEmpty(String var) {
		return (var == null) || (var.length() == 0);
	}
	

}
