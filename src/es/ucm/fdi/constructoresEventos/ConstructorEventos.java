//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.ini.Ini;
import es.ucm.fdi.ini.IniSection;

public abstract class ConstructorEventos {
	protected String etiqueta;
	protected String[] claves;
	protected String[] valoresPorDefecto;
	
	ConstructorEventos(){
		this.etiqueta = null;
		this.claves = null;
		this.valoresPorDefecto = null;
	}
	
	public abstract Evento parser(IniSection section);
	
	protected static int parseaInt(IniSection seccion, String clave){
		String v = seccion.getValue(clave);
		if(v == null){
			throw new IllegalArgumentException("Valor inexistente para la clave: " + clave);	
		}
		else return Integer.parseInt(seccion.getValue(clave));
	}
	
	protected static int parseaInt(IniSection seccion, String clave, int vDefecto){
		String v = seccion.getValue(clave);
		return(v != null) ? Integer.parseInt(seccion.getValue(clave)) : vDefecto;
	}
	
	protected static int parseaIntNoNegativo(IniSection seccion, String clave, int vDef){
		int i = ConstructorEventos.parseaInt(seccion, clave, vDef);
		if(i < 0){throw new IllegalArgumentException("El valor " + i + " para " + clave +" no es un ID valido");}
		else return i;
	}
	
	protected static String identificadorValido(IniSection seccion, String clave){
		String s = seccion.getValue(clave);
		if(!esIdentificadorValido(s)){
			throw new IllegalArgumentException("El valor " + s + " para " + clave + " no es un ID valido");
		}
		else return s;
	}

	protected static String[] conjuntoIdsValidos(IniSection seccion, String clave){
		String g[] = seccion.getValue(clave).split(",");
		for(String x: g){
			if(!esIdentificadorValido(x))
				throw new IllegalArgumentException("El valor " + x + " para " + clave + " no es un ID valido");
		}
		return g;
	}
	
	private static boolean esIdentificadorValido(String id){
		return id != null && id.matches("[a-z0-9_]+");
	}
	protected long parseaLong(IniSection section, String clave){
		String x = section.getValue(clave);
		if(x != null){
			return Long.parseLong(x);
		}
		else{
			throw new IllegalArgumentException("Valor inexistente para la clave: " + clave);
		}
	}

	protected double parseaDouble(IniSection ini, String clave){
		String x = ini.getValue(clave);
		if(x != null){
			return Double.parseDouble(x);
		}
		else throw new IllegalArgumentException("Valor inexistente para la clave: " + clave);
	}

	public String template(){
		String ret = "";
		ret += "[" + this.etiqueta + "]\n";
		ret += "id = \n";
		ret += "time = \n";
		ret += completaTemplate();

		return ret;
	}

	public abstract String completaTemplate();
}
