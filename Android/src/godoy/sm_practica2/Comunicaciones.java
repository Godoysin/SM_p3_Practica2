package godoy.sm_practica2;

import godoy.sm_practica1.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Comunicaciones extends Activity implements Cliente{
	
	private String defaultdom = "192.168.1.2";
	private String defaultport = "6000";
	FragmentManager fm = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicio);
		
		//Declaraciones
		String user = "";
		String pass = "";
		String dom = "";
		String port = "";
		
		//Extraigo los datos del Intent
		Bundle extra = getIntent().getExtras();
		if(extra != null){
			user = extra.getString("user");
			pass = extra.getString("pass");
			dom = extra.getString("dom");
			//Dominio por defecto
			if(dom.compareTo("") == 0){
				dom = defaultdom;
			}
			port = extra.getString("port");
			//Puerto por defecto
			if(port.compareTo("") == 0){
				port = defaultport;
			}
		}
		
		//Inicia la conexión con el servidor
		LogIn(user, pass, dom, port);
		
		//TODO con los datos intentar conectarme a la máquina de turno
		//Control de errores
		//LogIn(mensaje);
		
		
	}

	@Override
	public void LogIn(String user, String pass, String dominio, String puerto){
		//Llamo al fragmento que controla la conexion
		fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_console2);
		if (fragment == null) {
			FragmentTransaction ft = fm.beginTransaction();
			FragmentConectar conectar = new FragmentConectar();
			ft.add(R.id.fragment_console2, conectar);
			ft.commit();
		}
		//TODO conexión con el servidor
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
}