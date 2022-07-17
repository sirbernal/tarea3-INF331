package proyectojUnit;

public class Receta {
	
	private String nombre;
	private int precio;
	private int cafe;
	private int chocolate;
	private int leche;
	private int azucar;
	
	
	
	public Receta(String nombre, int precio, int cafe, int chocolate, int leche, int azucar) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cafe = cafe;
		this.chocolate = chocolate;
		this.leche = leche;
		this.azucar = azucar;
	}



	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public int getCafe() {
		return cafe;
	}

	public int getChocolate() {
		return chocolate;
	}


	public int getLeche() {
		return leche;
	}

	public int getAzucar() {
		return azucar;
	}


	
	

}
