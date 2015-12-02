package godoy.sm_practica2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Esta clase controlará el envío y recepción de mensajes.
 * @author David
 */

public class Mensaje{
	
	/**
	 * Permitirá ordenar los mensajes ya que estos iran en un orden determinado.
	 */
	protected static int secuencia;
	/**
	 * Representa los mensajes que se envían o reciben.
	 */
	private String mensaje = "";
	/**
	 * Representa el dato de temperatura.
	 */
	private Temperatura data1 = null;
	/**
	 * Representa el dato de humedad.
	 */
	private Humedad data2 = null;
	/**
	 * Permite saber el orden en que se reciben mensajes.
	 */
	protected int secuenciaRecibida = 0;
	
//	/**
//	 * Crea un mensaje a partir de valos de temperatura y humedad y les asigna una secuencia numérica.
//	 * @param t Representa la lectura de temperatura.
//	 * @param h Representa la lectura de humedad.
//	 */
//	public Mensaje(int t, double h){
//		
//		mensaje=Integer.toString(secuencia)+" "+Integer.toString(t)+" "+Double.toString(h);
//		secuencia++;
//	}
	
	public void Autentification(String user, String pass){
		this.mensaje = Integer.toString(secuencia)+" "+user+" "+pass;
	}
	
	public void Operacion(int operacion){
		this.mensaje = Integer.toString(secuencia)+" "+operacion;
	}
	
//	/**
//	 * Pasa los datos a un Array de bytes.
//	 * @return Si no hay error, devuelve un array de bytes.
//	 */
//	public byte[] toByteArray(){
//		
//		//Esta clase crea un stream de salida en el que los datos se escriben
//		//en forma de bytes.
//		ByteArrayOutputStream bos = new ByteArrayOutputStream(5);
//		//Esta clase permite escribir tipos primitivos de Java como stream de salida.
//		DataOutputStream dos = new DataOutputStream(bos);
//		try{
//			
//			dos.writeInt(secuencia);
//			data1.toByteArray(dos);
//			data2.toByteArray(dos);
//			dos.close();
//			return bos.toByteArray();
//		} 
//		catch(IOException e){
//			
//			//TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
//	/**
//	 * Pasa un array de bytes a datos.
//	 * @param Datos datos de entrada.
//	 * @param bytedata Bytes de entrada.
//	 */
//	public Mensaje(String datos, byte[] bytedata){
//		
//		//Permite leer los bytes del stream.
//		ByteArrayInputStream bais = new ByteArrayInputStream(bytedata);
//		//Permite leer tipos primitivos de Java desde un stream de entrada.
//		DataInputStream dis = new DataInputStream(bais);
//		try{
//			
//			this.secuenciaRecibida = dis.readInt();
//		}
//		catch(IOException ex){
//			
//		}
//		String[] campos = datos.split(" ");
//		if(campos.length == 3){
//			secuenciaRecibida = Integer.parseInt(campos[0]);
//			data1 = new Temperatura(Integer.parseInt(campos[1]));
//			data2 = new Humedad(Double.parseDouble(campos[2]));
//		}
//	}
	
	/**
	 * Devuelve el mensaje creado.
	 * @return El mensaje creado.
	 */
	public String getMensaje(){
		return mensaje;
	}
	
	/**
	 * Le pasa valor a la variable mensaje
	 * @param mns Mensaje entrante.
	 */
	public void setMensaje(String mns){
		this.mensaje = mns;
	}
}