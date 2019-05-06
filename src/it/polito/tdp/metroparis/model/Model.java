package it.polito.tdp.metroparis.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {
	
	private Graph<Fermata, DefaultEdge> grafo;
	private List<Fermata> fermate;
	
	
	public void creaGrafo() {
		//Creo grafo
		this.grafo = new SimpleDirectedGraph<>(DefaultEdge.class);
		
		//Aggiungo vertici
		MetroDAO dao = new MetroDAO();
		this.fermate = dao.getAllFermate();
		Graphs.addAllVertices(this.grafo, this.fermate);
		
		//Aggiungo gli archi --> Metodo facile ma ci vuole un'ora
//		for(Fermata partenza : this.grafo.vertexSet()) {
//			for(Fermata arrivo : this.grafo.vertexSet()) {
//				if(dao.esisteConnessione(partenza, arrivo))
//					this.grafo.addEdge(partenza, arrivo);
//			}
//		}
		
		//Aggiungo gli archi --> Metodo più veloce perché pre
		
	for(Fermata partenza : this.grafo.vertexSet()) {
			List<Fermata> arrivi = dao.stazioniArrivo(partenza);
			
			for(Fermata arrivo : arrivi)
				this.grafo.addEdge(partenza, arrivo);
	}
	}


	public Graph<Fermata, DefaultEdge> getGrafo() {
		return grafo;
	}


	public List<Fermata> getFermate() {
		return fermate;
	}
}
