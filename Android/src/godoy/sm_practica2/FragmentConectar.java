package godoy.sm_practica2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import godoy.sm_practica1.R;

public class FragmentConectar extends Fragment{
	
	private Button mbutton1;
	private Button mbutton2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.menu_conectar, container, false);
		return view;
	}
}