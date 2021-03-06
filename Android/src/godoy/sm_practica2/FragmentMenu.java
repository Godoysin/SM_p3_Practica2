package godoy.sm_practica2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import godoy.sm_practica2.R;

public class FragmentMenu extends Fragment{
	
	private Button mbutton1;
	private Button mbutton2;
	private EditText mnum;
	Comunicaciones com;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.menu, container, false);
		
		mbutton1 = (Button) view.findViewById(R.id.buttonmenu1);
		mbutton2 = (Button) view.findViewById(R.id.buttonmenu2);
		mnum = (EditText)view.findViewById(R.id.numbermenu);
		
		mbutton1.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				
				int value = Integer.valueOf(mnum.getText().toString());
				
				com = new Comunicaciones();
				com.Operacion1(value);
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