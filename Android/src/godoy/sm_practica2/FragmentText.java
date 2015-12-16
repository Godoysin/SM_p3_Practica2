package godoy.sm_practica2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentText extends Fragment{
	
	public static final String PARAMETRO = "p1" ;
	private TextView mtextview;
	private String mtext = "Bienvenido";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        
        View view = inflater.inflate(R.layout.texto, container, false);
        
        mtextview = (TextView)view.findViewById(R.id.fragment_text);
        mtextview.setText(mtext);
        
        return view;
    }
    
    public static FragmentText newInstance(Bundle args){
    	FragmentText f = new FragmentText();
    	if(args != null){
    		f.setArguments(args);
    	}
    	return f;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle bundle = getArguments();
		if(bundle != null){
			mtext = bundle.getString(PARAMETRO);
		}
//		mtextview.setText(mtext);
	}
    
    
}