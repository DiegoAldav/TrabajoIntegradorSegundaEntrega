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
	
	
	@Test
	public void test7() throws Exception {
		String archivo = "D:\\archivos\\resultados.csv";
		List<String> idpartidos = Files.readAllLines(Paths.get(archivo));
		 String[] rondas = idpartidos.get(1).split(",");
		 String r1 = rondas[0];
	        Ronda ronda1 = new Ronda();
	        ronda1.setNro(r1);
	        int nRonda = 0;
	        for (int x = 1; x < idpartidos.size(); x++) {
	            String[] idpart = idpartidos.get(x).split(",");
	            if (idpart[0].startsWith(r1)) {
	                nRonda += 1;
	            }
	        }
	        
	        // Participante y puntos
	        
	        Participante participante1 = new Participante();      
	        int puntosJugador1 = 0;
	        
	        // Primer Ronda
	        
	        List<String> idpartido = Files.readAllLines(Paths.get(archivo));
	        //idpartido.remove(0);

	        Partido[] unaRonda = new Partido[nRonda+1/*idpartidos.size()*/];

	        for (int i = 1; i < (nRonda+1); i++) {
	            String[] partidos = idpartido.get(i).split(",");

	            Equipo equipo1 = new Equipo();
	            equipo1.setNombre(partidos[1]);
	            Equipo equipo2 = new Equipo();
	            equipo2.setNombre(partidos[4]);

	            int golesEquipo1 = Integer.parseInt(partidos[2]);
	            int golesEquipo2 = Integer.parseInt(partidos[3]);

	            Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);

	            unaRonda[i] = partido;

	        }
	        
	        //////////////////////////////////////////////////////
	        // LARGO DE LA SEGUNDA RONDA

	        int segRonda = 0;
	        for (int x = 1; x < idpartidos.size(); x++) {
	            String[] idpart = idpartidos.get(x).split(",");
	            if (idpart[0].startsWith("2")) {
	                segRonda += 1;
	            }
	        }
	        
	        // SEGUNDA RONDA

            Partido[] segundaRonda = new Partido[idpartido.size()];
        if ( idpartido.size() != (nRonda+1)) {

            for (int i = (nRonda + 1); i < idpartido.size(); i++) {
                String[] partidos = idpartido.get(i).split(",");

                Equipo equipo1 = new Equipo();
                equipo1.setNombre(partidos[1]);
                Equipo equipo2 = new Equipo();
                equipo2.setNombre(partidos[4]);

                int golesEquipo1 = Integer.parseInt(partidos[2]);
                int golesEquipo2 = Integer.parseInt(partidos[3]);

                Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);

                segundaRonda[i] = partido;

            }
        }
        
        //   PRONOSTICOS CALCULO DE CANTIDAD DE PARTIDOS
        String archivo1 = "D:\\archivos\\pronostico.csv";
        List<String> idpronosticos = Files.readAllLines(Paths.get(archivo1));
        
        String[] idpp1 = idpronosticos.get(1).split(",");
        String p1 = idpp1[0];
        participante1.setNombre(p1);
        //System.out.println("celda de mariana contiene " + p1);
        int nPron = 0;
        for (int x = 1; x < nRonda+1; x++) {
            String[] idpp2 = idpronosticos.get(x).split(",");
            if (idpp2[0].equals(p1)) {
                nPron += 1;
            }
        }

        if ( nRonda != nPron){
            System.out.println("la cantidad de partidos de la primer ronda es distinta a la cantidad de partidos de pronosticos");
            System.exit(0);
        }
        
       
        

        ////////////////////////////////////////////////////////
        // RONDA 1 JUGADOR 1
        
        System.out.println("asiertos de los participantes");

        for (int i = 1; i < nPron+1/*idpronosticos.size()*/; i++) {
            String[] idpx = idpronosticos.get(i).split(",");
            participante1.setNombre(idpx[0]);

            Equipo equipoprons1 = new Equipo(idpx[1], "");
            Equipo equipoprons2 = new Equipo(idpx[5], "");

            int egp1 = 0;
            int egp2 = 0;
            //Resultado prediccion = null;
            if (idpx[2] != "") {
                egp1 = 1;
                egp2 = 0;
                //prediccion = Resultado.ganador;
            } else if (idpx[4] != "") {
                egp1 = 0;
                egp2 = 1;
                //prediccion = Resultado.perdedor;
            } else if (idpx[3] != "") {
                egp1 = 0;
                egp2 = 0;
                //prediccion = Resultado.empate;
            } else {
                System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv");
                if (unaRonda[i].getGolesEquipo1() == unaRonda[i].getGolesEquipo2()) {
                    egp1 = 30;
                }
                //prediccion = Resultado.desconocido;
            }

            Partido partidopronx = new Partido(equipoprons1, equipoprons2, egp1, egp2);

            Pronostico pronosticox = new Pronostico(unaRonda[i], unaRonda[i].getEquipo1(), partidopronx.resultado(equipoprons1));

            if (pronosticox.puntos() == 1) {
                System.out.println(participante1.nombre + " asierto en el partido " + i + " de la primer ronda entre " + equipoprons1.getNombre()+ " y " + equipoprons2.getNombre() + " suma un punto.");
            }
            puntosJugador1 += pronosticox.puntos();
        }
        System.out.println("");
        
        // CALCULO LARGO RONDA 2
        
        int nPron2 = nPron+nPron+nPron+nPron;

        int segPron = 0;
        for (int x = nPron2; x < (nPron2+segRonda+1); x++) {
            String[] idpp2 = idpronosticos.get(x).split(",");
            if (idpp2[0].startsWith(p1)/*.equals(p1)*/) {
                segPron += 1;
            }
        }
        
        
        // ronda 2  jugador 1
        
       

        if ( idpronosticos.size() != (nPron2+1)) {
            if (idpartido.size() != (nRonda + 1)) {

                for (int z = (nPron2 + 1); z < (nPron2 + segPron + 1); z++) {
                    int j = (z-nPron2);
                    int i = (nPron+j);
                    String[] idpz = idpronosticos.get(z).split(",");
                    //participante4.setNombre(idpz[0]);
                    //participante4.setNombre(idpz[0]);
                    //System.out.println("jugador 1 ronda 2 j: "+j+" i: "+i);

                    //int p = z;
                    //int p2 = z + 1;
                    Equipo equipopron1 = new Equipo(idpz[1], "");
                    Equipo equipopron2 = new Equipo(idpz[5], "");


                    int egp2p = 0;
                    int egp2p2 = 0;
                    if (idpz[2] != "") {
                        egp2p = 1;
                        egp2p2 = 0;
                    } else if (idpz[4] != "") {
                        egp2p = 0;
                        egp2p2 = 1;
                    } else if (idpz[3] != "") {
                        egp2p = 0;
                        egp2p2 = 0;
                    } else {
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante1.nombre + " en la segunda ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2p = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1, equipopron2, egp2p, egp2p2);
                    //System.out.println("segunda ronda: "+partidopron2z.getGolesEquipo1()+" "+i+" "+segundaRonda[i].getGolesEquipo1());

                    Pronostico pronostico2z = new Pronostico(segundaRonda[i], segundaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante1.nombre + " asierto en el partido " + j + " de la segunda ronda entre " + equipopron1.getNombre() + " y " + equipopron2.getNombre() + " suma un punto.");
                    }
                    puntosJugador1 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");

        
        //jugador y puntos
        
        System.out.println("PARTICIPANTES CON SUS PUNTOS");
        System.out.println("jugador/a: " + participante1.nombre + " total de puntos:" + puntosJugador1);
    participante1.setPuntosParticipante(puntosJugador1);

        int resultado = participante1.getPuntosParticipante();
        
        int esperado = 25;
        /////////////////////////////////////////

	    System.out.println("esperado: "+esperado);
	    System.out.println("resultado: "+resultado);
		
		assertEquals(esperado, resultado);
	}
	
}
