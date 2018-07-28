package vista_control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Client;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;

public class JFClient extends JFrame{

	private static final long serialVersionUID = 1L;
	private Client client;
	private JButton btnEnviar;
	private JTextField tfNombre;
	private JTextField tfEnviar;
	private JTextArea textArea;
	
	public JFClient() {
		setBackground(Color.DARK_GRAY);
		setForeground(Color.ORANGE);
		setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
		
		try {
			client = new Client();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().setLayout(null);
		setSize(572, 658);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(473, 568, 73, 33);
		getContentPane().add(btnEnviar);
		
		tfNombre = new JTextField();
		tfNombre.setText("Pepe");
		tfNombre.setBounds(12, 569, 116, 33);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfEnviar = new JTextField();
		tfEnviar.setColumns(10);
		tfEnviar.setBounds(140, 569, 321, 33);
		getContentPane().add(tfEnviar);
		
		buttons();

		
		this.setVisible(true);	
		
		tfEnviar.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
	
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10)
				{
					try {
						client.sendMessage(tfNombre.getText().toUpperCase() + ": " + tfEnviar.getText());
						tfEnviar.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 13, 530, 542);
		getContentPane().add(scrollPane);
		
		 textArea = new JTextArea();
		 textArea.setWrapStyleWord(true);
		 textArea.setLineWrap(true);
		 scrollPane.setViewportView(textArea);
		 client.receiverMessages(textArea);
	}
	
	private void buttons()
	{
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					client.sendMessage(tfNombre.getText().toUpperCase() + ": " + tfEnviar.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tfEnviar.setText("");
				
			}
		});
	}
}
