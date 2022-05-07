package com.alv.util;

import java.util.ArrayList;
import java.util.List;

import com.alv.dto.VarDto;


public class EngineXml {
	

	
	public static String reemplazar(VarDto content) {
		StringBuilder cons = new StringBuilder("->");

		if (content.getLista() == null) {
			for (VarDto item : content.getLista()) {
				cons.append(reemplazar(item));
			}
		}

		cons.append(content.getValue() + "<-");

		return cons.toString();
	}

	public static List<VarDto> getArbolCompleto(String content) {
		return getArbolCompletoByContent(content);
	}

	private static List<VarDto> getArbolCompletoByContent(String content) {

		List<VarDto> variables = procesingLXml(content);
		String subContent = "";
		for (VarDto item : variables) {
			subContent = getContent(content, item.getName());
			content = content.replace(">"+subContent+"<", ">--procesado--<");
			item.setValue(subContent);
		}

		return variables;
	}

	public static List<VarDto> procesingLXml(String content) {
		List<VarDto> variables = new ArrayList<VarDto>();
		String varEncontrada = "";
		
		String auxTemp = "";
		for (int i = 0; i < content.length(); i++) {
			varEncontrada = "";
			auxTemp = "";
			if ("<".equals(String.valueOf(content.charAt(i))) && "@".equals(String.valueOf(content.charAt(i + 1)))) {
				i = i + 2;
				while (i < content.length() && !">".equals(String.valueOf(content.charAt(i)))) {
					varEncontrada += content.charAt(i);
					i++;
				}
				i++;

				while (i < content.length() && !auxTemp.equals(varEncontrada)) {

					if ("<".equals(String.valueOf(content.charAt(i))) && "@".equals(String.valueOf(content.charAt(i + 1))) && "/".equals(String.valueOf(content.charAt(i + 2)))) {
						i = i + 3;

						auxTemp = "";
						while (i < content.length() && !">".equals(String.valueOf(content.charAt(i)))) {
							auxTemp += content.charAt(i);
							i++;
						}
						i++;

					}
					i++;
				}
				VarDto add = new VarDto();
				add.setName(varEncontrada);

				variables.add(add);
			}
		}
		return (new ArrayList<VarDto>(variables));
	}

	public static String getContent(String content, String label) {
		String subContent = "";
		int i = content.indexOf("<@" + label + ">");
		subContent = content.substring(i + label.length() + 3, content.length());

		i = subContent.indexOf("<@/" + label + ">");
		
		subContent = subContent.substring(0, i);

		return subContent;
	}

	public static String espaciadoTrim(String content, String firstLine) {

		int indice = content.lastIndexOf(firstLine);
		int i = indice;
		StringBuilder cons = new StringBuilder();
		while (!String.valueOf(content.charAt(i)).equals("\n")) {
			cons.append(" ");
			i--;
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

	public static String getTabs(int len) {
		StringBuilder cons = new StringBuilder();
		for (int i = 0; i < len; i++) {
			cons.append("\t");
		}
		return cons.toString();
	}

	public static String getByKey(String key, String content) {
		String value = getContent(content, key);
		return value;
	}

	public static String getByKey(String key, List<VarDto> lista) {
		for (VarDto varDto : lista) {
			if (varDto.getName().toUpperCase().equals(key.toUpperCase())) {
				return varDto.getValue();
			}
		}

		return "";
	}

	public static boolean Balanceo(String contentLine) {
		int acumulador = 0;
		String linea = "";

		for (int i = 0; i < contentLine.length(); i++) {
			linea = String.valueOf(contentLine.charAt(i));

			if ((linea.indexOf("{") >= 0 || linea.toUpperCase().indexOf("(") >= 0)) {
				acumulador++;
			}

			if ((linea.indexOf("}") >= 0 || linea.toUpperCase().indexOf(")") >= 0)) {
				acumulador--;
			}
			if (acumulador < 0) {
				return false;
			}
		}
		return true;

	}

	public static int getAcumuladorCount(String contentLine, boolean isOpen) {
		int acumulador = 0;
		String linea = "";		

		for (int i = 0; i < contentLine.length(); i++) {
			linea = String.valueOf(contentLine.charAt(i));

			if (isOpen && (linea.indexOf("{") >= 0 || linea.toUpperCase().indexOf("(") >= 0)) {
				acumulador++;
			}

			if (!isOpen && (linea.indexOf("}") >= 0 || linea.toUpperCase().indexOf(")") >= 0)) {
				acumulador--;
			}

		}
		return acumulador;

	}
	
	public static List<VarDto> clonarLista(List<VarDto> lista) {
		List<VarDto> clone = new ArrayList<VarDto>();
		for (VarDto varDto : lista) {
			clone.add(new VarDto(varDto));
		}
		
		return clone;
	}
	
	
	
	
}
