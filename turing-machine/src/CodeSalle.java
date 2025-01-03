public class CodeSalle {

	private char campus; // B, S, M
	private char batiment; // A, B, C, D, E
	private int etage; // 0, 1, 2, 3, 4
	private int numero; // 0 à 9

	// Constructeur
	public CodeSalle(char campus, char batiment, int etage, int numero) {
		if (campus != 'B' && campus != 'S' && campus != 'M') {
            throw new IllegalArgumentException("Invalid campus. Must be 'B', 'S', or 'M'.");
        }
        if (batiment < 'A' || batiment > 'E') {
            throw new IllegalArgumentException("Invalid batiment. Must be between 'A' and 'E'.");
        }
        if (etage < 0 || etage > 4) {
            throw new IllegalArgumentException("Invalid etage. Must be between 0 and 4.");
        }
        if (numero < 0 || numero > 9) {
            throw new IllegalArgumentException("Invalid numero. Must be between 0 and 9.");
        }
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
