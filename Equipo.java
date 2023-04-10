package Domain;

public class Equipo {
	
	private String nombre;
	
	private String descripcion;
	
	 public Equipo() {

	    }
	
	
	public Equipo(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String unNombre) {
		this.nombre = unNombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String unaDescripcion) {
		this.descripcion = unaDescripcion;
	}

}
