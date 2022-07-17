package proyectojUnit;


import org.junit.Before;
import org.junit.Test;

public class InventarioTest {

	Inventario inventario;

	@Before
	public void setUp() throws Exception{
		
	}
	
	@Test(expected = proyectojUnit.InvalidRangeInventory.class)
	public void InventarioCapacityTest() throws Exception {
		inventario = new Inventario(100,100,100,100);	
	}
}
