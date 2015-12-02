package godoy.sm_practica2;

import godoy.sm_practica2.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class Comunicaciones extends Activity implements Cliente{
	
	private String defaultdom = "192.168.1.2";
	private String defaultport = "6000";
	private String muser = "";
	private String mpass = "";
	private String mdom = "";
	private String mport = "";
	FragmentManager fm = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicio);
		
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
		}
		else{
			if (fragment == null) {
				FragmentTransaction ft = fm.beginTransaction();
				FragmentText conectar = new FragmentText();
				ft.add(R.id.fragment_console2, conectar);
				ft.commit();
			}
		}
		
		
		//Extraigo los datos del Intent
		Bundle extra = getIntent().getExtras();
		if(extra != null){
			this.muser = extra.getString("user");
			this.mpass = extra.getString("pass");
			this.mdom = extra.getString("dom");
			//Dominio por defecto
			if(mdom.compareTo("") == 0){
				this.mdom = defaultdom;
			}
			mport = extra.getString("port");
			//Puerto por defecto
			if(mport.compareTo("") == 0){
				this.mport = defaultport;
			}
		}
		
		//Inicia la conexión con el servidor
		//LogIn(user, pass, dom, port);
		
		//TODO con los datos intentar conectarme a la máquina de turno
		//Control de errores
		//LogIn(mensaje);
		
		
	}

	@Override
	public Boolean LogIn(){
		Boolean connection = false;
		//TODO conexión con el servidor
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if(networkInfo != null && networkInfo.isConnected()){
			//obtener los datos
			connection = true;
			
			}
		else{
			//mostrar el error
			connection = false;
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
	
	public String getDom(){
		return mdom;
	}
	
	public String getPort(){
		return mport;
	}
}