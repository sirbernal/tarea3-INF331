package proyectojUnit;

public class Inventario {
	
	private int cafe;
	private int chocolate;
	private int leche;
	private int azucar;
	
	public Inventario(int cafe, int chocolate, int leche, int azucar) throws Exception {
		super();
		
		if (this.CheckSoportado(cafe, chocolate, leche, azucar)) {
			this.cafe = cafe;
			this.chocolate = chocolate;
			this.leche = leche;
			this.azucar = azucar;
		} else {
			throw new InvalidRangeInventory("Maximo soporte de inventario superado");
		}

		
	}
	
	public void addInventario(int cafe, int chocolate, int leche, int azucar) throws Exception {
		
		if (this.CheckSoportado(cafe, chocolate, leche, azucar)) {
			this.cafe += cafe ;
			this.chocolate += chocolate;
			this.leche += leche;
			this.azucar += azucar;
		} else {
			throw new InvalidRangeInventory("Maximo soporte de inventario superado");
		}

		
	}
	
	public boolean CheckSoportado(int cafe, int chocolate, int leche, int azucar) {
		
		if (cafe + this.cafe <= 10 && chocolate + this.chocolate <= 10 && leche + this.leche <= 80 && azucar + this.azucar <= 40) {
			return true;
		}else {
			return false;
		}
	}

	public int getCafe() {
		return cafe;
	}

	public void setCafe(int cafe) {
		this.cafe = cafe;
	}

	public int getChocolate() {
		return chocolate;
	}

	public void setChocolate(int chocolate) {
		this.chocolate = chocolate;
	}

	public int getLeche() {
		return leche;
	}

	public void setLeche(int leche) {
		this.leche = leche;
	}

	public int getAzucar() {
		return azucar;
	}

	public void setAzucar(int azucar) {
		this.azucar = azucar;
	}
	
	
	


}
