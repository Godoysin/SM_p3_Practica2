package godoy.sm_practica1;

import android.app.Activity;
import android.os.Bundle;

public class Comunicaciones extends Activity implements Cliente{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.servicio);
		
		String mensaje = getIntent().getExtras().getString("datos");
	}

	@Override
	public String LogIn(String datos){
		// TODO Auto-generated method stub
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