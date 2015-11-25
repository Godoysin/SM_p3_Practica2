package godoy.sm_practica2;

/**
 * Esta clase contiene el interfaz que va a ir en el cliente.
 * @author David
 */
public interface Cliente{
	
	//Declaraciones.
	//Scanner sc = new Scanner(System.in);
	/**
	 * mensaje representa la información que enviará el cliente al servidor.
	 */
	String mensaje = "";
	
	/**
	 * Solicita los datos del cliente para enviárselos al servidor.
	 * @return Devuelve la información de registro del usuario.
	 */
	public String LogIn(String datos);
	
	/**
	 * Cierre de sesión
	 * @return Devuelve un mensaje de cierre de sesión.
	 */
	public String LogOut();
	
	/**
	 * Una de las operaciones a implementar.
	 * @return Devuelve un string con la información correspondiente a al operación.
	 */
	public String Operacion1();
	
	/**
	 * Otra de las operaciones a implementar.
	 * @return Devuelve un string con la información correspondiente a al operación.
	 */
	public String Operacion2();
	
	/**
	 * Envía datos al servidor.
	 * @param mensaje
	 */
	public void Enviar(String mensaje);
	
	/**
	 * Recive datos del servidor.
	 * @param datos
	 */
	public void Recivir(String datos);
}