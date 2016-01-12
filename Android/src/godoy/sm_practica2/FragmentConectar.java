package godoy.sm_practica2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import godoy.sm_practica2.R;

public class FragmentConectar extends Fragment{
	
	private Button mbutton1;
	private Button mbutton2;
	private Comunicaciones com = null;
	final FragmentManager fm = getActivity().getFragmentManager();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.menu_conectar, container, false);
		
		mbutton1 = (Button) view.findViewById(R.id.buttonconnect1);
		mbutton2 = (Button) view.findViewById(R.id.buttonconnect2);
		
		mbutton1.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				//Intento realizar la conexión con el servidor
				//Por aquí falla pero no sé como hacerlo
//				com = new Comunicaciones();
//				com.RealizaAutenticacion();
				//TODO cambiar a 0
				if(com.autenticado == 1){
					//Llamar al siguiente fragmento
					//TODO no lo he podido probar porque me da fallos al principio
					FragmentTransaction ft = fm.beginTransaction();
					FragmentMenu menu = new FragmentMenu();
					ft.replace(R.id.fragment_console2, menu);
					ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	    			ft.addToBackStack(null);
					ft.commit();
				}
				else{
					//Vuelvo al principio
	        		Intent intent = new Intent(getActivity(), MainActivity.class);
	        		startActivity(intent);
				}
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