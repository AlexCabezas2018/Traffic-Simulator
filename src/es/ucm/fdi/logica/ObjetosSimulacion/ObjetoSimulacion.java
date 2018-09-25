//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica.ObjetosSimulacion;
import es.ucm.fdi.ini.IniSection;

public abstract class ObjetoSimulacion {
		 protected String id;

		 public ObjetoSimulacion(String id) {
		 	this.id = id;
		 }

		 public String getId() {return id;}

		 @Override
		 public String toString(){
		 	return id;
		 }


		 public String generaInforme(int tiempo) {
			 IniSection is = new IniSection(this.getNombreSeccion());
			 is.setValue("id", this.id);
			 is.setValue("time", tiempo + 1);
			 this.completaDetallesSeccion(is);
			 return is.toString();
		 }

		 public abstract void completaDetallesSeccion(IniSection is);
		 public abstract String getNombreSeccion();
		 public abstract void avanza();
		 
		}


