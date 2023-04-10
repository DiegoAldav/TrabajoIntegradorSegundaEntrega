package Programa;

import Domain.*;
import Enum.Resultado;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puntaje {

	
	public static void main(String[] args) throws Exception {

        // LECTURA DE ARCHIVOS.CSV

        String archivo = "D:\\archivos\\resultados.csv";
        String archivo1 = "D:\\archivos\\pronostico.csv";

        File result = new File(archivo);
        if (!result.exists()) {
            System.out.println("no encuentra el archivo resultados.csv\npuede que la direccion del archivo esta mal\no puede que el archivo no exista");
            System.exit(0);
        }

        File pronos = new File(archivo1);
        if (!pronos.exists()) {
            System.out.println("no encuentra el archivo pronostico.csv\npuede que la direccion del archivo esta mal\no puede que el archivo no exista");
            System.exit(0);
        }

        /*for (String linea : Files.readAllLines(Paths.get(archivo))) {
            System.out.println(linea);
        }*/

        //  PARTIDOS

        List<String> idpartidos = Files.readAllLines(Paths.get(archivo));

        int s1 = -1;
        int s2 = 0;

        //   PRONOSTICOS

        List<String> idpronosticos = Files.readAllLines(Paths.get(archivo1));

        // SE CREAN LOS PARTICIPANTES

        Participante participante1 = new Participante();
        Participante participante2 = new Participante();
        Participante participante3 = new Participante();
        Participante participante4 = new Participante();

        int puntosJugador1 = 0;
        int puntosJugador2 = 0;
        int puntosJugador3 = 0;
        int puntosJugador4 = 0;

        //////////////////////////////////////////
        //  LARGO DE UNA RONDA

        String[] rondas = idpartidos.get(1).split(",");
        String r1 = rondas[0];
        Ronda ronda1 = new Ronda();
        ronda1.setNro(r1);
        //System.out.println("celda de la ronda 1 contiene " + r1);
        int nRonda = 0;
        for (int x = 1; x < idpartidos.size(); x++) {
            String[] idpart = idpartidos.get(x).split(",");
            if (idpart[0].startsWith(r1)) {
                nRonda += 1;
            }
        }
        //System.out.println("cantidad de partidos en la ronda 1 es: "+nRonda);

        //////////////////////////////////////////
        // PRIMER RONDA

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

        //////////////////////////////////////////////////////
        //   PRONOSTICOS CALCULO DE CANTIDAD DE PARTIDOS

        String[] idpp1 = idpronosticos.get(1).split(",");
        String p1 = idpp1[0];
        participante1.setNombre(p1);
        //System.out.println("celda de mariana contiene " + p1);
        int nPron = 0;
        for (int x = 1; x < idpronosticos.size(); x++) {
            String[] idpp2 = idpronosticos.get(x).split(",");
            if (idpp2[0].equals(p1)) {
                nPron += 1;
            }
        }
        if ( nRonda != nPron){
            nPron= nPron/2;
        }

        ////////////////////////////////////////////////////////
        // RONDA 1 JUGADOR 1
        System.out.println("asiertos de los participantes");

        for (int i = 1; i < nPron+1/*idpronosticos.size()*/; i++) {
            String[] idpx = idpronosticos.get(i).split(",");

            Equipo equipoprons1 = new Equipo(idpx[1], "");
            Equipo equipoprons2 = new Equipo(idpx[5], "");

            int egps1 = 0;
            int egps2 = 0;
            //Resultado prediccion = null;
            if (idpx[2] != "") {
                egps1 = 1;
                egps2 = 0;
                //prediccion = Resultado.ganador;
            } else if (idpx[4] != "") {
                egps1 = 0;
                egps2 = 1;
                //prediccion = Resultado.perdedor;
            } else if (idpx[3] != "") {
                egps1 = 0;
                egps2 = 0;
                //prediccion = Resultado.empate;
            } else {
                System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv");
                if (unaRonda[i].getGolesEquipo1() == unaRonda[i].getGolesEquipo2()) {
                    egps1 = 30;
                }
                //prediccion = Resultado.desconocido;
            }

            Partido partidopronx = new Partido(equipoprons1, equipoprons2, egps1, egps2);

            Pronostico pronosticox = new Pronostico(unaRonda[i], unaRonda[i].getEquipo1(), partidopronx.resultado(equipoprons1));

            if (pronosticox.puntos() == 1) {
                System.out.println(participante1.nombre + " asierto en el partido " + i + " de la primer ronda entre " + equipoprons1.getNombre()+ " y " + equipoprons2.getNombre() + " suma un punto.");
            }
            puntosJugador1 += pronosticox.puntos();
        }
        System.out.println("");

            // FIN JUGADOR 1
            /////////////////////////////////////
            // RONDA 1 JUGADOR 2

            if ( idpronosticos.size() != nPron+1) {

                for (int z = nPron+1; z < (nPron+nPron+1)/*idpronosticos.size()*/; z++) {
                    int i = 0-nPron+z;
                    String[] idpz = idpronosticos.get(z).split(",");
                    participante2.setNombre(idpz[0]);

                    int p = z;
                    int p2 = z+1;
                    Equipo equipopron1p = new Equipo(idpz[1], "");
                    Equipo equipopron2p2 = new Equipo(idpz[5], "");

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
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: "+participante2.nombre);
                        if (unaRonda[i].getGolesEquipo1() == unaRonda[i].getGolesEquipo2()) {
                            egp2p = 30;
                        }

                    }

                    Partido partidopron2z = new Partido(equipopron1p, equipopron2p2, egp2p, egp2p2);

                    Pronostico pronostico2z = new Pronostico(unaRonda[i], unaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2p2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante2.nombre + " asierto en el partido " + i + " de la primer ronda entre " + equipopron1p.getNombre()+ " y " + equipopron2p2.getNombre() + " suma un punto.");
                    }
                    puntosJugador2 += pronostico2z.puntos();
                }

            }
            System.out.println("");

            // FIN JUGADOR 2
            /////////////////////////////////////////////////
            //  RONDA 1 JUGADOR 3

        if ( idpronosticos.size() != nPron+nPron+1) {

            for (int z = nPron+nPron+1; z < nPron+nPron+nPron+1/*idpronosticos.size()*/; z++) {
                int i = 0-nPron-nPron+z;
                String[] idpz = idpronosticos.get(z).split(",");
                participante3.setNombre(idpz[0]);

                int p = z;
                int p2 = z+1;
                Equipo equipopron1p = new Equipo(idpz[1], "");
                Equipo equipopron2p2 = new Equipo(idpz[5], "");


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
                    System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: "+participante3.nombre);
                    if (unaRonda[i].getGolesEquipo1() == unaRonda[i].getGolesEquipo2()) {
                        egp2p = 30;
                    }

                }

                Partido partidopron2z = new Partido(equipopron1p, equipopron2p2, egp2p, egp2p2);

                Pronostico pronostico2z = new Pronostico(unaRonda[i], unaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2p2));

                if (pronostico2z.puntos() == 1) {
                    System.out.println(participante3.nombre + " asierto en el partido " + i + " de la primer ronda entre " + equipopron1p.getNombre()+ " y " + equipopron2p2.getNombre() + " suma un punto.");
                }
                puntosJugador3 += pronostico2z.puntos();
            }

        }
        System.out.println("");

            // FIN JUGADOR 3
            ///////////////////////////////////////////////////
            // RONDA 1 JUGADOR 4
        int nPron2 = nPron+nPron+nPron+nPron;

        if ( idpronosticos.size() != nPron+nPron+nPron+1) {

            for (int z = nPron+nPron+nPron+1; z < (nPron2+1); z++) {
                int i = 0-nPron-nPron-nPron+z;
                String[] idpz = idpronosticos.get(z).split(",");
                participante4.setNombre(idpz[0]);

                int p = z;
                int p2 = z+1;
                Equipo equipopron1p = new Equipo(idpz[1], "");
                Equipo equipopron2p2 = new Equipo(idpz[5], "");


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
                    System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: "+participante4.nombre);
                    if (unaRonda[i].getGolesEquipo1() == unaRonda[i].getGolesEquipo2()) {
                        egp2p = 30;
                    }
                }

                Partido partidopron2z = new Partido(equipopron1p, equipopron2p2, egp2p, egp2p2);

                Pronostico pronostico2z = new Pronostico(unaRonda[i], unaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2p2));

                if (pronostico2z.puntos() == 1) {
                    System.out.println(participante4.nombre + " asierto en el partido " + i + " de la primer ronda entre " + equipopron1p.getNombre()+ " y " + equipopron2p2.getNombre() + " suma un punto.");
                }
                puntosJugador4 += pronostico2z.puntos();
            }
        }
        System.out.println("");

            // fin jugador 4
            ///////////////////////////////////////////////////
            // ronda 2  jugador 1

        if ( idpronosticos.size() != (nPron2+1)) {
            if (idpartido.size() != (nRonda + 1)) {

                for (int z = (nPron2 + 1); z < (nPron2 + nPron + 1); z++) {
                    int j = (0 - nPron2 + z);
                    int i = (0 - nPron2 + nPron + z);
                    String[] idpz = idpronosticos.get(z).split(",");
                    //participante4.setNombre(idpz[0]);
                    //participante4.setNombre(idpz[0]);

                    int p = z;
                    int p2 = z + 1;
                    Equipo equipopron1p = new Equipo(idpz[1], "");
                    Equipo equipopron2p2 = new Equipo(idpz[5], "");


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

                    Partido partidopron2z = new Partido(equipopron1p, equipopron2p2, egp2p, egp2p2);
                    //System.out.println("segunda ronda: "+partidopron2z.getGolesEquipo1()+" "+i+" "+segundaRonda[i].getGolesEquipo1());

                    Pronostico pronostico2z = new Pronostico(segundaRonda[i], segundaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2p2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante1.nombre + " asierto en el partido " + j + " de la segunda ronda entre " + equipopron1p.getNombre() + " y " + equipopron2p2.getNombre() + " suma un punto.");
                    }
                    puntosJugador1 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");

        // FIN JUGADOR 1
        //////////////////////////////////////////////////////////////////
        // RONDA 2 JUGADOR 2
        if ( idpronosticos.size() != (nPron2+nPron+1)) {
            if (idpartido.size() != (nRonda + 1)) {

                for (int z = (nPron2+nPron + 1); z < (nPron2 +nPron+ nPron + 1); z++) {
                    int j = (0 - nPron2-nPron + z);
                    int i = (0 - nPron2 + z);

                    String[] idpz = idpronosticos.get(z).split(",");

                    int p = z;
                    int p2 = z + 1;
                    Equipo equipopron1p = new Equipo(idpz[1], "");
                    Equipo equipopron2p2 = new Equipo(idpz[5], "");


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
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante2.nombre + " en la segunda ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2p = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1p, equipopron2p2, egp2p, egp2p2);

                    Pronostico pronostico2z = new Pronostico(segundaRonda[i], segundaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2p2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante2.nombre + " asierto en el partido " + j + " de la segunda ronda entre " + equipopron1p.getNombre() + " y " + equipopron2p2.getNombre() + " suma un punto.");
                    }
                    puntosJugador2 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");

        // FIN JUGADOR 2
        ///////////////////////////////////////////////////////////////////
        // RONDA 2 JUGADOR 3

        if ( idpronosticos.size() != (nPron2+nPron+nPron+1)) {
            if (idpartido.size() != (nRonda + 1)) {

                for (int z = (nPron2+nPron+nPron + 1); z < (nPron2 +nPron+ nPron+nPron + 1); z++) {
                    int j = (0 - nPron2-nPron-nPron + z);
                    int i = (0 - nPron2-nPron + z);

                    String[] idpz = idpronosticos.get(z).split(",");

                    int p = z;
                    int p2 = z + 1;
                    Equipo equipopron1p = new Equipo(idpz[1], "");
                    Equipo equipopron2p2 = new Equipo(idpz[5], "");


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
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante3.nombre + " en la segunda ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2p = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1p, equipopron2p2, egp2p, egp2p2);

                    Pronostico pronostico2z = new Pronostico(segundaRonda[i], segundaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2p2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante3.nombre + " asierto en el partido " + j + " de la segunda ronda entre " + equipopron1p.getNombre() + " y " + equipopron2p2.getNombre() + " suma un punto.");
                    }
                    puntosJugador3 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");

        // FIN JUGADOR 3
        ///////////////////////////////////////////////////////////////////
        // RONDA 2 JUGADOR 4

        if ( idpronosticos.size() != (nPron2+nPron+nPron+nPron+1)) {
            if (idpartido.size() != (nRonda + 1)) {

                for (int z = (nPron2+nPron+nPron+nPron + 1); z < (nPron2 + nPron2 + 1); z++) {
                    int j = (0 - nPron2-nPron-nPron-nPron + z);
                    int i = (0 - nPron2-nPron-nPron + z);
                    //System.out.println("jugador 4 j y i: "+j+"  "+i);
                    String[] idpz = idpronosticos.get(z).split(",");

                    int p = z;
                    int p2 = z + 1;
                    Equipo equipopron1p = new Equipo(idpz[1], "");
                    Equipo equipopron2p2 = new Equipo(idpz[5], "");

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
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante4.nombre + " en la segunda ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2p = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1p, equipopron2p2, egp2p, egp2p2);

                    Pronostico pronostico2z = new Pronostico(segundaRonda[i], segundaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2p2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante4.nombre + " asierto en el partido " + j + " de la segunda ronda entre " + equipopron1p.getNombre() + " y " + equipopron2p2.getNombre() + " suma un punto.");
                    }
                    puntosJugador4 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");

        // FIN JUGADOR 4
        /////////////////////////////////////////////////////
        // JUGADORES Y PUNTOS

            System.out.println("PARTICIPANTES CON SUS PUNTOS");
            System.out.println("jugador/a: " + participante1.nombre + " total de puntos:" + puntosJugador1);
        participante1.setPuntosParticipante(puntosJugador1);

        if ( idpronosticos.size() != nPron+1) {
                System.out.println("jugador/a: " + participante2.nombre + " total de puntos:"+ puntosJugador2);
            participante2.setPuntosParticipante(puntosJugador2);
            } else {
                System.out.println("no se encuentra al jugador numero dos");
            }

        if ( idpronosticos.size() != nPron+nPron+1) {
            System.out.println("jugador/a: " + participante3.nombre + " total de puntos:"+ puntosJugador3);
            participante3.setPuntosParticipante(puntosJugador3);
        } else {
            System.out.println("no se encuentra al jugador numero tres");
        }

        if ( idpronosticos.size() != nPron+nPron+nPron+1) {
            System.out.println("jugador/a: " + participante4.nombre + " total de puntos:"+ puntosJugador4);
            participante4.setPuntosParticipante(puntosJugador4);
        } else {
            System.out.println("no se encuentra al jugador numero cuatro");
        }
        
        // ORDEN DE PUNTOS
        int[] orden = new int[4];
        orden[0] = puntosJugador1;
        orden[1] = puntosJugador2;
        orden[2] = puntosJugador3;
        orden[3] = puntosJugador4;

        Arrays.sort(orden);
        int[] b = new int[orden.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = orden[b.length-1-i];
        }

        System.out.println("");
        System.out.println("PRIMER PUESTO, SEGUNDO PUESTO Y TERCER PUESTO\n");
        if (b[0]==participante1.puntosParticipante) {
            System.out.println("primer puesto por mayor cantidad de puntos: \nnombre: "+participante1.nombre+" con "+participante1.puntosParticipante+" puntos");
        } else if (b[0]==participante2.puntosParticipante) {
            System.out.println("primer puesto por mayor cantidad de puntos: \nnombre: "+participante2.nombre+" con "+participante2.puntosParticipante+" puntos");
        }  if (b[0]==participante3.puntosParticipante) {
            System.out.println("primer puesto por mayor cantidad de puntos: \nnombre: "+participante3.nombre+" con "+participante3.puntosParticipante+" puntos");
        } if (b[0]==participante4.puntosParticipante) {
            System.out.println("primer puesto por mayor cantidad de puntos: \nnombre: "+participante4.nombre+" con "+participante4.puntosParticipante+" puntos");
        }

        System.out.println("");

        if (b[1]==participante1.puntosParticipante) {
            System.out.println("segundo puesto por cantidad de puntos: \nnombre: "+participante1.nombre+" con "+participante1.puntosParticipante+" puntos");
        } else if (b[1]==participante2.puntosParticipante) {
            System.out.println("segundo puesto por cantidad de puntos: \nnombre: "+participante2.nombre+" con "+participante2.puntosParticipante+" puntos");
        }  if (b[1]==participante3.puntosParticipante) {
            System.out.println("segundo puesto por cantidad de puntos: \nnombre: "+participante3.nombre+" con "+participante3.puntosParticipante+" puntos");
        } if (b[1]==participante4.puntosParticipante) {
            System.out.println("segundo puesto por cantidad de puntos: \nnombre: "+participante4.nombre+" con "+participante4.puntosParticipante+" puntos");
        }

        System.out.println("");

        if (b[2]==participante1.puntosParticipante) {
            System.out.println("tercer puesto por cantidad de puntos: \nnombre: "+participante1.nombre+" con "+participante1.puntosParticipante+" puntos");
        } else if (b[2]==participante2.puntosParticipante) {
            System.out.println("tercer puesto por cantidad de puntos: \nnombre: "+participante2.nombre+" con "+participante2.puntosParticipante+" puntos");
        }  if (b[2]==participante3.puntosParticipante) {
            System.out.println("tercer puesto por cantidad de puntos: \nnombre: "+participante3.nombre+" con "+participante3.puntosParticipante+" puntos");
        } if (b[2]==participante4.puntosParticipante) {
            System.out.println("tercer puesto por cantidad de puntos: \nnombre: "+participante4.nombre+" con "+participante4.puntosParticipante+" puntos");
        }

		
}
}
