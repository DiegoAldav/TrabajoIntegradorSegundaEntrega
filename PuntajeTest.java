package Programa;

import static org.junit.jupiter.api.Assertions.*;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
//import org.junit.Before;

import Domain.Participante;
import Domain.Partido;
import Domain.Ronda;
import Domain.Equipo;
import Domain.Pronostico;
import Enum.Resultado;

class PuntajeTest {
	
	
	@Test
	public void test1() throws Exception {
		String archivo = "D:\\archivos\\resultados.csv";
		List<String> idpartidos = Files.readAllLines(Paths.get(archivo));
		 String[] rondas = idpartidos.get(1).split(",");
	        String r1 = rondas[0];
	        Ronda ronda1 = new Ronda();
	        ronda1.setNro(r1);
	    String esperado = "1";
	    String resultado = ronda1.getNro();
	    System.out.println(esperado);
	    System.out.println(resultado);
		
		assertEquals(esperado, resultado);
	}
	
	
	@Test
	public void test2() throws Exception {
		String archivo1 = "D:\\archivos\\pronostico.csv";
		 List<String> idpronosticos = Files.readAllLines(Paths.get(archivo1));
		 String[] idpp1 = idpronosticos.get(1).split(",");
	        String p1 = idpp1[0];
	        Participante participante1 = new Participante();
	        participante1.setNombre(p1);
	    String esperado = "Mariana";
	    String resultado = participante1.getNombre();
	    System.out.println(esperado);
	    System.out.println(resultado);
		
		assertEquals(esperado, resultado);
	}
	

	@Test
	public void test3() {
		Equipo equipo1 = new Equipo("Qatar","");
		Equipo equipo2 = new Equipo("Senegal","");
		Partido partido1 = new Partido(equipo1,equipo2,1,2);
		Equipo equipo = new Equipo();
		equipo = partido1.getEquipo1();
		String resultado = equipo.getNombre();
		String esperado = "Qatar";		
		System.out.println(resultado);
		System.out.println(esperado);
		
		assertEquals(esperado, resultado);
	}
	
	
	@Test
	public void test4() {
		Equipo equipo1Pronostico = new Equipo();
		equipo1Pronostico.setNombre("Argentina");
		Equipo equipo2Pronostico = new Equipo();
		equipo2Pronostico.setNombre("mexico");
		Partido partidoPronostico = new Partido(equipo1Pronostico,equipo2Pronostico,1,0);
		Resultado resultado = partidoPronostico.resultado(equipo1Pronostico);		
		Resultado esperado = Resultado.ganador;
		
		System.out.println(resultado);
		System.out.println(esperado);
		assertEquals(esperado, resultado);
	}
	
	
	@Test
	public void test5() {
		Equipo equipo1 = new Equipo("Brasil","");
		Equipo equipo2 = new Equipo("Camerun","");
		Partido partido = new Partido(equipo1,equipo2,3,0);
		
		Equipo equipo1Pronostico = new Equipo();
		equipo1Pronostico.setNombre("Brasil");
		Equipo equipo2Pronostico = new Equipo();
		equipo2Pronostico.setNombre("Camerun");
		Partido partidoPronostico = new Partido(equipo1Pronostico,equipo2Pronostico,1,0);
		Pronostico pronostico1 = new Pronostico(partido,equipo1,partidoPronostico.resultado(equipo1Pronostico));
		
		int resultado = 0;
		resultado += pronostico1.puntos();
		int esperado = 1;	
		System.out.println(resultado);
		System.out.println(esperado);
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void test6() {
	 int[] orden = new int[4];
     orden[0] = 38;
     orden[1] = 26;
     orden[2] = 32;
     orden[3] = 34;

     Arrays.sort(orden);
     int[] b = new int[orden.length];
     for (int i = 0; i < b.length; i++) {
         b[i] = orden[b.length-1-i];
     }
     int[] resultado = b;
     /*for (int i = 0; i < b.length; i++) {
    	 System.out.println(b[i]);
     }*/
     int[] esperado = {38,34,32,26};
     assertArrayEquals(esperado,resultado);
     }
	
}
