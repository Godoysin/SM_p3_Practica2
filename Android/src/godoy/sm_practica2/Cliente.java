package godoy.sm_practica2;

/**
 * Esta clase contiene el interfaz que va a ir en el cliente.
 * @author David
 */
public interface Cliente{
	
	//Declaraciones.
	//Scanner sc = new Scanner(System.in);
	/**
	 * mensaje representa la informaci�n que enviar� el cliente al servidor.
	 */
	String mensaje = "";
	
	/**
	 * Solicita los datos del cliente para envi�rselos al servidor.
	 * @return Devuelve la informaci�n de registro del usuario.
	 */
	public String LogIn(String datos);
	
	/**
	 * Cierre de sesi�n
	 * @return Devuelve un mensaje de cierre de sesi�n.
	 */
	public String LogOut();
	
	/**
	 * Una de las operaciones a implementar.
	 * @return Devuelve un string con la informaci�n correspondiente a al operaci�n.
	 */
	public String Operacion1();
	
	/**
	 * Otra de las operaciones a implementar.
	 * @return Devuelve un string con la informaci�n correspondiente a al operaci�n.
	 */
	public String Operacion2();
	
	/**
	 * Env�a datos al servidor.
	 * @param mensaje
	 */
	public void Enviar(String mensaje);
	
	/**
	 * Recive datos del servidor.
	 * @param datos
	 */
	public void Recivir(String datos);
}