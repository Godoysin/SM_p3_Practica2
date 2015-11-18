package godoy.sm_practica1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

public class Comunicaciones extends Activity implements Cliente{
	
	private TextView mtext;
	FragmentManager fm = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicio);
		
		String mensaje;
		Bundle extra = getIntent().getExtras();
		if(extra != null){
			mensaje = extra.getString("datos");
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