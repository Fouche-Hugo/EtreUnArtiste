package fr.eseo.pdlo.projet.artiste.modele;

public enum Remplissage {
	AUCUNE("aucune"), UNIFORME("uniforme"), DEGRADE("degradé"), PARTIEL("partiel");
	
	private String mode;
	private Remplissage(String mode) {
		this.mode = mode;
	}
	
	public String getMode() {
		return this.mode;
	}

	public static Remplissage fromString(String text) {
		for(Remplissage r : Remplissage.values()) {
			if(r.getMode().equalsIgnoreCase(text)) {
				return r;
			}
		}
		throw new IllegalArgumentException("Pas de remplissage disponibles depuis le text" + text);
	}
}
