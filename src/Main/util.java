package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class util {
	HashMap<String, ArrayList<List<Double>>> leerIrisClases(String archivo) throws FileNotFoundException, IOException {
		
		  String cadena;
	      FileReader f = new FileReader(archivo);
	      BufferedReader b = new BufferedReader(f);
	      List<String> listaLinea = new ArrayList<String>();
	      HashMap<String, ArrayList<List<Double>>>  resul = new HashMap<String, ArrayList<List<Double>>>();
	      resul.put("Iris-setosa", new ArrayList<List<Double>>());
	      resul.put("Iris-versicolor", new ArrayList<List<Double>>());
	      while((cadena = b.readLine())!=null && (cadena != "")) {
	    	  String[] linea = cadena.split(",");
	    	  listaLinea = Arrays.asList(linea);
	    	  ArrayList<List<Double>> listMuestras = new ArrayList<List<Double>>();
	    	  
	    	  List<Double> vector = new ArrayList<Double>();
	    	  if("Iris-setosa".equals(listaLinea.get(4))) {
	    		  //listaLinea.remove(4);
	    		  listMuestras = resul.get("Iris-setosa");
	    		  
	    		  for(int i = 0; i < listaLinea.size()-1; i++) {
	    			  Double val = Double.parseDouble(listaLinea.get(i));
	    			  
	    			  vector.add(val);
	    		  }
	    		  listMuestras.add(vector);
	    		  
	    		  resul.put("Iris-setosa", listMuestras);
	    	  }
	    	  else if("Iris-versicolor".equals(listaLinea.get(4))) {
	    		  //listaLinea.remove(4);
	    		  listMuestras = resul.get("Iris-versicolor");
	    		  
	    		  for(int i = 0; i < listaLinea.size()-1; i++) {
	    			  Double val = Double.parseDouble(listaLinea.get(i));
	    			  
	    			  vector.add(val);
	    		  }
	    		  listMuestras.add(vector);
	    		  
	    		  resul.put("Iris-versicolor", listMuestras);
	    	  }
	      }
	      b.close();
		
		return resul;
	}
	
	Double dist(List<Double> x, List<Double> v) {
		Double resul = 0.0;
		
		for(int i = 0; i < x.size(); i++) {
			resul += Math.pow(x.get(i)-v.get(i), 2);
		}
		
		return Math.sqrt(resul);
		
	}
	
	Double P(List<Double> x, List<List<Double>> v, int numClase) {
		
		Double numerador, denominador;
		
		
		
		numerador =  1 / dist(x, v.get(numClase));
		denominador = 1 /  dist(x, v.get(numClase)) + 1/ dist(x, v.get( Math.abs(Math.abs(numClase-1) ) ) ); 
		
		return numerador/denominador;
		
	}

	List<Double> procesaSumaVector(List<List<Double>> vector){
		List<Double> resul= new ArrayList<Double>(); 
		
		resul.add(0.0);
		resul.add(0.0);
		resul.add(0.0);
		resul.add(0.0);
		
		for(int i = 0; i < vector.size(); i++) {
			for(int j = 0; j < vector.get(i).size(); j++) {
				resul.set(j, resul.get(j)+vector.get(i).get(j));
			}
		}
		
		return resul;
	}
	
	List<Double> divideVector(List<Double> vector, Double num){
		List<Double> resul = new ArrayList<Double>();
		resul.add(0.0);
		resul.add(0.0);
		resul.add(0.0);
		resul.add(0.0);
		
		for(int i = 0; i < vector.size(); i++) {
			resul.set(i, vector.get(i)/num);
		}
		
		return resul;
	}
	
	
	List<List<Double>> actualizaCentros(List<List<Double>> centros, HashMap<String, ArrayList<List<Double>>> muestras) {
		List<List<Double>> centrosAntiguos = new ArrayList<List<Double>>(centros);
		
		
		
		Double denominador = 0.0;
		List<List<Double>> numerador = new ArrayList<List<Double>>();	
		
		//setosa
		List<List<Double>> listMuestrasSetosa = new ArrayList<List<Double>>();		
		listMuestrasSetosa = muestras.get("Iris-setosa");
		for (int i = 0; i < listMuestrasSetosa.size(); i++) {
			Double perten = Math.pow(P(listMuestrasSetosa.get(i), centros, 0), 2);
			denominador += perten ; 
			List<Double> muestraPorP = new ArrayList<Double>();
			muestraPorP.add(perten*listMuestrasSetosa.get(i).get(0));
			muestraPorP.add(perten*listMuestrasSetosa.get(i).get(1));
			muestraPorP.add(perten*listMuestrasSetosa.get(i).get(2));
			muestraPorP.add(perten*listMuestrasSetosa.get(i).get(3));
			numerador.add(muestraPorP);
		}
		List<Double> numeradorSumado = procesaSumaVector(numerador);
		
		List<Double> resulSetosa = divideVector(numeradorSumado, denominador);
		
		//versicolor
		numerador = new ArrayList<List<Double>>();
		denominador = 0.0;
		List<List<Double>> listMuestrasVersicolor = new ArrayList<List<Double>>();		
		listMuestrasVersicolor = muestras.get("Iris-versicolor");
		
		for (int i = 0; i < listMuestrasVersicolor.size(); i++) {
			Double perten = Math.pow(P(listMuestrasSetosa.get(i), centros, 1) , 2);
			denominador += perten ; 
			List<Double> muestraPorP = new ArrayList<Double>();
			muestraPorP.add(perten*listMuestrasVersicolor.get(i).get(0));
			muestraPorP.add(perten*listMuestrasVersicolor.get(i).get(1));
			muestraPorP.add(perten*listMuestrasVersicolor.get(i).get(2));
			muestraPorP.add(perten*listMuestrasVersicolor.get(i).get(3));
			numerador.add(muestraPorP);
		}
		List<Double> numeradorSumadoV = procesaSumaVector(numerador);
		
		List<Double> resulVersicolor = divideVector(numeradorSumadoV, denominador);
		
		centros.set(0, resulSetosa);
		centros.set(1, resulVersicolor);

		//comparar
		if(dist(centros.get(0), centrosAntiguos.get(0)) < 0.01 && dist(centros.get(1), centrosAntiguos.get(1)) < 0.01) {
			return centros;
		}
		else {
			return actualizaCentros(centros, muestras);
		}
		
		
	}
} 
