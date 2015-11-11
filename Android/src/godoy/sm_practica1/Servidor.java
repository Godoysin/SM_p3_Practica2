package godoy.sm_practica1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase gestiona el comportamiento del servidor.
 * @author David
 */

public class Servidor{
	
	//Posibles utilidades
	//Scanner sc = new Scanner(System.in);
	//List<String> lista = new ArrayList<String>();
	
	/**
	 * Representa los datos que recibe el servidor.
	 */
	protected String datos = "";
	/**
	 * Representa los mensajes que recibe el servidor.
	 */
	protected String mensaje = "";
	
	/**
	 * Maquina de estados que gestiona el funcionamiento del servidor.
	 */
	public void Maquina(){
		
		//Funciones locales
		int estado;
		int inicio = 0;
		int autentificar = 1;
		int servicio1 = 2;
		int servicio2 = 3;
		Boolean fin_conex = false, fin = false;
		
		do{
			
			//Lo primero que hago siempre es recibir el mensaje del cliente.
			Recibir(mensaje);
			//y en la primera iteración fuerzo a entrar en el primer estado.
			estado = inicio;
			
			do{
				//Inicio de la maquina de estados.
				switch(estado){
				
				//Aquí se reciben los datos de usuario y clave.
				case inicio:
					
					//Lo primero que hago siempre es recibir el mensaje del cliente.
					Recibir(mensaje);
					//TODO Procesar los datos.
					
					//Si el proceso es afirmativo:
					estado=autentificar;
					break;
						
				//Aquí se comprueba que el usuario y la clave sean correctos.
				case autentificar:
					
					//Lo primero que hago siempre es recibir el mensaje del cliente.
					Recibir(mensaje);
					//TODO Procesar los datos.
					
					if(Autentificar(datos)==true){
						
						//Indicar que ha habido éxito.
						//Se pasa al siguiente estado.
						estado = Elegir();
					}
					else{
						
						//Indicar que ha fallado.
						//Ver si quiere repetir o cerrar sesión.
					}
					break;
						
				//Estado del primer servicio.
				case servicio1:
					
					Servicio1();
					//Si solicita seguir usando el servidor.
					if(Repetir() == true){
						
						estado = Elegir();
					}
					else{
						
						//Solicita dejar de usar el servidor.
						fin = true;
						fin_conex = true;
					}
					break;
						
				//Estado del segundo servicio.
				case servicio2:
					
					Servicio2();
					//Si solicita seguir usando el servidor.
					if(Repetir() == true){
						
						estado = Elegir();
					}
					else{
						
						//Solicita dejar de usar el servidor.
						fin = true;
						fin_conex = true;
					}
					break;
						
				//Caso de error
				default:
					//TODO Implementar solución a posibles errores.
					break;
				}
			//Fin de la máquina de estados.
			}while(fin=true);
			
		}while(fin_conex=true);
		
	}
	
	/**
	 * Comprueba la validez del usuario y clave introducidos.
	 * @param datos Datos que ha recibido el servidor.
	 * @return True si es exitosa, False si no lo es.
	 */
	public Boolean Autentificar(String datos){
		
		//Funciones locales.
		boolean autentificar = false;
		
		//TODO Procesar los datos.
		
		//Si la autentidicación es afirmativa: autentificar = true;
		//Si la autentidicación es negativa autentificar = false;
		
		return autentificar;
	}
	
	/**
	 * Esta es la función que regula los servicios que el cliente puede pedir al servidor.
	 * @return El servicio solicitado.
	 */
	public int Elegir(){
		
		int funcion = 0;
		
		//Lo primero que hago siempre es recibir el mensaje del cliente.
		Recibir(mensaje);
		//TODO Procesar los datos
		
		//Ahora habria que mostrar al cliente la oferta de servicios, pedirle que solicite uno
		//y recibir la respuesta.
		
		return funcion;
	}
	
	/**
	 * Función del primer servicio.
	 */
	public void Servicio1(){
		
		//Lo primero que hago siempre es recibir el mensaje del cliente.
		Recibir(mensaje);
		//TODO Procesar los datos.
		
		//En función de lo que se procese, actuar en consecuencia.
	}
	
	/**
	 * Función del segundo servicio.
	 */
	public void Servicio2(){
		
		//Lo primero que hago siempre es recibir el mensaje del cliente.
		Recibir(mensaje);
		//TODO Procesar los datos.
			
		//En función de lo que se procese, actuar en consecuencia.
	}
	
	/**
	 * Esta clase pregunta al cliente si quiere volver a usar el servio, usar otro o salir.
	 * @return True si desea repetir, False si desea salir.
	 */
	public Boolean Repetir(){
		
		Boolean repetir = false;
		Boolean error = true;
		
		//No salimos del bucle hasta que tengamos una respuesta satisfactoria.
		do{
			//TODO Preguntamos al cliente que quiere hacer.
			Enviar(datos);
			Recibir(mensaje);
			
			//Si solicita repetir:
			repetir = true;
			error = false;
			
			//Si no quiere se cierra la conexión.
			repetir = false;
			error = false;
			
			//Si hay un error, se repite el bucle hasta que se solucione.
			
		}while(error = true);
		return repetir;
	}
	
	/**
	 * Envía datos al cliente.
	 * @param datos Datos que desea enviar el servidor.
	 */
	public void Enviar(String datos){
			
	}
		
	/**
	 * Recive datos del cliente.
	 * @param mensaje Mensaje que recibe del cliente.
	 */
	public void Recibir(String mensaje){
		
		this.datos=mensaje;
	}
	
}