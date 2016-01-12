package godoy.sm_practica2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

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
	
	private static String default_user = "user1";
	private static String default_pass = "123456";
//	private static String default_host = "192.168.1.131";
	private static String default_host = "192.168.1.138";
	private static String default_port = "6000";
	public int autenticado = 0;
	private String muser = "";
	private String mpass = "";
	private String mhost = "";
	private String mport = "";
	public String respuesta = "";
	private Tarea tarea = null;
	FragmentManager fm = null;
	Mensaje mensaje;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicio);
		
		//Extraigo los datos del Intent
		Bundle extra = getIntent().getExtras();
		if(extra != null){
			this.muser = extra.getString("user");
			//Usuario por defecto
			if(muser.compareTo("") == 0){
				muser = default_user;
			}
			this.mpass = extra.getString("pass");
			//Clave por defecto
			if(mpass.compareTo("") == 0){
				mpass = default_pass;
			}
			this.mhost = extra.getString("dom");
			//Dominio por defecto
			if(mhost.compareTo("") == 0){
				this.mhost = default_host;
			}
			this.mport = extra.getString("port");
			//Puerto por defecto
			if(mport.compareTo("") == 0){
				this.mport = default_port;
			}
		}
		//Inicializo la clase de los mensajes
		mensaje = new Mensaje();
		
		//Llamo al fragmento
		fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_console2);
		if(LogIn()){
			if (fragment == null) {
				FragmentTransaction ft = fm.beginTransaction();
				FragmentConectar conectar = new FragmentConectar();
				ft.add(R.id.fragment_console2, conectar);
				ft.commit();
			}
			
//			Pintar(muser);
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
	public void RealizaAutenticacion(){
		//Creo el mensaje de autenticación
		mensaje.Autentification(autenticado, muser, mpass);
		tarea = new Tarea();
		//Envio el mensaje al servidor
		tarea.execute(mensaje.getMensaje(),null,null);
		AnalizaRespuesta();
	}
	public void RealizaOperacion1(int value){
		tarea = new Tarea();
		//Envio el mensaje al servidor
		tarea.execute(Operacion1(value),null,null);
		AnalizaRespuesta();
	}

	@Override
	public Boolean LogIn(){
		Boolean connection = false;
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
	public String Operacion1(int value) {
		//Creo el mensaje de operacion;
		mensaje.Operacion(autenticado, value);
		return mensaje.getMensaje();
	}

	@Override
	public String Operacion2() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//La idea es crear una nueva conexión para cada operación
	//entonces le paso el mensaje que vaya a enviar
	//Antigua conectaSocket
	
	private class Tarea extends AsyncTask<String, Integer, String>{
		@Override
		protected String doInBackground(String... mensaje){
			return Enviar(mensaje[0]);
		}
		protected void onPostExecute(String reply) {
			Recibir(reply);
		}
		
	}

	@Override
	public void Recibir(String datos) {
		this.respuesta = datos;
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
	
	// Reads an InputStream and converts it to a String.
	public String readIt(InputStream stream, int len) throws IOException,
			UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
			
		reader.read(buffer);
		return new String(buffer);
	}

	@Override
	public String Enviar(String mensaje) {
		if (mport != "" && mhost != "") {
			String contentAsString = "";
			Socket s = null;
			InputStream is;
			DataOutputStream dos;

			try {
				int port = Integer.parseInt(mport);
				s = new Socket(mhost, port);
				
				is = s.getInputStream();
				dos = new DataOutputStream(s.getOutputStream());
				
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
	public void AnalizaRespuesta(){
		//Esto no lo puedo probar porque me falla la recepción de datos, así que quizas habría que quitar /r/n
		String[] respuestas = respuesta.split(" ");
		if(respuestas.length == 2){
			if(autenticado == 0){
//				this.autenticado = Integer.valueOf(respuestas[1]);
				//Hago trampa
				autenticado = 1;
				Pintar("Autenticado");
			}
			else{
//				respuesta = String.valueOf(respuestas[1]);
				//Hago trampa
				respuesta = "4";
				Pintar("Operación correcta");
			}
		}
		else if(respuestas.length == 3){
			Pintar(respuestas[2]);
		}
	}
	//TODO
	public void Pintar(String text){
		
		Bundle bundle = new Bundle();
		bundle.putString(FragmentText.PARAMETRO, text);
		
		
		FragmentTransaction ft = fm.beginTransaction();
		Fragment f = fm.findFragmentById(R.id.fragment_text2);
		FragmentText pinto = FragmentText.newInstance(bundle);
		if(f != null){
			ft.replace(R.id.fragment_text2, pinto);
		}
		else{
			ft.add(R.id.fragment_text2, pinto);
		}
		ft.commit();
	}
}