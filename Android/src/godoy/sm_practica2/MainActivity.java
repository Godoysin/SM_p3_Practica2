package godoy.sm_practica2;

import godoy.sm_practica2.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity{
	
	FragmentManager fm = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_console);
		if (fragment == null) {
			FragmentTransaction ft = fm.beginTransaction();
			FragmentConsole console = new FragmentConsole();
			ft.add(R.id.fragment_console, console);
			ft.commit();
		}
	}
}