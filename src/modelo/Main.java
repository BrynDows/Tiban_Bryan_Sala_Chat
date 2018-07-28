package modelo;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import vista_control.JFClient;


public class Main{

	public static boolean server_ON = false;
	/*
	 * 
	 * ESTE PROGRAMA HACE USO DE 3 CLASES, 2 DE ELLAS CONTIENEN LA L�GICA DE SERVIDOR Y CLIENTE Y SE LLAMAN "CLIENT" Y "SERVER" RESPECTIVAMENTE, LA CLASE
	 * LLAMADA "JFCLIENT" CORRESPONDE CON LA INTERFAZ DEL CLIENTE.
	 * 
	 * ESTE PROGRAMA TUVO DOS VERSIONES Y ACTUALMENTE EST� EN USO LA PRIMERA VERSI�N:
	 * 		PRIMERA VERSION: EL CLIENTE HAC�A USO DE DOS SOCKETS, POR UN SOCKET SE RECIB�A Y ENVIABA EL MENSAJE DE CHAT Y POR EL OTRO SOCKET
	 * 						 SE ENVIABA SOLO EL N� DE PUERTO DEL CLIENTE CONECTADO AL SERVIDOR.
	 * 						 EL SERVIDOR TAMBI�N CONTEN�A DOS SOCKETS POR EL CUAL RECIB�A MENSAJES DE LOS CLIENTES, EL OTRO SOCKET RECIB�A EL N� DE PUERTO
	 * 						 DEL CLIENTE.
	 * 		SEGUNDA VERSION: EL SERVIDOR CONTEN�A UN �NICO SOCKET MEDIANTE EL CUAL RECIB�A Y ENVIABA MENSAJES, AS� COMO TAMBI�N RECIB�A EL N� DE PUERTO DEL CLIENTE 
	 * 						 (A DIFERENCIA DE LA PRIMERA VERSI�N QUE LO HACIA A TRAV�S DE OTRO PUERTO).
	 * 						 EL CLIENTE CONTEN�A UN �NICO SOCKET MEDIANTE EL CUAL ENVIABA UN DATAGRAMA CON EL MENSAJE.
	 */
		
	public static void main(String[] args) {
		
		//int clients = Integer.parseInt(args[0]); //PARA EJECUTAR DESDE CONSOLA.
		
		int clients = 2;//PARA EJECUTAR DESDE EL ENTORNO DE DESARROLLO.
				
		Thread ts = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Main.server_ON = true;
					Server s = new Server("localhost", 5555);
				} catch (IOException e) {
					e.printStackTrace();
				}
						
			}
		});
		ts.start();
		
		
				for (int i = 0; i < clients; i++) {
					Thread tc = new Thread(new Runnable() {
						
						@Override
						public void run() {
							if(server_ON==true)
							{
								new JFClient().setVisible(true);
							}
					
						}
					});
						tc.start();
				}
				
		
		
		
		
	}

}
