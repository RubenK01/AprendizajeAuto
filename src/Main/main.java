package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class main {

	public static void main(String[] args) {
		util u = new util();
		List<List<Double>> centrosIni = new ArrayList<List<Double>>(); 
		
		//inicializacion de centros
		List<Double> listAux= new ArrayList<Double>();
		listAux.add(4.6);
		listAux.add(3.0);
		listAux.add(4.0);
		listAux.add(0.0);
		centrosIni.add(listAux);
		
		listAux= new ArrayList<Double>();
		listAux.add(6.8);
		listAux.add(3.4);
		listAux.add(4.6);
		listAux.add(0.7);
		centrosIni.add(listAux);
		
		try {
			HashMap<String, ArrayList<List<Double>>> muestras = u.leerIrisClases("Archivos/Iris2Clases.txt");
			
			
			List<List<Double>> centrosAct = u.actualizaCentros(centrosIni,  muestras);
			
			System.out.println(centrosAct);
			
			listAux= new ArrayList<Double>();
			listAux.add(5.1);
			listAux.add(3.5);
			listAux.add(1.4);
			listAux.add(0.2);
			if(u.dist(centrosAct.get(0), listAux) < u.dist(centrosAct.get(1), listAux)) {
				System.out.println("setosa");
			}
			else {
				System.out.println("versicolor");
			}
			
			listAux= new ArrayList<Double>();
			listAux.add(6.9);
			listAux.add(3.1);
			listAux.add(4.9);
			listAux.add(1.5);
			if(u.dist(centrosAct.get(0), listAux) < u.dist(centrosAct.get(1), listAux)) {
				System.out.println("setosa");
			}
			else {
				System.out.println("versicolor");
			}
			
			listAux= new ArrayList<Double>();
			listAux.add(5.0);
			listAux.add(3.4);
			listAux.add(1.5);
			listAux.add(0.2);
			if(u.dist(centrosAct.get(0), listAux) < u.dist(centrosAct.get(1), listAux)) {
				System.out.println("setosa");
			}
			else {
				System.out.println("versicolor");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
