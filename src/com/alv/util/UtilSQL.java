package com.alv.util;

public class UtilSQL {

	
	public static String tipoCampo(String tipo) {
		String campoSql = tipo;
		
		
		switch (tipo.toUpperCase()) {
		
		case "FLOAT":
			campoSql ="double";
		break;
		
		case "DECIMAL":
			campoSql ="double";
		break;
		case "NUMERIC":
			campoSql ="long";
		break;
		case "DATETIME":
			campoSql ="DateTime";
		break;
		
		case "DATE":
			campoSql ="DateTime";
		break;
		
		case "TEXT":
			campoSql ="string";
		break;
		
		case "VARCHAR":
			campoSql ="string";
		break;
		
		case "NVARCHAR":
				campoSql ="string";
			break;
			
		case "INT":
			campoSql ="int";
		break;
		
		case "BIT":
			campoSql ="bool";
		break;
		
		default:
			break;
		}		
		return campoSql;
	}
	
	public static String tipoCampoConvert(String tipo,String texto) {
		String campoSql = tipo;
		
		
		switch (tipo.toUpperCase()) {
		
		case "NUMERIC":
			campoSql ="Convert.ToInt64(row[\"" + texto + "\"])";
		break;
		case "DECIMAL":
			campoSql ="Convert.ToDouble(row[\"" + texto + "\"])";
		break;
		case "DATETIME":
			campoSql ="Convert.ToDateTime(" + texto + ")";
		break;
		
		case "TEXT":
			campoSql ="Convert.ToString(row[\"" + texto + "\"])";
		break;
		
		case "NVARCHAR":
				campoSql ="Convert.ToString(row[\"" + texto + "\"])";
			break;
			
		case "INT":
			campoSql ="Convert.ToInt32(row[\"" + texto + "\"].ToString() == \"\" ? 0 : row[\"" + texto + "\"])";
		break;

		default:
			break;
		}		
		return campoSql;
	}
	
	
	
	
	 
			
}
