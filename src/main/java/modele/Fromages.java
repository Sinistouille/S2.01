package modele;

import java.util.*;

public class Fromages {

	private List<Fromage> fromages;

	public Fromages() {
		this.fromages = new LinkedList<>();
	}

	public void addFromages(List<Fromage> fromages) {
		Set<Fromage> set = new HashSet<>(this.fromages);
		set.addAll(fromages);
		this.fromages = new LinkedList<>(set);
		Collections.sort(this.fromages);
	}

	public String toStringFromagesEtArticles() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.fromages) {
			enForme.append(f.toString()).append('\n');
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toString()).append('\n');
				}
			}
		}
		return enForme.toString();
	}

	public String toStringArticlesEtStock() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.fromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toStringAvecStock() + '\n');
				}
			}
		}
		return enForme.toString();
	}

	// A compléter !
	public List<Fromage> fromagesAuLaitDe(TypeLait lait) {
		List<Fromage> fromages = new LinkedList<>();
		for(Fromage f : this.fromages) {
			if (f.getTypeFromage() == lait) {
				fromages.add(f);
			}
		}
		return fromages;
	}

	public List<Fromage> getFromages() {
		Collections.sort(this.fromages);
		return this.fromages;
	}
	
	public Vector<Vector<String>> arrayFromages(List<TypeLait> listeLaits){
		Vector<Vector<String>> fromages = new Vector<>();
		for(Fromage c : this.fromages) {
			if(listeLaits.contains(c.getTypeFromage())) {
				Vector<String> vf = new Vector<>();
				vf.add(c.getDésignation());
				fromages.add(vf);
			}
		}
		return fromages;
	}

	public Fromage getFromage(String désignation) {
		for (Fromage f : this.fromages) {
			if (f.getDésignation().equals(désignation)) {
				return f;
			}
		}
		return null;
	}

	public void regénérationDuStock() {
		for (Fromage f : this.fromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					article.setQuantitéEnStock(
					        (int) Math.round(Math.random() * 100));
				}
			}
		}
	}

	public String vérificationSaisie() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.fromages) {
			if (f.nombreArticles() == 0) {
				enForme.append("Pas d'articles pour " + f.toString() + '\n');
			}
			if (f.getDescription() == null) {
				enForme.append(
				        "Pas de description pour " + f.toString() + '\n');
			}
			if (f.getNomImage() == null) {
				enForme.append(
						"Pas de nom d'image pour " + f.toString() + '\n');
			}
		}
		return enForme.toString();
	}

	public Article getArticle(String désignation, String clé) {
		for (Fromage f : this.fromages) {
			if (f.getDésignation().equals(désignation)) {
				for (Article article : f.getArticles()) {
					if (article.getClé().equals(clé)) {
						return article;
					}
				}
			}
		}
		return null;
	}
}
