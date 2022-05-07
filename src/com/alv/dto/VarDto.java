package com.alv.dto;

import java.util.ArrayList;
import java.util.List;

import com.alv.util.EngineXml;

public class VarDto implements Cloneable{

	private String name;
	private String value;
	private List<VarDto> lista;
	
	public  VarDto() {}
	public  VarDto(VarDto init) {
		this.name = init.name;
		this.value = init.value;
		if(init.lista != null) {
			this.lista = new ArrayList<VarDto>();
			for (VarDto item : init.lista) {
				this.lista.add(new VarDto(item));
			}
		} else {
			this.lista = init.lista;
		}
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		
		if(this.name.indexOf("-")< 0){
		List<VarDto> varN = EngineXml.procesingLXml(value);
		String content = "";
		String backUpValue = value;
		if(varN.size() > 0){
			for(VarDto item: varN) {
					
					content = EngineXml.getContent(backUpValue, item.getName());
					backUpValue = backUpValue.replace("<@"+item.getName()+">"+content+"<@/"+item.getName()+">", "--procesado--");
					
				item.setValue(content);
				
			}
			lista = varN;
		}
		}
		this.value = value.trim();
	}
	public List<VarDto> getLista() {
		return lista;
	}
	public void setLista(List<VarDto> lista) {
		this.lista = lista;
	}
	
	public VarDto clone() {
        try {
            return (VarDto) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
	
	
	
}
