package ujaen.git.ppt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;


public class Connection implements Runnable {
	protected String error;
	protected String errors[] = {"Usuario incorrecto","Clave incorrecta","Ya autenticado","No autenticado","Formato numérico incorrecto","Error en la secuencia de mansajes"};
	public static String MSG_WELCOME = "OK Bienvenido al servidor de pruebas\r\n";
	public static String CRLF = "/r/n";
	Socket mSocket;

	public Connection(Socket s) {
		mSocket = s;
	}

	@Override
	public void run() {
		String inputData = null;
		String outputData = "";
		String campos[];
		String parametro[] = {};
		int comando;
		int estado;
		int autenticado[] = {0,1};
		boolean ok = false;
		estado = comando = -1;
		
		if (mSocket != null) {
			try {

				DataOutputStream output = new DataOutputStream(mSocket.getOutputStream());
				BufferedReader input = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
				
				output.write(MSG_WELCOME.getBytes());

				while((inputData = input.readLine()) != null){
					System.out.println("Servidor [Recibido]> " + inputData);
					campos = inputData.split(" ");
					
					if(DetectarError(campos)){
						if(campos.length == 4){
							comando = Integer.valueOf(campos[1]);
							parametro[0] = campos[2];
							parametro[1] = campos[3];
							if(comando == 1){
								ok = true;
								estado = 1;
							}
						}
						else if(campos.length == 5){
							comando = Integer.valueOf(campos[1]);
							parametro[0] = campos[2];
							parametro[1] = campos[3];
							parametro[2] = campos[4];
							if(comando == 0){
								ok = true;
								estado = 0;
							}
						}
					}
					else{
						if(campos.length == 3){
							comando = Integer.valueOf(campos[0]);
							parametro[0] = campos[1];
							parametro[1] = campos[2];
							if(comando == 1){
								ok = true;
								estado = 1;
							}
						}
						else if(campos.length == 4){
							comando = Integer.valueOf(campos[0]);
							parametro[0] = campos[1];
							parametro[1] = campos[2];
							parametro[2] = campos[3];
							if(comando == 0){
								ok = true;
								estado = 0;
							}
						}
					}
					
					
					if(ok){
						switch(estado){
						
						case 0:
							if(Integer.valueOf(parametro[0]) == 1){
								outputData = "ER " + autenticado[0] + " " + this.errors[2] + CRLF;
							}
							else if(Autenticar(parametro[1], parametro[2])){
								outputData = "OK " + autenticado[1] + CRLF;
							}
							else{
								outputData = "ER " + autenticado[0] + " " + this.error + CRLF;
							}
							break;
							
						case 1:
							if(Integer.valueOf(parametro[0]) == 1){
								try{
									String power = String.valueOf(Integer.parseInt(parametro[1]) * Integer.parseInt(parametro[1]));
									outputData = "OK " +  power + CRLF;
								}
								catch (NumberFormatException ex) {
									outputData = "ER " + this.errors[4] + CRLF;
								}
							}
							else{
								outputData = "ER "+ this.errors[3] + CRLF;
							}
						}
					}
					else{
						outputData = "ER "+ this.errors[5] + CRLF;
					}

					output.write(outputData.getBytes());

				}
				System.out.println(
						"Servidor [Finalizado]> " + mSocket.getInetAddress().toString() + ":" + mSocket.getPort());

				input.close();
				output.close();
				mSocket.close();
			} catch (SocketException se) {

				se.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	protected boolean Autenticar(String user, String pass){
		if(User(user)){
			if(Pass(pass)){
				return true;
			}
			else{
				this.error = this.errors[1];
				return false;
			}
		}
		else{
			this.error = this.errors[0];
			return false;
		}
	}
	protected boolean User(String user){
		if(user.compareTo("user1") == 0){
			return true;
		}
		else{
			return false;
		}
	}
	protected boolean Pass(String pass){
		if(pass.compareTo("123456") == 0){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean DetectarError(String input[]){
		if((input[1].compareToIgnoreCase("0") == 0 ||input[1].compareToIgnoreCase("1") == 0) && (input[2].compareToIgnoreCase("0") == 0 ||input[2].compareToIgnoreCase("1") == 0)){
			return true;
		}
		else{
			return false;
		}
	}
}
