package godoy.sm_practica2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import godoy.sm_practica1.R;

public class FragmentMenu extends Fragment{
	
	private Button mbutton1;
	private Button mbutton2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.menu, container, false);
		
		mbutton2 = (Button) view.findViewById(R.id.buttonmenu1);
		mbutton2 = (Button) view.findViewById(R.id.buttonmenu2);
		
		mbutton1.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				//Llamo a la operacion 1
			}
		});
		
		mbutton2.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		//Llamo a la operacion 2
        	}
		});
		return view;
	}
}