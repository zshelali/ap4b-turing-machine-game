public class CodeSalle {

	private char campus; // B, S, M
	private char batiment; // A, B, C, D, E
	private int etage; // 0, 1, 2, 3, 4
	private int numero; // 0 à 9

	// Constructeur
	public CodeSalle(char campus, char batiment, int etage, int numero) {
		this.campus = campus;
		this.batiment = batiment;
		this.etage = etage;
		this.numero = numero;
	}

	// Getters
	public char getCampus() {
		return campus;
	}

	public char getBatiment() {
		return batiment;
	}

	public int getEtage() {
		return etage;
	}

	public int getNumero() {
		return numero;
	}

	// Méthode de comparaison
	public boolean comparer(CodeSalle c) {
		return this.campus == c.campus &&
				this.batiment == c.batiment &&
				this.etage == c.etage &&
				this.numero == c.numero;
	}

	// Méthode toString pour un affichage lisible
	@Override
	public String toString() {
		return "" + campus + batiment + etage + numero;
	}


}
