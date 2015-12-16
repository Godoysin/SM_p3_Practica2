package godoy.sm_practica2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import godoy.sm_practica2.R;

public class FragmentConectar extends Fragment{
	
	private Button mbutton1;
	private Button mbutton2;
	private Comunicaciones com = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.menu_conectar, container, false);
		
		mbutton1 = (Button) view.findViewById(R.id.buttonconnect1);
		mbutton2 = (Button) view.findViewById(R.id.buttonconnect2);
		
		mbutton1.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				//Intento realizar la conexión con el servidor
//				com = new Comunicaciones();
//				com.Pintar("hola");
			}
		});
		
		mbutton2.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		//Vuelvo al principio
        		Intent intent = new Intent(getActivity(), MainActivity.class);
        		startActivity(intent);
        	}
		});
		return view;
	}
}