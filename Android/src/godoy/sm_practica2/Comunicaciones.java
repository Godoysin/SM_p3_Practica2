package godoy.sm_practica2;

import godoy.sm_practica1.R;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

public class Comunicaciones extends Activity implements Cliente{
	
	private String defaultdom = "192.168.1.2";
	private String defaultport = "6000";
	private TextView mtext;
	FragmentManager fm = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicio);
		
		String user = "";
		String pass = "";
		String dom = "";
		String port = "";
		String mensaje = "";
		Bundle extra = getIntent().getExtras();
		if(extra != null){
			user = extra.getString("user");
			pass = extra.getString("pass");
			dom = extra.getString("dom");
			if(dom.compareTo("") == 0){
				dom = defaultdom;
			}
			port = extra.getString("port");
			if(port.compareTo("") == 0){
				port = defaultport;
			}
			mensaje = user + " " + pass + " " + dom + " " + port;
			if(mensaje != null){
				mtext = (TextView)findViewById(R.id.comunicaciones_text);
		        mtext.setText(mensaje);
			}
		}
		//TODO con los datos intentar conectarme a la máquina de turno
		//Control de errores
		//LogIn(mensaje);
		
		
	}

	@Override
	public String LogIn(String datos){
		// TODO Auto-generated method stub
		FragmentTransaction ft =fm.beginTransaction();
		
		FragmentServicio f = new FragmentServicio();
		ft.replace(R.layout.servicio, f);
		
		ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.addToBackStack(null);
		ft.commit();
		return null;
	}

	@Override
	public String LogOut() {
		// TODO Auto-generated method stub
		return null;
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