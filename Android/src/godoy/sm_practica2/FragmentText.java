package godoy.sm_practica2;

import godoy.sm_practica1.R;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentText extends Fragment{
	
	private TextView mtext;
	
	public FragmentText(){
        //Definir
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        
        View view = inflater.inflate(R.layout.texto, container, false);
        
        mtext = (TextView)view.findViewById(R.id.fragment_text);
        mtext.setText("Bienvenido");
        
        return view;
    }
        
}