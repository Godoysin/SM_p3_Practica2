package godoy.sm_practica2;

import godoy.sm_practica2.R;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FragmentConsole extends Fragment{
	
	private EditText muser;
	private EditText mpass;
	private EditText mdom;
	private EditText mport;
	private Button mbutton;
	
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

		//final FragmentManager fm = getActivity().getFragmentManager();
		
		mbutton.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		
        		//Declaraciones
        		String user = muser.getText().toString();
        		String pass = mpass.getText().toString();
        		String dom = mdom.getText().toString();
        		String port = mport.getText().toString();
        		
        		//Llamo al activity comunicaciones y le paso los datos
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