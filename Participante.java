package Domain;

public class Participante {
	
	public String nombre;
    public int puntosParticipante;

    public Participante() {
    }

    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPuntosParticipante(int puntosParticipante) {
        this.puntosParticipante = puntosParticipante;
    }

    public int getPuntosParticipante() {
        return puntosParticipante;
    }

}
