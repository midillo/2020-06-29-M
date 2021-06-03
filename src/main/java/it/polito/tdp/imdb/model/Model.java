package it.polito.tdp.imdb.model;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {

	private ImdbDAO dao;
	private SimpleWeightedGraph<Director, DefaultWeightedEdge> grafo;
	private Map<Integer, Director> idMap;
	
	public Model() {
		this.dao = new ImdbDAO();
		this.idMap = new HashMap<Integer, Director>();
		
	}
	
	public String creaGrafo(Year anno) {
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiungo vertici
		dao.getDirectorsByYear(anno, idMap);
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		//aggiungo archi
		
		
		if(this.grafo==null) {
			return null;
		}
		return "Grafo creato con: " +getNvertici()+ " vertici e " +getNarchi()+ " archi.";
	}
	
	public List<Director> getDirectors(Year anno){
		return this.dao.listAllDirectors(anno);
	}
	
	public int getNvertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNarchi() {
		return this.grafo.edgeSet().size();
	}
}
