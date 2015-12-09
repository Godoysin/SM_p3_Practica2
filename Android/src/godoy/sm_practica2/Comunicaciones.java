package godoy.sm_practica2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import godoy.sm_practica2.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

public class Comunicaciones extends Activity implements Cliente{
	
	private static String defaulthost = "10.82.248.160";
	private String defaultport = "6000";
	private String muser = "";
	private String mpass = "";
	private String mhost = "";
	private String mport = "";
	private Enviar envio = null;
	FragmentManager fm = null;
	Mensaje mensaje;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicio);
		
		//Obtendo la ip de mi equipo
		defaulthost = getLocalIpAddress();
		
		//Extraigo los datos del Intent
		Bundle extra = getIntent().getExtras();
		if(extra != null){
			this.muser = extra.getString("user");
			this.mpass = extra.getString("pass");
			this.mhost = extra.getString("dom");
			//Dominio por defecto
			if(mhost.compareTo("") == 0){
				this.mhost = defaulthost;
			}
			mport = extra.getString("port");
			//Puerto por defecto
			if(mport.compareTo("") == 0){
				this.mport = defaultport;
			}
		}
		
		mensaje = new Mensaje();
		
		//Llamo al fragmento
		fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_console2);
		if(LogIn()){
			//TODO llegado a este punto me toca implementar el socket
//			if (fragment == null) {
//				FragmentTransaction ft = fm.beginTransaction();
//				FragmentConectar conectar = new FragmentConectar();
//				ft.add(R.id.fragment_console2, conectar);
//				ft.commit();
//			}
			mensaje.Autentification(muser, mpass);
			Enviar(mensaje.getMensaje());
		}
		else{
			if (fragment == null) {
				FragmentTransaction ft = fm.beginTransaction();
				FragmentText conectar = new FragmentText();
				ft.add(R.id.fragment_console2, conectar);
				ft.commit();
			}
		}
		
	}

	@Override
	public Boolean LogIn(){
		Boolean connection = false;
		//TODO conexión con el servidor
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if(networkInfo != null && networkInfo.isConnected()){
			//Conectado
			connection = true;
			Toast.makeText(this, "Conectado", Toast.LENGTH_LONG).show();
			}
		else{
			//No conectado
			connection = false;
			Toast.makeText(this, "No Conectado", Toast.LENGTH_LONG).show();
		}
		return connection;
	}

	@Override
	public void LogOut() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public String Operacion1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Operacion2() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//La idea es crear una nueva conexión para cada operación
	//entonces le paso el mensaje que vaya a enviar
	//Antigua conectaSocket
	
	private class Enviar extends AsyncTask<String, Integer, String>{
		protected String doInBackground(String mensaje){
			if (mport != "" && mhost != "") {
				String contentAsString = "";
				Socket s = new Socket();
				InputStream is;
				DataOutputStream dos;

				try {
					int port = Integer.parseInt(mport);
					s = new Socket(mhost, port);
					
					//Error
					is = s.getInputStream();
					dos = new DataOutputStream(s.getOutputStream());
					
					//Por aquí está el trabajo
					dos.writeUTF(mensaje);
					dos.flush();
					
					// Convert the InputStream into a string
					contentAsString=contentAsString+readIt(is,100);
					
					dos.close();
					is.close();
					s.close();
					return contentAsString;
				} catch (IOException e) {
					return e.getMessage();
					
				} catch (IllegalArgumentException e) {
					return e.getMessage();
					
				}
			}
			return "Conexión fallida";
		}
		protected void onPostExecute(String registrado) {
			
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	@Override
	public void Recivir(String datos) {
		// TODO Auto-generated method stub
		
	}
	
	public String getUser(){
		return muser;
	}
	
	public String getPass(){
		return mpass;
	}
	
	public String getHost(){
		return mhost;
	}
	
	public String getPort(){
		return mport;
	}
	
	//Obtiene la ip del equipo
	public static String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
	                    return inetAddress.getHostAddress();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	
	// Reads an InputStream and converts it to a String.
	public String readIt(InputStream stream, int len) throws IOException,
			UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
			
		reader.read(buffer);
		return new String(buffer);
	}
}