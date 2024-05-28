package modele;

import java.util.Map;
import java.util.TreeMap;

public class Panier {
	
	private Map<Fromage,Integer> paniers;
	
	public Panier() {
		paniers = new TreeMap<>();
	}
	
	public void ajouterFromage(Fromage f, int q) {
		if(paniers.get(f) - q <= 0) {
			paniers.remove(f);
		}
		else {
			paniers.put(f,q);
		}
	}
	
	public int retirerFromage(Fromage f) {
		return paniers.remove(f);
	}
	
	public Map<Fromage,Integer> getPanier() {
		return this.paniers;
	}
	
	
}