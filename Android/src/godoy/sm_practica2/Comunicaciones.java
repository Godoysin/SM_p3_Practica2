package godoy.sm_practica2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import godoy.sm_practica2.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class Comunicaciones extends Activity implements Cliente{
	
	private String defaultdom = "10.82.117.174";
	private String defaultport = "6000";
	private String muser = "";
	private String mpass = "";
	private String mhost = "";
	private String mport = "";
	FragmentManager fm = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicio);
		
		//Extraigo los datos del Intent
		Bundle extra = getIntent().getExtras();
		if(extra != null){
			this.muser = extra.getString("user");
			this.mpass = extra.getString("pass");
			this.mhost = extra.getString("dom");
			//Dominio por defecto
			if(mhost.compareTo("") == 0){
				this.mhost = defaultdom;
			}
			mport = extra.getString("port");
			//Puerto por defecto
			if(mport.compareTo("") == 0){
				this.mport = defaultport;
			}
		}
		
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
		//TODO conexi�n con el servidor
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if(networkInfo != null && networkInfo.isConnected()){
			//obtener los datos
			connection = true;
			Toast.makeText(this, "Conectado", Toast.LENGTH_LONG).show();
			}
		else{
			//mostrar el error
			connection = false;
			Toast.makeText(this, "No Conectado", Toast.LENGTH_LONG).show();
		}
		return connection;
	}

	@Override
	public void LogOut() {
		//Intent intent = new Intent(this, MainActivity.class);
		//startActivity(intent);
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

	@Override
	public void Enviar(String mensaje) {
		// TODO Auto-generated method stub
		
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
	
	//La idea es crear una nueva conexi�n para cada operaci�n
	public String conectaSocket(){
		
		if (mport != "" && mhost != "") {
			String contentAsString = "";
			Socket s = new Socket();
			InputStream is;
			DataOutputStream dos;

			try {
				int port = Integer.parseInt(mport);
				s = new Socket(mhost, port);
				
				is = s.getInputStream();
				dos = new DataOutputStream(s.getOutputStream());

				dos.writeUTF("get /index.html HTTP/1.1\r\nHOST=www.ujaen.es\r\n");
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
		return "Conexi�n fallida";

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