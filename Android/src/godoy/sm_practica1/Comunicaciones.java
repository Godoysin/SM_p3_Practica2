package godoy.sm_practica1;

import android.app.Activity;
import android.os.Bundle;

public class Comunicaciones extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.servicio);
		
		getIntent().getExtras().getString("datos");
	}
}