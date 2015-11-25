package godoy.sm_practica2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Esta clase controla la lectura de humedad.
 * @author David
 */

public class Humedad{
	
	/**
	 * Representa la lectura de humedad.
	 */
	protected double humedad;
	
	/**
	 * Pasa valores al parámetro humedad.
	 * @param h Lectura de humedad.
	 */
	public Humedad(double h){
		
		this.humedad=h;
	}
	
	/**
	 * Leo una cadena de entrada y lo paso a valores de humedad.
	 * @param Stream de datos de entrada.
	 */
	public Humedad(DataInputStream dis){
		
		try{
			this.humedad=dis.readDouble();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			this.humedad=0;
			e.printStackTrace();
		}
	}
	
	/**
	 * Convierte un cadena de salida a un ByteArray.
	 * @param dos Cadena de datos de salida.
	 */
	public void toByteArray (DataOutputStream dos){
		
		try{
			dos.writeDouble(this.humedad);
		} 
		catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Convierte los datos numéricos de humedad a un String.
	 */
	public String toString(){
		
		return Double.toString(humedad);
	}
}