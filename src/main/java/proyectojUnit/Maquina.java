package proyectojUnit;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maquina {
	
	private Inventario inventario;
	
	ArrayList<Receta> bebidas;
	
	int dinero_en_maquina;
	
	
	/* ASUMIREMOS QUE LAS BEBIDAS LAS DEFINIREMOS NOSOTROS POR LO QUE DEJARE EL CONSTRUCTOR DE ABAJO SOLAMENTE PARA TEMAS DE COVERAGE
	public Maquina(Inventario inventario, ArrayList<Receta> bebidas) {
		super();
		this.inventario = inventario;
		this.bebidas = bebidas;
		this.dinero_en_maquina = 0;
	}
	*/
	public Maquina() throws Exception {
		super();
		
		ArrayList<Receta> bebidas = new ArrayList<Receta>();
		
		bebidas.add(new Receta("Cafe con leche", 500, 2, 0, 4 , 2));
		bebidas.add(new Receta("Cafe Capuccino", 700, 4, 0, 0 , 3));
		bebidas.add(new Receta("Cafe Mocca", 800, 2, 4, 0 , 2));
		
		this.inventario = new Inventario(0,0,0,0);
		this.bebidas = bebidas;
		this.dinero_en_maquina = 0;
		
	}

	private static final Logger logger = LogManager.getLogger(Maquina.class);
	
	public int menu() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---- Menu maquina de cafe ----");
		System.out.println("Escriba el numero de la opción a elegir:");
		System.out.println("1) Agregar inventario");
		System.out.println("2) Verificar inventario");
		System.out.println("3) Comprar bebida");
		
		
		String opcion = sc.nextLine();
		return Integer.parseInt(opcion);
	}
	
	public void AgregarInventario() throws Exception {
		
		
		int cafe,chocolate,leche,azucar;
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			try {
				System.out.println("Escriba la cantidad de cafe a agregar");
				String opcion = sc.nextLine();
				int valor = Integer.parseInt(opcion);
				if (valor < 0) {
					throw new InvalidRangeInventory("");
				}
				cafe = valor;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Error: Valor invalido escrito. Debe de ser un número entero");
				
			} catch(InvalidRangeInventory e) {
				System.out.println("Error: El valor ingresado es menor a 0");
				
			} 				
		}
		
		while(true) {
			try {
				System.out.println("Escriba la cantidad de chocolate a agregar");
				String opcion = sc.nextLine();
				int valor = Integer.parseInt(opcion);
				if (valor < 0) {
					throw new InvalidRangeInventory("");
				}
				chocolate = valor;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Error: Valor invalido escrito. Debe de ser un número entero");
				
			} catch(InvalidRangeInventory e) {
				System.out.println("Error: El valor ingresado es menor a 0");
				
			} 				
		}
		
		while(true) {
			try {
				System.out.println("Escriba la cantidad de leche a agregar");
				String opcion = sc.nextLine();
				int valor = Integer.parseInt(opcion);
				if (valor < 0) {
					throw new InvalidRangeInventory("");
				}
				leche = valor;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Error: Valor invalido escrito. Debe de ser un número entero");
				
			} catch(InvalidRangeInventory e) {
				System.out.println("Error: El valor ingresado es menor a 0");
				
			} 				
		}
		
		while(true) {
			try {
				System.out.println("Escriba la cantidad de azucar a agregar");
				String opcion = sc.nextLine();
				int valor = Integer.parseInt(opcion);
				if (valor < 0) {
					throw new InvalidRangeInventory("");
				}
				azucar = valor;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Error: Valor invalido escrito. Debe de ser un número entero");
				
			} catch(InvalidRangeInventory e) {
				System.out.println("Error: El valor ingresado es menor a 0");
				
			} 				
		}
		
		try {
			this.inventario.addInventario(cafe,chocolate,leche,azucar);
			System.out.println("Elementos agregados al inventario");
			logger.info("Elementos agregados al inventario: cafe: "+cafe+" chocolate: "+chocolate+" leche: "+leche+" azucar: "+azucar);
		}catch(Exception e) {
			System.out.println("No se pudo agregar elementos a inventario. Fuera de la capacidad disponible");
			logger.error("No se pudo agregar elementos a inventario. Fuera de la capacidad disponible ");
		}

	}
	
	
	public  void VerificarInventario() {
		
		System.out.println("Cantidad de cafe: " + this.inventario.getCafe());
		System.out.println("Cantidad de chocolate: " + this.inventario.getChocolate());
		System.out.println("Cantidad de leche: " + this.inventario.getLeche());
		System.out.println("Cantidad de azucar: " + this.inventario.getAzucar());
		
		
	}
	
	public void ComprarBebida(){
		try {
			System.out.println("Bebidas disponibles para comprar, escribir numero de bebida;");
			Scanner sc = new Scanner(System.in);
			
			//MOSTRAR BEBIDAS DISPONIBLES
			int c = 1;
			for(Receta b: this.bebidas) {
				System.out.println( c + ") " + b.getNombre() + " Precio: " + b.getPrecio());
				c++;
			}
			//OBTENER EL NUMERO DE LA BEBIDA A COMPRAR
			int opt_bebida = Integer.parseInt(sc.nextLine());
			
			//SIEMPRE Y CUANDO EL VALOR SEA VALIDO 
			if (opt_bebida >= 0) {
				//OBTENEMOS EL OBJETO DE LA BEBIDA
				Receta bebida = this.bebidas.get(opt_bebida - 1);
				try {
					//SOLICITAMOS EL DINERO A INGRESAR A LA MAQUINA
					System.out.println("Ingrese el dinero: ");
					int dinero = Integer.parseInt(sc.nextLine());
					logger.info("Se elige la bebida: "+bebida.getNombre() + " , se ingresa la cantidad de dinero: "+ dinero);
					//SI EL DINERO INGRESADO ES MAYOR O IGUAL AL COSTE DE LA BEBIDA
					if (dinero >= bebida.getPrecio()) {
						//OBTENEMOS LAS CANTIDADES DE INGREDIENTES QUE NECESITAMOS PARA CREAR LA BEBIDA
						int res_azucar = this.inventario.getAzucar() - bebida.getAzucar();
						int res_cafe = this.inventario.getCafe() - bebida.getCafe();
						int res_chocolate = this.inventario.getChocolate() - bebida.getChocolate();
						int res_leche = this.inventario.getLeche() - bebida.getLeche();
						
						//SI HAY SUFICIENTES INGREDIENTES PARA CREAR LA BEBIDA, RESTAMOS LO QUE USAMOS AL INVENTARIO Y CREAMOS LA BEBIDA
						if (res_azucar >= 0 && res_cafe >= 0 && res_chocolate >= 0 && res_leche >= 0) {
							this.inventario.setAzucar(res_azucar);
							this.inventario.setCafe(res_cafe);
							this.inventario.setChocolate(res_chocolate);
							this.inventario.setLeche(res_leche);
							
							this.dinero_en_maquina += dinero;
							
							System.out.println("Bebida entregada. Se devuelve vuelto si es necesario");
							logger.info("Se entrega la bebida solicitada");
						}
						//EN CASO CONTRARIO ARROJAMOS ERROR DE DISPONIBILIDAD DE INVENTARIO
						else {
							throw new InvalidRangeInventory("");
						}
					//EN CASO CONTRARIO ARROJAMOS ERROR DE CANTIDAD DE DINERO INGRESADA ES MENOR AL PRECIO	
					}else {
						throw new LessAmountOfMoney("");
					}
				//OBTENEMOS EL ERROR DE CANTIDAD DE DINERO INGRESADA ES MENOR AL PRECIO		
				} catch(LessAmountOfMoney e) {
					System.out.println("Error: Cantidad de dinero ingresado menor al precio de bebida. Se devuelve el dinero");				        
			        logger.error("Cantidad de dinero ingresado menor al precio de bebida. Se devuelve el dinero");
				//OBTENEMOS EL ERROR DE DISPONIBILIDAD DE INVENTARIO
				} catch (InvalidRangeInventory e) {
					System.out.println("Error: No quedan ingredientes en inventario para entregar la bebida solicitada");
					logger.error("No quedan ingredientes en inventario para entregar la bebida solicitada");
				}

			}
		//OBTENEMOS EL ERROR AL INGRESAR UN VALOR INVALIDO O NO ENTERO	
		} catch (NumberFormatException e) {
			System.out.println("Error: Valor invalido escrito. Debe de ser un número entero");
		}
		
	}
	
	public void RunMaquina() {
			boolean maquina = true;
			while(maquina) {
				try {
					int opt = this.menu();
					
					switch(opt) {
					case 1:
						logger.info("Opción elegida: Agregar inventario ");
						AgregarInventario();
						/*
						try {
							int cafe = elems.get(0);
							int chocolate = elems.get(1);
							int leche = elems.get(2);
							int azucar = elems.get(3);
							this.inventario.addInventario(cafe,chocolate,leche,azucar);
							System.out.println("Elementos agregados al inventario");
							logger.info("Elementos agregados al inventario: cafe: "+cafe+" chocolate: "+chocolate+" leche: "+leche+" azucar: "+azucar);
						} catch(Exception e) {
							System.out.println("No se pudo agregar elementos a inventario. Fuera de la capacidad disponible");
							logger.error("No se pudo agregar elementos a inventario. Fuera de la capacidad disponible ");
						} */
						continue;
						
					case 2:
						this.VerificarInventario();
						logger.info("Opción elegida: Verificar inventario");
						continue;
						
					case 3:
						logger.info("Opción elegida: Comprar bebidas");
						this.ComprarBebida();
						/*
						if (opt_bebida >= 0) {
							Receta bebida = bebidas.get(opt_bebida);
							Scanner sc = new Scanner(System.in);
							try {
								System.out.println("Ingrese el dinero: ");
								int dinero = Integer.parseInt(sc.nextLine());
								logger.info("Se elige la bebida: "+bebida.getNombre() + " , se ingresa la cantidad de dinero: "+ dinero);
								if (dinero >= bebida.getPrecio()) {
									
									int res_azucar = this.inventario.getAzucar() - bebida.getAzucar();
									int res_cafe = this.inventario.getCafe() - bebida.getCafe();
									int res_chocolate = this.inventario.getChocolate() - bebida.getChocolate();
									int res_leche = this.inventario.getLeche() - bebida.getLeche();
									
									
									if (res_azucar >= 0 && res_cafe >= 0 && res_chocolate >= 0 && res_leche >= 0) {
										this.inventario.setAzucar(res_azucar);
										this.inventario.setCafe(res_cafe);
										this.inventario.setChocolate(res_chocolate);
										this.inventario.setLeche(res_leche);
										
										dinero_en_maquina += dinero;
										
										System.out.println("Bebida entregada. Se devuelve vuelto si es necesario");
										logger.info("Se entrega la bebida solicitada");
									}
									else {
										throw new InvalidRangeInventory("");
									}
								}else {
									throw new LessAmountOfMoney("");
								}
								
							} catch (NumberFormatException e) {
								System.out.println("Error: Se debe ingresar una cantidad entero de dinero");
								logger.error("Se ingreso opción no valida (o numero no entero)");

								
							} catch(LessAmountOfMoney e) {
								System.out.println("Error: Cantidad de dinero ingresado menor al precio de bebida. Se devuelve el dinero");				        
						        logger.error("Cantidad de dinero ingresado menor al precio de bebida. Se devuelve el dinero");
								
							} catch (InvalidRangeInventory e) {
								System.out.println("Error: No quedan ingredientes en inventario para entregar la bebida solicitada");
								logger.error("No quedan ingredientes en inventario para entregar la bebida solicitada");
							}
							catch (Exception e) {
								System.out.println("Error");
								logger.error("Error");
							}
							
							
						} */
						continue;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error: Opción no valida. Escriba una opcion valida");
					logger.error("Error: Se ingresa Opción no valida");
				}
			}
			
			

		}

	public Inventario getInventario() {
		return inventario;
	}

}
