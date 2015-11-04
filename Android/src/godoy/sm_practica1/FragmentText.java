package godoy.sm_practica1;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentText extends Fragment{
	
	public FragmentText(){
        //Definir
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        
        View view = inflater.inflate(R.layout.texto, container, false);
        
        return view;
    }
        
}