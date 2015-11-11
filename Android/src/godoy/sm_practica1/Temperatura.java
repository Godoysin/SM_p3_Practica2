package godoy.sm_practica1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Esta clase controla la lectura de temperatura.
 * @author David
 */

public class Temperatura{
	
	/**
	 * Representa la lectura de temperatura.
	 */
	protected int temperatura;
	
	/**
	 * Pasa valores al parámetro humedad.
	 * @param h Lectura de humedad.
	 */
	public Temperatura(int t){
		
		this.temperatura=t;
	}
	
	/**
	 * Leo una cadena de entrada y lo paso a valores de temperatura.
	 * @param Stream de datos de entrada.
	 */
	public Temperatura(DataInputStream dis){
		
		try{
			this.temperatura=dis.readInt();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			this.temperatura=0;
			e.printStackTrace();
		}
	}
	
	/**
	 * Convierte un cadena de salida a un ByteArray.
	 * @param dos Cadena de datos de salida.
	 */
	public void toByteArray(DataOutputStream dos){
		
		try{
			dos.writeInt(this.temperatura);
		} 
		catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Convierte los datos numéricos de temperatura a un String.
	 */
	public String toString(){
		
		return Integer.toString(temperatura);
	}
}