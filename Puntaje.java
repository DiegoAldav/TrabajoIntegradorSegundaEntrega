package Programa;

import Domain.*;

import Enum.Resultado;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

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

        ///////////////////////////////////////////////////
        // COMPROBAR ERRORES EN EL ARCHIVO RESULTADOS.CSV EN El ID DE LA RONDA

            try {
                for (int i = 1; i < idpartidos.size(); i++) {
                    String[] errores = idpartidos.get(i).split(",");

                    int a = Integer.parseInt(String.valueOf(errores[0]));

                }

            } catch (Exception ex) {
                System.out.println("Excepcion "+ex.getMessage());
                System.out.println("En el archivo resultados.csv en el lugar donde va el id de la ronda definida por un numero se encuentra una letra o un numero decimal");
                // ACA ESTOY EN LA DUDA DE UNA PARADA ABRUPTA O SACAR EL SYSTEM.0 Y QUE SIGA
                Scanner entrada = new Scanner(System.in);
                System.out.println(" \ningrese 1 para seguir adelante igual, esto puede traer algun otro problema o resultados distintos,\n o ingrese un numero distinto de uno para parar el programa y corregir el error\n NO INGRESE UNA LETRA O UN DECIMAL POR FAVOR NO QUEREMOS SEGUIR CORRIGIENDO ERRORES");
                int a = entrada.nextInt();
                if (a == 1) {
                    System.out.println("el programa continuara");

                } else {System.exit(0);}
            } finally {
                //System.out.println("c: "+c);
            }


        ///////////////////////////////////////////////////
        // COMPROBAR ERRORES EN EL ARCHIVO RESULTADOS.CSV EN LOS GOLES EQUIPO 1 Y 2

        try {
            for (int i = 1; i < idpartidos.size(); i++) {
                String[] errores = idpartidos.get(i).split(",");

                int a = Integer.parseInt(String.valueOf(errores[2]));
                int b = Integer.parseInt(String.valueOf(errores[3]));
                //errores[2] = String.valueOf(0);

            }

        } catch (Exception ex) {
            System.out.println("Excepcion "+ex.getMessage());
            System.out.println("En el archivo resultados.csv en el lugar donde van los goles del equipo uno y dos se encuentra una letra o un numero decimal");
            System.out.println(" \ningrese enter para finalizar el programa ");
            Scanner entrada = new Scanner(System.in);
            String a = entrada.nextLine();
            if (a == "1") {
                System.out.println("el programa continuara");
                System.exit(0);
            } else {System.out.println("corrija el error");
            System.exit(0);
            }
        } finally {
            //System.out.println("c: "+c);
        }

        ///////////////////////////////////////////////////
        //  LARGO DE LA PRIMER RONDA

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
        // LARGO DE LA SEGUNDA RONDA

        int segRonda = 0;
        for (int x = 1; x < idpartidos.size(); x++) {
            String[] idpart = idpartidos.get(x).split(",");
            if (idpart[0].startsWith("2")) {
                segRonda += 1;
            }
        }
        //System.out.println("la segunda ronda tiene "+segRonda+" partidos");

        /////////////////////////////////////////////////////////////
        // SEGUNDA RONDA

            Partido[] segundaRonda = new Partido[nRonda+segRonda+1];
        if ( idpartido.size() != (nRonda+1)) {

            for (int i = (nRonda + 1); i < (nRonda+segRonda+1); i++) {
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

        /////////////////////////////////////////////////////
        //  LARGO TERCERA RONDA

        int tercerRonda = 0;
        for (int x = 1; x < idpartidos.size(); x++) {
            String[] idpart = idpartidos.get(x).split(",");
            if (idpart[0].startsWith("3")) {
                tercerRonda += 1;
            }
        }
        //System.out.println("tercerRonda: "+tercerRonda);

        ////////////////////////////////////////////////////
        //  TERCERA RONDA

        //System.out.println("idpartido.size: "+idpartido.size()+"  nRonda+segRonda+1: "+(nRonda+segRonda+1));
        Partido[] terceraRonda = new Partido[nRonda+segRonda+tercerRonda+1];
        if ( idpartido.size() != (nRonda+segRonda+1)) {

            for (int i = (nRonda+segRonda + 1); i < (nRonda+segRonda+tercerRonda+1); i++) {
                String[] partidos = idpartido.get(i).split(",");

                Equipo equipo1 = new Equipo();
                equipo1.setNombre(partidos[1]);
                Equipo equipo2 = new Equipo();
                equipo2.setNombre(partidos[4]);

                int golesEquipo1 = Integer.parseInt(partidos[2]);
                int golesEquipo2 = Integer.parseInt(partidos[3]);

                Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);

                terceraRonda[i] = partido;

            }
        }

        //////////////////////////////////////////////////////
        //   PRONOSTICOS CALCULO DE CANTIDAD DE PARTIDOS PRIMER RONDA

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
        //System.out.println("rondas part: "+nRonda+" pronosticos part: "+nPron);

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

            // FIN JUGADOR 1
            /////////////////////////////////////
            // RONDA 1 JUGADOR 2

            if ( idpronosticos.size() != nPron+1) {

                for (int z = nPron+1; z < (nPron+nPron+1)/*idpronosticos.size()*/; z++) {
                    int i = z-nPron;
                    String[] idpz = idpronosticos.get(z).split(",");
                    participante2.setNombre(idpz[0]);

                    //int p = z;
                    //int p2 = z+1;
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
                int i = z-nPron-nPron;
                String[] idpz = idpronosticos.get(z).split(",");
                participante3.setNombre(idpz[0]);

                //int p = z;
                //int p2 = z+1;
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
                int i = z-nPron-nPron-nPron;
                String[] idpz = idpronosticos.get(z).split(",");
                participante4.setNombre(idpz[0]);
                //System.out.println("jugador 4 ronda 1 j: "+i+" i: "+i);
                //int p = z;
                //int p2 = z+1;
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
        // CALCULO LARGO RONDA 2

        int segPron = 0;
        for (int x = nPron2; x < (nPron2+segRonda+1); x++) {
            String[] idpp2 = idpronosticos.get(x).split(",");
            if (idpp2[0].startsWith(p1)/*.equals(p1)*/) {
                segPron += 1;
            }
        }

        //segPron = 49;
        if ( segRonda != segPron){
            System.out.println("la cantidad de partidos de la segunda ronda es distinta a la cantidad de partidos de pronosticos");
            System.out.println("\nParticipantes con puntos parciales(NO TOTALES)");
            System.out.println("jugador/a: " + participante1.nombre + " total de puntos:" + puntosJugador1);
            System.out.println("jugador/a: " + participante2.nombre + " total de puntos:" + puntosJugador2);
            System.out.println("jugador/a: " + participante3.nombre + " total de puntos:" + puntosJugador3);
            System.out.println("jugador/a: " + participante4.nombre + " total de puntos:" + puntosJugador4);
            System.exit(0);
        }
        //System.out.println("ronda2 part: "+segRonda+" pronosticos2 part: "+segPron);


        //////////////////////////////////////////////////
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

        // FIN JUGADOR 1
        //////////////////////////////////////////////////////////////////
        // RONDA 2 JUGADOR 2

        if ( idpronosticos.size() != (nPron2+segPron+1)) {
            if (idpartido.size() != (nRonda + 1)) {

                for (int z = (nPron2+segPron + 1); z < (nPron2 +segPron+ segPron + 1); z++) {
                    int j = (z-nPron2-segPron);
                    int i = (nPron+j);
                    //System.out.println("jugador 2 ronda 2 j: "+j+" i: "+i);
                    //System.out.println("nPron2: "+nPron2+" nPron: "+nPron+" segPron: "+segPron+" segRonda: "+segRonda);

                    String[] idpz = idpronosticos.get(z).split(",");

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
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante2.nombre + " en la segunda ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2p = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1, equipopron2, egp2p, egp2p2);

                    Pronostico pronostico2z = new Pronostico(segundaRonda[i], segundaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante2.nombre + " asierto en el partido " + j + " de la segunda ronda entre " + equipopron1.getNombre() + " y " + equipopron2.getNombre() + " suma un punto.");
                    }
                    puntosJugador2 += pronostico2z.puntos();
                    i += 1;
                    j += 1;
                }
            }
        }
        System.out.println("");

        // FIN JUGADOR 2
        ///////////////////////////////////////////////////////////////////
        // RONDA 2 JUGADOR 3

        if ( idpronosticos.size() != (nPron2+segPron+segPron+1)) {
            if (idpartido.size() != (nRonda + 1)) {

                for (int z = (nPron2+segPron+segPron + 1); z < (nPron2 +segPron+ segPron+segPron + 1); z++) {
                    int j = (z-nPron2-segPron-segPron);
                    int i = (nPron+j);
                    //System.out.println("jugador 3 ronda 2 j: "+j+" i: "+i);
                    String[] idpz = idpronosticos.get(z).split(",");

                    //int p = z;
                    //int p2 = z + 1;
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
        int segpron2 = segPron+segPron+segPron+segPron;

        if ( idpronosticos.size() != (nPron2+segPron+segPron+segPron+1)) {
            if (idpartido.size() != (nRonda + 1)) {

                for (int z = (nPron2+segPron+segPron+segPron + 1); z < (nPron2 + segpron2 + 1); z++) {
                    int j = (z-nPron2-segPron-segPron-segPron);
                    int i = (nPron+j);
                    //System.out.println("jugador 4 ronda 2 j: "+j+" i: "+i);
                    String[] idpz = idpronosticos.get(z).split(",");

                    //int p = z;
                    //int p2 = z + 1;
                    Equipo equipopron1 = new Equipo(idpz[1], "");
                    Equipo equipopron2 = new Equipo(idpz[5], "");

                    int egp1 = 0;
                    int egp2 = 0;
                    if (idpz[2] != "") {
                        egp1 = 1;
                        egp2 = 0;
                    } else if (idpz[4] != "") {
                        egp1 = 0;
                        egp2 = 1;
                    } else if (idpz[3] != "") {
                        egp1 = 0;
                        egp2 = 0;
                    } else {
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante4.nombre + " en la segunda ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2 = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1, equipopron2, egp1, egp2);

                    Pronostico pronostico2z = new Pronostico(segundaRonda[i], segundaRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante4.nombre + " asierto en el partido " + j + " de la segunda ronda entre " + equipopron1.getNombre() + " y " + equipopron2.getNombre() + " suma un punto.");
                    }
                    puntosJugador4 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");

        // FIN JUGADOR 4
        /////////////////////////////////////////////////////
        // CALCULO LARGO RONDA 3

        //System.out.println("tercerRonda: "+tercerRonda);
        //System.out.println("idpron: "+idpronosticos.size()+" npron2+segpron2+1: "+(nPron2+segpron2+1));
        int tercerPron = 0;
        if (idpronosticos.size() != (nPron2+segpron2+1)) {

            for (int x = (nPron2 + segpron2); x < (nPron2 + segpron2 + tercerRonda + 1); x++) {
                String[] idpp2 = idpronosticos.get(x).split(",");
                if (idpp2[0].startsWith(p1)/*.equals(p1)*/) {
                    tercerPron += 1;
                }
            }
        }

        //tercerPron = 49;
        //System.out.println("tercerRonda: "+tercerRonda+" tercerPron: "+tercerPron);
        if ( tercerRonda != tercerPron){
            System.out.println("la cantidad de partidos de la tercer ronda es distinta a la cantidad de partidos de pronosticos\ntambien puede ser que falten los participantes de la tercer ronda");
            System.out.println("\nParticipantes con puntos parciales(NO TOTALES)");
            System.out.println("jugador/a: " + participante1.nombre + " total de puntos:" + puntosJugador1);
            System.out.println("jugador/a: " + participante2.nombre + " total de puntos:" + puntosJugador2);
            System.out.println("jugador/a: " + participante3.nombre + " total de puntos:" + puntosJugador3);
            System.out.println("jugador/a: " + participante4.nombre + " total de puntos:" + puntosJugador4);
            System.exit(0);
        }
        //System.out.println("ronda2 part: "+segRonda+" pronosticos2 part: "+segPron);

        ////////////////////////////////////////////////////
        //  RONDA 3 JUGADOR 1

        if ( idpronosticos.size() != (nPron2+segpron2+1)) {
            if (idpartido.size() != (nRonda + segPron + 1)) {

                for (int z = (nPron2+segpron2 + 1); z < (nPron2 + segpron2+tercerPron + 1); z++) {
                    int j = (z-nPron2-segpron2);
                    int i = (nPron+segPron+j);
                    //System.out.println("npron2: "+nPron2+"  z: "+z);
                    //System.out.println("jugador 1 ronda 3 j: "+j+" i: "+i+" tercerPron: "+tercerPron);
                    String[] idpz = idpronosticos.get(z).split(",");

                    //int p = z;
                    //int p2 = z + 1;
                    Equipo equipopron1 = new Equipo(idpz[1], "");
                    Equipo equipopron2 = new Equipo(idpz[5], "");

                    int egp1 = 0;
                    int egp2 = 0;
                    if (idpz[2] != "") {
                        egp1 = 1;
                        egp2 = 0;
                    } else if (idpz[4] != "") {
                        egp1 = 0;
                        egp2 = 1;
                    } else if (idpz[3] != "") {
                        egp1 = 0;
                        egp2 = 0;
                    } else {
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante1.nombre + " en la tercer ronda");
                        if (terceraRonda[i].getGolesEquipo1() == terceraRonda[i].getGolesEquipo2()) {
                            egp2 = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1, equipopron2, egp1, egp2);

                    Pronostico pronostico2z = new Pronostico(terceraRonda[i], terceraRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante1.nombre + " asierto en el partido " + j + " de la tercer ronda entre " + equipopron1.getNombre() + " y " + equipopron2.getNombre() + " suma un punto.");
                    }
                    puntosJugador1 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");

        /////////////////////////////////////////////////////
        //   RONDA 3 JUGADOR 2

        if ( idpronosticos.size() != (nPron2+segpron2+tercerPron+1)) {
            if (idpartido.size() != (nRonda + segPron + 1)) {

                for (int z = (nPron2+segpron2+tercerPron + 1); z < (nPron2 + segpron2+tercerPron+tercerPron + 1); z++) {
                    int j = (z-nPron2-segpron2-tercerPron);
                    int i = (nPron+segPron+j);
                    //System.out.println("npron2: "+nPron2+"  z: "+z);
                    //System.out.println("jugador 2 ronda 3 j: "+j+" i: "+i);
                    String[] idpz = idpronosticos.get(z).split(",");

                    //int p = z;
                    //int p2 = z + 1;
                    Equipo equipopron1 = new Equipo(idpz[1], "");
                    Equipo equipopron2 = new Equipo(idpz[5], "");

                    int egp1 = 0;
                    int egp2 = 0;
                    if (idpz[2] != "") {
                        egp1 = 1;
                        egp2 = 0;
                    } else if (idpz[4] != "") {
                        egp1 = 0;
                        egp2 = 1;
                    } else if (idpz[3] != "") {
                        egp1 = 0;
                        egp2 = 0;
                    } else {
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante1.nombre + " en la tercer ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2 = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1, equipopron2, egp1, egp2);

                    Pronostico pronostico2z = new Pronostico(terceraRonda[i], terceraRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante2.nombre + " asierto en el partido " + j + " de la tercer ronda entre " + equipopron1.getNombre() + " y " + equipopron2.getNombre() + " suma un punto.");
                    }
                    puntosJugador2 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");
        /////////////////////////////////////////////////////
        //  RONDA 3 JUGADOR 3

        if ( idpronosticos.size() != (nPron2+segpron2+tercerPron+tercerPron+1)) {
            if (idpartido.size() != (nRonda + segPron + 1)) {

                for (int z = (nPron2+segpron2+tercerPron+tercerPron + 1); z < (nPron2 + segpron2+tercerPron+tercerPron+tercerPron + 1); z++) {
                    int j = (z-nPron2-segpron2-tercerPron-tercerPron);
                    int i = (nPron+segPron+j);
                    //System.out.println("npron2: "+nPron2+"  z: "+z);
                    //System.out.println("jugador 3 ronda 3 j: "+j+" i: "+i);
                    String[] idpz = idpronosticos.get(z).split(",");

                    //int p = z;
                    //int p2 = z + 1;
                    Equipo equipopron1 = new Equipo(idpz[1], "");
                    Equipo equipopron2 = new Equipo(idpz[5], "");

                    int egp1 = 0;
                    int egp2 = 0;
                    if (idpz[2] != "") {
                        egp1 = 1;
                        egp2 = 0;
                    } else if (idpz[4] != "") {
                        egp1 = 0;
                        egp2 = 1;
                    } else if (idpz[3] != "") {
                        egp1 = 0;
                        egp2 = 0;
                    } else {
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante1.nombre + " en la tercer ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2 = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1, equipopron2, egp1, egp2);

                    Pronostico pronostico2z = new Pronostico(terceraRonda[i], terceraRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante3.nombre + " asierto en el partido " + j + " de la tercer ronda entre " + equipopron1.getNombre() + " y " + equipopron2.getNombre() + " suma un punto.");
                    }
                    puntosJugador3 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");
        /////////////////////////////////////////////////////
        //  RONDA 3 JUGADOR 4
        int tercerPron2 = tercerPron+tercerPron+tercerPron+tercerPron;

        if ( idpronosticos.size() != (nPron2+segpron2+tercerPron+tercerPron+tercerPron+1)) {
            if (idpartido.size() != (nRonda + segPron + 1)) {

                for (int z = (nPron2+segpron2+tercerPron+tercerPron+tercerPron + 1); z < (nPron2 + segpron2+tercerPron2 + 1); z++) {
                    int j = (z-nPron2-segpron2-tercerPron-tercerPron-tercerPron);
                    int i = (nPron+segPron+j);
                    //System.out.println("npron2: "+nPron2+"  z: "+z);
                    //System.out.println("jugador 4 ronda 3 j: "+j+" i: "+i);
                    String[] idpz = idpronosticos.get(z).split(",");

                    //int p = z;
                    //int p2 = z + 1;
                    Equipo equipopron1 = new Equipo(idpz[1], "");
                    Equipo equipopron2 = new Equipo(idpz[5], "");

                    int egp1 = 0;
                    int egp2 = 0;
                    if (idpz[2] != "") {
                        egp1 = 1;
                        egp2 = 0;
                    } else if (idpz[4] != "") {
                        egp1 = 0;
                        egp2 = 1;
                    } else if (idpz[3] != "") {
                        egp1 = 0;
                        egp2 = 0;
                    } else {
                        System.out.println("falta rellenar la casilla de ganador/empate/perdedor de ese partido en pronostico.csv del jugador/a: " + participante1.nombre + " en la tercer ronda");
                        if (segundaRonda[i].getGolesEquipo1() == segundaRonda[i].getGolesEquipo2()) {
                            egp2 = 30;
                        }
                    }

                    Partido partidopron2z = new Partido(equipopron1, equipopron2, egp1, egp2);

                    Pronostico pronostico2z = new Pronostico(terceraRonda[i], terceraRonda[i].getEquipo2(), partidopron2z.resultado(equipopron2));

                    if (pronostico2z.puntos() == 1) {
                        System.out.println(participante4.nombre + " asierto en el partido " + j + " de la tercer ronda entre " + equipopron1.getNombre() + " y " + equipopron2.getNombre() + " suma un punto.");
                    }
                    puntosJugador4 += pronostico2z.puntos();
                }
            }
        }
        System.out.println("");

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