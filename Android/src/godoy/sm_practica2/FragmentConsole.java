package godoy.sm_practica2;

import godoy.sm_practica1.R;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentConsole extends Fragment{
	
	private EditText muser;
	private EditText mpass;
	private EditText mdom;
	private EditText mport;
	private Button mbutton;
	//private TextView mtext;
	
	public FragmentConsole() {
        // Required empty public constructor
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.console, container, false);

		muser = (EditText)view.findViewById(R.id.console_user);
		mbutton = (Button) view.findViewById(R.id.console_button);
        mpass = (EditText)view.findViewById(R.id.console_pass);
        mdom = (EditText)view.findViewById(R.id.console_dominio);
        mport = (EditText)view.findViewById(R.id.console_puerto);
        //mtext = (TextView)view.findViewById(R.id.console_text);
        
        
        //mbutton.setOnClickListener(new OnClickListener(){
        	//public void onClick(View view){
        		//String mensaje = "Nombre: " + muser.getText().toString() + "\r\n";
        		//mensaje = mensaje + " " + "Clave: " +mpass.getText().toString() + "\r\n";
        		//mensaje = mensaje + " " + "Dominio: " +mdom.getText().toString() + "\r\n";
        		//mensaje = mensaje + " " + "Puerto: " +mport.getText().toString() + "\r\n";
        		//mtext.setText(mensaje);
            
        	//}
        //});
		
		final FragmentManager fm = getActivity().getFragmentManager();
		
		mbutton.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		
        		String user = muser.getText().toString();
        		String pass = mpass.getText().toString();
        		String dom = mdom.getText().toString();
        		String port = mport.getText().toString();
        		
        		//Por alguna razón con esto crashea
        		Intent intent = new Intent(getActivity(), Comunicaciones.class);
        		intent.putExtra("user", user);
        		intent.putExtra("pass", pass);
        		intent.putExtra("dom", dom);
        		intent.putExtra("port", port);
        		startActivity(intent);
        		
        		//Esto de momento no hace falta, pero está bien y permite
        		//cambiar un fragmento por otro
        		//FragmentTransaction ft =fm.beginTransaction();
        		
        		//FragmentServicio f = new FragmentServicio();
				//ft.replace(R.id.fragment_console, f);
        		
        		//ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    			//ft.addToBackStack(null);
    			//ft.commit();
        	}
        });
        return view;
	}
	
	 
}