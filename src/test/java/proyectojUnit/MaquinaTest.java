package proyectojUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class MaquinaTest {
	
	Maquina maquina;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception{
		maquina = new Maquina();
		System.setOut(new PrintStream(outContent));
		
	}
	
	//Verificamos que se carga el menu
	@Test
	public void CargaMenuMaquinaTest() {
		InputStream sysInBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		
		int resp = maquina.menu();
		assertTrue(outContent.toString().contains("---- Menu maquina de cafe ----"));
		assertEquals(resp,1);
		
		
	  }
	
	//Se prueba agregar inventario desde el propio metodo
	@Test
	public void AgregarInventarioTest() throws Exception {
		ByteArrayInputStream elems = new ByteArrayInputStream("1\n1\n1\n1".getBytes());
		System.setIn(elems);

		maquina.AgregarInventario();
		
		assertTrue(outContent.toString().contains("Elementos agregados al inventario"));
		
		int cafe = maquina.getInventario().getCafe();
		int choco = maquina.getInventario().getChocolate();
		int leche = maquina.getInventario().getLeche();
		int azucar = maquina.getInventario().getAzucar();
		
		assertEquals(cafe,1);
		assertEquals(choco,1);
		assertEquals(leche,1);
		assertEquals(azucar,1);
		
	}
	//Test(expected = proyectojUnit.InvalidRangeInventory.class)
	@Test
	public void AgregarInventarioSobrepasado() throws Exception {
		ByteArrayInputStream elems = new ByteArrayInputStream("100\n1\n1\n1".getBytes());
		System.setIn(elems);
		maquina.AgregarInventario();
		
		assertTrue(outContent.toString().contains("No se pudo agregar elementos a inventario. Fuera de la capacidad disponible"));
		
		int cafe = maquina.getInventario().getCafe();
		int choco = maquina.getInventario().getChocolate();
		int leche = maquina.getInventario().getLeche();
		int azucar = maquina.getInventario().getAzucar();
	
		
		assertEquals(cafe,0);
		assertEquals(choco,0);
		assertEquals(leche,0);
		assertEquals(azucar,0);
		
	}
	
	@Test(expected = proyectojUnit.InvalidRangeInventory.class)
	public void ThrowRangoInventario() throws Exception {
		//Añadiendo cosas fuera del rango del inventario desde la clase
		maquina.getInventario().addInventario(100,11,1,1);
		
	}
	
	@Test
	public void VerificarInventario() throws Exception{
		maquina.VerificarInventario();
		assertEquals("Cantidad de cafe: 0\r\n"
				+ "Cantidad de chocolate: 0\r\n"
				+ "Cantidad de leche: 0\r\n"
				+ "Cantidad de azucar: 0\r\n", outContent.toString());
	}
	
	//Este es el caso ideal donde compramos una bebida con los ingredientes disponibles e ingresando el dinero correctamente
	@Test
	public void ComprarBebida() throws Exception {
		//Aseguramos de tener inventario en maquina
		maquina.getInventario().addInventario(5, 5, 5, 5);
		//Ingresamos 3: comprar bebida -> 1: bebida cafe con leche de 500 e ingresamos 500
		ByteArrayInputStream in = new ByteArrayInputStream("1\n500".getBytes());
		System.setIn(in);
		maquina.ComprarBebida();
		
		assertTrue(outContent.toString().contains("Bebida entregada. Se devuelve vuelto si es necesario"));
	}
	
	//Este es el caso donde no podemos comprar bebida por falta de stock de inventario
	@Test
	public void ComprarBebidaFallido() throws Exception {

		ByteArrayInputStream in = new ByteArrayInputStream("1\n500".getBytes());
		System.setIn(in);
		maquina.ComprarBebida();
		
		assertTrue(outContent.toString().contains("Error: No quedan ingredientes en inventario para entregar la bebida solicitada"));
	}
	
	//Este es el caso donde no podemos comprar bebida por falta de dinero ingresado
	@Test
	public void ComprarBebidaFallido2() throws Exception {
		maquina.getInventario().addInventario(5, 5, 5, 5);
		//Se ingresan 300 que es menor a 500 (lo que vale la bebida)
		ByteArrayInputStream in = new ByteArrayInputStream("1\n300".getBytes());
		System.setIn(in);
		maquina.ComprarBebida();
		
		assertTrue(outContent.toString().contains("Error: Cantidad de dinero ingresado menor al precio de bebida. Se devuelve el dinero"));
	}
	
	//Este es el caso donde se ingresa opcion invalida al intentar comprar
	@Test
	public void ComprarBebidaFallido3() throws Exception {
		//Ingreso de opcion no valida
		ByteArrayInputStream in = new ByteArrayInputStream("hola".getBytes());
		System.setIn(in);
		maquina.ComprarBebida();
		assertTrue(outContent.toString().contains("Error: Valor invalido escrito. Debe de ser un número entero"));
	}
	
	/*
	@Test
	public void RunMaquinaAddInventario() throws Exception{
		ByteArrayInputStream in = new ByteArrayInputStream("1\n4\n4\n4\n4".getBytes());
		System.setIn(in);
		maquina.RunMaquina();
		assertTrue(outContent.toString().contains("Error: Valor invalido escrito. Debe de ser un número entero"));
	}
	*/
	
	
	
	
}
