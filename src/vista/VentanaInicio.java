
package vista;

import conexionBD.ConexionBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.AllPermission;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import controlador.*;
import modelo.*;

class Interfaz extends JFrame implements ActionListener, ItemListener{
	
	ConexionBD conexion = ConexionBD.getInstance();
	
	int x=0;
	int y=0;
	CompradorDAO compradorDAO = CompradorDAO.getInstance();
	ContratistaDAO contratistaDAO = ContratistaDAO.getInstance();
	CriptomonedaDAO criptomonedaDAO = CriptomonedaDAO.getInstance();
	PoolDAO poolDAO = PoolDAO.getInstance();
	OrdenDAO ordenDAO = OrdenDAO.getInstance();
	OrdenDePotenciaDAO ordenDePotenciaDAO = OrdenDePotenciaDAO.getInstance();
	JMenuBar menuBar = new JMenuBar();//=================MenuBar
	JMenu comprador = new JMenu("Comprador");
	JMenu contratista = new JMenu("Contratista");
	JMenu criptomoneda = new JMenu("Criptomoneda");
	JMenu pool = new JMenu("Pool");
	JMenu orden = new JMenu("Orden");
	JMenu ordenDePotencia = new JMenu("Orden de potencia");
	JMenuItem menuItems[][]=new JMenuItem[6][4];
	JLabel lblOpComprador = new JLabel("", SwingConstants.CENTER);//================Formulario
	JLabel lblOpContratista = new JLabel("", SwingConstants.CENTER);
	JLabel lblOpCriptomoneda = new JLabel("", SwingConstants.CENTER);
	JLabel lblOpPool = new JLabel("", SwingConstants.CENTER);
	JLabel lblOpOrden = new JLabel("", SwingConstants.CENTER);
	JLabel lblOpOrdenDePotencia = new JLabel("", SwingConstants.CENTER);
	JLabel lblsComprador[] = new JLabel[8];
	JLabel lblsContratista[] = new JLabel[3];
	JLabel lblsCriptomoneda[] = new JLabel[3];
	JLabel lblsPool[] = new JLabel[4];
	JLabel lblsOrden[] = new JLabel[4];
	JLabel lblsOrdenDePotencia[] = new JLabel[7];
	JTextField jtfsComprador[] = new JTextField[8];
	JTextField jtfsContratista[] = new JTextField[3];
	JTextField jtfsCriptomoneda[] = new JTextField[3];
	JTextField jtfsPool[] = new JTextField[4];
	JTextField jtfsOrden[] = new JTextField[3];
	JTextField jtfsOrdenDePotencia[] = new JTextField[3];
	JComboBox<String> comboCompradorIdOrden = new JComboBox<String>();
	JComboBox<String> comboOrdenIdOrdenDePotencia = new JComboBox<String>();
	JComboBox<String> comboCriptomonedaIdOrdenDePotencia = new JComboBox<String>();
	JComboBox<String> comboContratistaIdOrdenDePotencia = new JComboBox<String>();
	JComboBox<String> comboPoolIdOrdenDePotencia = new JComboBox<String>();
	JComboBox<String> comboFecha[]=new JComboBox[3];
	JButton interacciones[][] = new JButton[6][4];
	JTable tablas[]=new JTable[6];//=========================Tablas
	JScrollPane sp[] = new JScrollPane[6];
	JPanel panelComprador = new JPanel();//======================Paneles y Frames
	JPanel panelContratista = new JPanel();
	JPanel panelCriptomoneda = new JPanel();
	JPanel panelPool = new JPanel();
	JPanel panelOrden = new JPanel();
	JPanel panelOrdenDePotencia = new JPanel();
	JInternalFrame frameComprador=new JInternalFrame();
	JInternalFrame frameContratista=new JInternalFrame();
	JInternalFrame frameCriptomoneda=new JInternalFrame();
	JInternalFrame framePool=new JInternalFrame();
	JInternalFrame frameOrden=new JInternalFrame();
	JInternalFrame frameOrdenDePotencia=new JInternalFrame();
	JInternalFrame lastOpent;
	JDesktopPane dp = new JDesktopPane();
	
	BufferedImage imgComprador, imgContratista,imgCriptomoneda,imgPool,imgOrden,imgOrdenDePotencia,imgAlta,imgBaja,imgCambio,imgConsulta;
    JLabel [][] imgs = new JLabel[6][2];

	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static boolean validate(String emailStr) {
		       Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		       return matcher.find();
		     }
	
	public Interfaz() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1315,862);
		setLocationRelativeTo(null);
		setTitle("NiceHash");
		setVisible(true);
		
		asignacion();
		setJMenuBar(menuBar);
		
		panelYFrame(panelComprador, frameComprador, new Color(255, 172, 212), "Comprador");
		panelYFrame(panelContratista, frameContratista, new Color(255, 172, 188), "Contratista");
		panelYFrame(panelCriptomoneda, frameCriptomoneda, new Color(255, 172, 172), "Criptomoneda");
		panelYFrame(panelPool, framePool, new Color(255, 196, 172), "Pool");
		panelYFrame(panelOrden, frameOrden, new Color(255, 209, 172), "Orden");
		panelYFrame(panelOrdenDePotencia, frameOrdenDePotencia, new Color(255, 234, 172), "Orden De Potencia");
		
		modificarYAñadirLabel(0, 25, 250, 40,lblOpComprador,panelComprador,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(0, 25, 250, 40,lblOpContratista,panelContratista,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(0, 25, 250, 40,lblOpCriptomoneda,panelCriptomoneda,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(0, 25, 250, 40,lblOpPool,panelPool,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(0, 25, 250, 40,lblOpOrden,panelOrden,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(0, 25, 250, 40,lblOpOrdenDePotencia,panelOrdenDePotencia,new Font("Calibri", Font.BOLD, 50));
		
		//===============================================================================================================Formulario======================================
		String stringsComprador[]= {"ID Comprador","Nombre","Wallet","Dirección","Ciudad","Estado","Teléfono","Email"};
		String stringsContratista[]= {"ID Contratista","Nombre del contratista","Meses operando"};
		String stringsCriptomoneda[]= {"ID Criptomoneda","Precio Unitario","Descripciónn"};
		String stringsPool[]= {"ID Pool","Potencia MH/s","Cantidad de trabajadores","Cantidad de mineros"};
		String stringsOrden[]= {"ID Orden","Fecha de orden","ID Comprador","Horas de operación"};
		String stringsOrdenDePotencia[]= {"ID Compra","ID Orden","ID Criptomoneda","ID Contratista","ID Pool","Cantidad de criptomonedas","Precio fiat"};
		
		for(int i=0;i<lblsComprador.length;i+=1) {
			lblsComprador[i]=new JLabel(stringsComprador[i]);
			lblsComprador[i].setBounds(250, 50+(i*30), 100, 20);
			jtfsComprador[i]=new JTextField();
			jtfsComprador[i].setBounds(350, 50+(i*30), 250, 20);
			panelComprador.add(lblsComprador[i]);
			panelComprador.add(jtfsComprador[i]);
		}
		jtfsComprador[0].addKeyListener(new KeyAdapter() {//validacion entero
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsComprador[0].getText().length()<10||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsComprador[0].setEditable(true);
				}else{
					jtfsComprador[0].setEditable(false);
				}
			}
		});
		jtfsComprador[1].addKeyListener(new KeyAdapter() {//validacion letras
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				char ch = ke.getKeyChar();
				if ((jtfsComprador[1].getText().equals("")?true:!(jtfsComprador[1].getText().charAt(jtfsComprador[1].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&(((Character.isLetter(ch)||(code==KeyEvent.VK_SPACE)))&&jtfsComprador[1].getText().length()<150||(code==KeyEvent.VK_BACK_SPACE))) {
					jtfsComprador[1].setEditable(true);
				}else{
					jtfsComprador[1].setEditable(false);
				}
			}
		});
		jtfsComprador[2].addKeyListener(new KeyAdapter() {//validacion tamaño
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (jtfsComprador[2].getText().length()<100||code==KeyEvent.VK_BACK_SPACE) {
					jtfsComprador[2].setEditable(true);
				}else{
					jtfsComprador[2].setEditable(false);
				}
			}
		});
		jtfsComprador[3].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((jtfsComprador[3].getText().equals("")?true:!(jtfsComprador[3].getText().charAt(jtfsComprador[3].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&(jtfsComprador[3].getText().length()<100||(code==KeyEvent.VK_BACK_SPACE))) {
					jtfsComprador[3].setEditable(true);
				}else{
					jtfsComprador[3].setEditable(false);
				}
			}
		});
		jtfsComprador[4].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				char ch = ke.getKeyChar();
				if ((jtfsComprador[4].getText().equals("")?true:!(jtfsComprador[4].getText().charAt(jtfsComprador[4].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&((((Character.isLetter(ch))||(code==KeyEvent.VK_SPACE)))&&jtfsComprador[4].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE))) {
					jtfsComprador[4].setEditable(true);
				}else{
					jtfsComprador[4].setEditable(false);
				}
			}
		});
		jtfsComprador[5].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				char ch = ke.getKeyChar();
				if ((jtfsComprador[5].getText().equals("")?true:!(jtfsComprador[5].getText().charAt(jtfsComprador[5].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&((((Character.isLetter(ch))||(code==KeyEvent.VK_SPACE)))&&jtfsComprador[5].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE))) {
					jtfsComprador[5].setEditable(true);
				}else{
					jtfsComprador[5].setEditable(false);
				}
			}
		});
		jtfsComprador[6].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9')||ke.getKeyChar() == '-')&&jtfsComprador[6].getText().length()<15||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsComprador[6].setEditable(true);
				}else{
					jtfsComprador[6].setEditable(false);
				}
			}
		});
		jtfsComprador[7].addKeyListener(new KeyAdapter() {//validacion tamaño
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				char ch = ke.getKeyChar();
				if ((jtfsComprador[7].getText().equals("")?true:!(jtfsComprador[7].getText().charAt(jtfsComprador[7].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&(((Character.isLetter(ch)||Character.isDigit(ch)||ch=='@'||ch=='.')&&jtfsComprador[7].getText().length()<50)||code==KeyEvent.VK_BACK_SPACE)) {
					jtfsComprador[7].setEditable(true);
				}else{
					jtfsComprador[7].setEditable(false);
				}
			}
		});
		
		for(int i=0;i<lblsContratista.length;i+=1) {
			lblsContratista[i]=new JLabel(stringsContratista[i]);
			lblsContratista[i].setBounds(250, 50+(i*30), 150, 20);
			jtfsContratista[i]=new JTextField();
			jtfsContratista[i].setBounds(400, 50+(i*30), 250, 20);
			panelContratista.add(lblsContratista[i]);
			panelContratista.add(jtfsContratista[i]);
		}
		jtfsContratista[0].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsContratista[0].getText().length()<7||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsContratista[0].setEditable(true);
				}else{
					jtfsContratista[0].setEditable(false);
				}
			}
		});
		jtfsContratista[1].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				char ch = ke.getKeyChar();
				if ((jtfsContratista[1].getText().equals("")?true:!(jtfsContratista[1].getText().charAt(jtfsContratista[1].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&((((Character.isLetter(ch))||(code==KeyEvent.VK_SPACE)))&&jtfsContratista[1].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE))) {
					jtfsContratista[1].setEditable(true);
				}else{
					jtfsContratista[1].setEditable(false);
				}
			}
		});
		jtfsContratista[2].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsContratista[2].getText().length()<5||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsContratista[2].setEditable(true);
				}else{
					jtfsContratista[2].setEditable(false);
				}
			}
		});
		
		
		for(int i=0;i<lblsCriptomoneda.length;i+=1) {
			lblsCriptomoneda[i]=new JLabel(stringsCriptomoneda[i]);
			lblsCriptomoneda[i].setBounds(250, 50+(i*30), 110, 20);
			jtfsCriptomoneda[i]=new JTextField();
			jtfsCriptomoneda[i].setBounds(360, 50+(i*30), 250, 20);
			panelCriptomoneda.add(lblsCriptomoneda[i]);
			panelCriptomoneda.add(jtfsCriptomoneda[i]);
		}
		jtfsCriptomoneda[0].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((jtfsCriptomoneda[0].getText().equals("")?true:!(jtfsCriptomoneda[0].getText().charAt(jtfsCriptomoneda[0].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&(jtfsCriptomoneda[0].getText().length()<20||(code==KeyEvent.VK_BACK_SPACE))) {
					jtfsCriptomoneda[0].setEditable(true);
				}else{
					jtfsCriptomoneda[0].setEditable(false);
				}
			}
		});
		jtfsCriptomoneda[1].addKeyListener(new KeyAdapter() {//validacion double
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9')||(ke.getKeyChar() == '.'&&!jtfsCriptomoneda[1].getText().contains(".")))&&jtfsCriptomoneda[1].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsCriptomoneda[1].setEditable(true);
				}else{
					jtfsCriptomoneda[1].setEditable(false);
				}
			}
		});
		jtfsCriptomoneda[2].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				char ch = ke.getKeyChar();
				if ((jtfsCriptomoneda[2].getText().equals("")?true:!(jtfsCriptomoneda[2].getText().charAt(jtfsCriptomoneda[2].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&((Character.isLetter(ch)||Character.isDigit(ch)||code==KeyEvent.VK_SPACE)&&(jtfsCriptomoneda[2].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)))) {
					jtfsCriptomoneda[2].setEditable(true);
				}else{
					jtfsCriptomoneda[2].setEditable(false);
				}
			}
		});
		
		for(int i=0;i<lblsPool.length;i+=1) {
			lblsPool[i]=new JLabel(stringsPool[i]);
			lblsPool[i].setBounds(250, 50+(i*30), 150, 20);
			jtfsPool[i]=new JTextField();
			jtfsPool[i].setBounds(400, 50+(i*30), 150, 20);
			panelPool.add(lblsPool[i]);
			panelPool.add(jtfsPool[i]);
		}
		jtfsPool[0].addKeyListener(new KeyAdapter() {//validacion tamaño
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((jtfsPool[0].getText().equals("")?true:!(jtfsPool[0].getText().charAt(jtfsPool[0].getText().length()-1)==' '&&code==KeyEvent.VK_SPACE))&&(jtfsPool[0].getText().length()<10||code==KeyEvent.VK_BACK_SPACE)) {
					jtfsPool[0].setEditable(true);
				}else{
					jtfsPool[0].setEditable(false);
				}
			}
		});
		jtfsPool[1].addKeyListener(new KeyAdapter() {//validacion entero
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsPool[1].getText().length()<19||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsPool[1].setEditable(true);
				}else{
					jtfsPool[1].setEditable(false);
				}
			}
		});
		jtfsPool[2].addKeyListener(new KeyAdapter() {//validacion entero
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsPool[2].getText().length()<10||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsPool[2].setEditable(true);
				}else{
					jtfsPool[2].setEditable(false);
				}
			}
		});
		jtfsPool[3].addKeyListener(new KeyAdapter() {//validacion entero
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsPool[3].getText().length()<10||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsPool[3].setEditable(true);
				}else{
					jtfsPool[3].setEditable(false);
				}
			}
		});
		
		
		for(int i=0;i<lblsOrden.length;i+=1) {
			lblsOrden[i]=new JLabel(stringsOrden[i]);
			lblsOrden[i].setBounds(250, 50+(i*30), 150, 20);
			panelOrden.add(lblsOrden[i]);
		}
		jtfsOrden[0]=new JTextField();
		jtfsOrden[0].setBounds(400, 50, 150, 20);
		panelOrden.add(jtfsOrden[0]);
		
		
		for (int i=0;i<comboFecha.length;i+=1) {
			comboFecha[i]=new JComboBox<String>();
			comboFecha[i].setBounds(400+(i*55), 80, 55, 20);
			panelOrden.add(comboFecha[i]);
		}
		for (int i = 1; i <= 31; i+=1) {	
			if (i<10) {
				comboFecha[0].addItem("0"+i);
			}else {
				comboFecha[0].addItem(""+i);
			}
			
		}
		comboFecha[1].addItem("Ene");
		comboFecha[1].addItem("Feb");
		comboFecha[1].addItem("Mar");
		comboFecha[1].addItem("Abr");
		comboFecha[1].addItem("May");
		comboFecha[1].addItem("Jun");
		comboFecha[1].addItem("Jul");
		comboFecha[1].addItem("Ago");
		comboFecha[1].addItem("Sep");
		comboFecha[1].addItem("Oct");
		comboFecha[1].addItem("Nov");
		comboFecha[1].addItem("Dic");
		for (int i = 2021; i <= 2100; i+=1) {	comboFecha[2].addItem(""+i);}
		
		jtfsOrden[1]=new JTextField();//Se va a reutilizar como buffer
		//jtfsOrden[1].setBounds(400, 80, 150, 20);
		panelOrden.add(jtfsOrden[1]);
		comboCompradorIdOrden.setBounds(400, 110, 150, 20);
		comboCompradorIdOrden.setToolTipText("Solo se pueden poner compradores que ya estén en la base de datos");
		panelOrden.add(comboCompradorIdOrden);
		jtfsOrden[2]=new JTextField();
		jtfsOrden[2].setBounds(400, 140, 150, 20);
		panelOrden.add(jtfsOrden[2]);
		
		jtfsOrden[0].addKeyListener(new KeyAdapter() {//validacion entero
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsOrden[0].getText().length()<19||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsOrden[0].setEditable(true);
				}else{
					jtfsOrden[0].setEditable(false);
				}
			}
		});
		jtfsOrden[1].addKeyListener(new KeyAdapter() {//validacion tamaño
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (jtfsOrden[1].getText().length()<11||code==KeyEvent.VK_BACK_SPACE) {
					jtfsOrden[1].setEditable(true);
				}else{
					jtfsOrden[1].setEditable(false);
				}
			}
		});
		jtfsOrden[2].addKeyListener(new KeyAdapter() {//validacion entero
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsOrden[2].getText().length()<7||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsOrden[2].setEditable(true);
				}else{
					jtfsOrden[2].setEditable(false);
				}
			}
		});
		
		for(int i=0;i<lblsOrdenDePotencia.length;i+=1) {
			lblsOrdenDePotencia[i]=new JLabel(stringsOrdenDePotencia[i]);
			lblsOrdenDePotencia[i].setBounds(250, 50+(i*30), 180, 20);
			panelOrdenDePotencia.add(lblsOrdenDePotencia[i]);
		}
		jtfsOrdenDePotencia[0]=new JTextField();
		jtfsOrdenDePotencia[0].setBounds(430, 50, 150, 20);
		panelOrdenDePotencia.add(jtfsOrdenDePotencia[0]);
		comboOrdenIdOrdenDePotencia.setBounds(430, 80, 150, 20);
		comboOrdenIdOrdenDePotencia.setToolTipText("Solo se pueden poner ordenes que ya estén en la base de datos");
		panelOrdenDePotencia.add(comboOrdenIdOrdenDePotencia);
		comboCriptomonedaIdOrdenDePotencia.setBounds(430, 110, 150, 20);
		comboCriptomonedaIdOrdenDePotencia.setToolTipText("Solo se pueden poner criptomonedas que ya estén en la base de datos");
		panelOrdenDePotencia.add(comboCriptomonedaIdOrdenDePotencia);
		comboContratistaIdOrdenDePotencia.setBounds(430, 140, 150, 20);
		comboContratistaIdOrdenDePotencia.setToolTipText("Solo se pueden poner ordenes de potencia que ya estén en la base de datos");
		panelOrdenDePotencia.add(comboContratistaIdOrdenDePotencia);
		comboPoolIdOrdenDePotencia.setBounds(430, 170, 150, 20);
		comboPoolIdOrdenDePotencia.setToolTipText("Solo se pueden poner pools que ya estén en la base de datos");
		panelOrdenDePotencia.add(comboPoolIdOrdenDePotencia);
		jtfsOrdenDePotencia[1]=new JTextField();
		jtfsOrdenDePotencia[1].setBounds(430, 200, 150, 20);
		panelOrdenDePotencia.add(jtfsOrdenDePotencia[1]);
		jtfsOrdenDePotencia[2]=new JTextField();
		jtfsOrdenDePotencia[2].setBounds(430, 230, 150, 20);
		panelOrdenDePotencia.add(jtfsOrdenDePotencia[2]);
		
		jtfsOrdenDePotencia[0].addKeyListener(new KeyAdapter() {//validacion entero
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsOrdenDePotencia[0].getText().length()<19||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsOrdenDePotencia[0].setEditable(true);
				}else{
					jtfsOrdenDePotencia[0].setEditable(false);
				}
			}
		});
		jtfsOrdenDePotencia[1].addKeyListener(new KeyAdapter() {//validacion double
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9')||(ke.getKeyChar() == '.'&&!jtfsOrdenDePotencia[1].getText().contains(".")))&&jtfsOrdenDePotencia[1].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsOrdenDePotencia[1].setEditable(true);
				}else{
					jtfsOrdenDePotencia[1].setEditable(false);
				}
			}
		});
		jtfsOrdenDePotencia[2].addKeyListener(new KeyAdapter() {//validacion double
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9')||(ke.getKeyChar() == '.'&&!jtfsOrdenDePotencia[2].getText().contains(".")))&&jtfsOrdenDePotencia[2].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsOrdenDePotencia[2].setEditable(true);
				}else{
					jtfsOrdenDePotencia[2].setEditable(false);
				}
			}
		});
		
		for(JButton i:interacciones[0]) {
			panelComprador.add(i);
		}
		for(JButton i:interacciones[1]) {
			i.setLocation(700, i.getY());
			panelContratista.add(i);
		}
		for(JButton i:interacciones[2]) {
			panelCriptomoneda.add(i);
		}
		for(JButton i:interacciones[3]) {
			i.setLocation(600, i.getY());
			panelPool.add(i);
		}
		for(JButton i:interacciones[4]) {
			i.setLocation(600, i.getY());
			panelOrden.add(i);
		}
		for(JButton i:interacciones[5]) {
			i.setLocation(600, i.getY());
			panelOrdenDePotencia.add(i);
		}
		//==================================================================================================Fin Formulario======================================
		imgs[0][0]=new JLabel(new ImageIcon(imgComprador));
		imgs[1][0]=new JLabel(new ImageIcon(imgContratista));
		imgs[2][0]=new JLabel(new ImageIcon(imgCriptomoneda));
		imgs[3][0]=new JLabel(new ImageIcon(imgPool));
		imgs[4][0]=new JLabel(new ImageIcon(imgOrden));
		imgs[5][0]=new JLabel(new ImageIcon(imgOrdenDePotencia));
		
		for (int i = 0; i < imgs.length; i++) {
			imgs[i][0].setBounds(5,65,150,150);
			imgs[i][1] = new JLabel(new ImageIcon(imgAlta));
		}
		
		
		
		panelComprador.add(imgs[0][1]);
		panelComprador.add(imgs[0][0]);
		panelContratista.add(imgs[1][1]);
		panelContratista.add(imgs[1][0]);
		panelCriptomoneda.add(imgs[2][1]);
		panelCriptomoneda.add(imgs[2][0]);
		panelPool.add(imgs[3][1]);
		panelPool.add(imgs[3][0]);
		panelOrden.add(imgs[4][1]);
		panelOrden.add(imgs[4][0]);
		panelOrdenDePotencia.add(imgs[5][1]);
		panelOrdenDePotencia.add(imgs[5][0]);
		
		dp.setLocation(0, 0);
		dp.setSize(Toolkit. getDefaultToolkit(). getScreenSize());
		dp.setBackground(Color.DARK_GRAY);
		add(dp);
	}
	
	public void asignacion() {
		for (int i=0;i<menuItems.length;i+=1) {
			for (int j = 0; j < menuItems[i].length; j++) {
				switch (j) {
				case 0:
					menuItems[i][j]=new JMenuItem("Registrar");
					menuItems[i][j].setBackground(new Color(180, 255, 180));
					break;
				case 1:
					menuItems[i][j]=new JMenuItem("Eliminar");
					menuItems[i][j].setBackground(new Color(255, 180, 180));
					break;
				case 2:
					menuItems[i][j]=new JMenuItem("Modificar");
					menuItems[i][j].setBackground(new Color(255, 220, 180));
					break;
				case 3:
					menuItems[i][j]=new JMenuItem("Buscar");
					menuItems[i][j].setBackground(new Color(180, 180, 255));
					break;
				default:break;
				}
				menuItems[i][j].addActionListener(this);
			}
		}
		
		for(JMenuItem i:menuItems[0]) {	comprador.add(i);}
		for(JMenuItem i:menuItems[1]) {	contratista.add(i);}
		for(JMenuItem i:menuItems[2]) {	criptomoneda.add(i);}
		for(JMenuItem i:menuItems[3]) {	pool.add(i);}
		for(JMenuItem i:menuItems[4]) {	orden.add(i);}
		for(JMenuItem i:menuItems[5]) {	ordenDePotencia.add(i);}
		
		for (int i=0;i<interacciones.length;i+=1) {
			for (int j = 0; j < interacciones[i].length; j++) {
				switch (j) {
				case 0:
					interacciones[i][j]=new JButton("Interaccion principal");
					interacciones[i][j].setBounds(650, 50, 100, 20);
					interacciones[i][j].setBackground(new Color(180, 255, 180));
					interacciones[i][j].setToolTipText("Interaccion principal");
					break;
				case 1:
					interacciones[i][j]=new JButton("Borrar");
					interacciones[i][j].setBounds(650, 100, 100, 20);
					interacciones[i][j].setBackground(new Color(255, 180, 180));
					interacciones[i][j].setToolTipText("Limpia todos los campos");
					break;
				case 2:
					interacciones[i][j]=new JButton("Cancelar");
					interacciones[i][j].setBounds(650, 150, 100, 20);
					interacciones[i][j].setBackground(new Color(255, 220, 180));
					interacciones[i][j].setToolTipText("Cierra la ventana");
					break;
				case 3:
					interacciones[i][j]=new JButton("Buscar");
					interacciones[i][j].setBounds(650, 200, 100, 20);
					interacciones[i][j].setBackground(new Color(180, 180, 255));
					interacciones[i][j].setToolTipText("Busca registros y actualiza la tabla según los campos");
					break;
				default:break;
				}
				
				interacciones[i][j].addActionListener(this);
			}
		}
		
		for (int i = 0; i < sp.length; i++) {
			sp[i]=new JScrollPane();
		}
		
		menuBar.add(comprador);
		menuBar.add(contratista);
		menuBar.add(criptomoneda);
		menuBar.add(pool);
		menuBar.add(orden);
		menuBar.add(ordenDePotencia);
		
		frameComprador.setMaximumSize(new Dimension(785,550));
		frameContratista.setMaximumSize(new Dimension(850,495));
		frameCriptomoneda.setMaximumSize(new Dimension(800,495));
		framePool.setMaximumSize(new Dimension(750,495));
		frameOrden.setMaximumSize(new Dimension(750,495));
		frameOrdenDePotencia.setMaximumSize(new Dimension(750,525));
		
		
		try {
			imgComprador = ImageIO.read(new File("./archivos/comprador.PNG"));
			imgContratista = ImageIO.read(new File("./archivos/contratista.PNG"));
			imgCriptomoneda = ImageIO.read(new File("./archivos/criptomoneda.PNG"));
			imgPool = ImageIO.read(new File("./archivos/pool.PNG"));
			imgOrden = ImageIO.read(new File("./archivos/orden.PNG"));
			imgOrdenDePotencia = ImageIO.read(new File("./archivos/ordendepotencia.PNG"));
			
			imgAlta = ImageIO.read(new File("./archivos/alta.PNG"));
			imgBaja = ImageIO.read(new File("./archivos/baja.PNG"));
			imgCambio = ImageIO.read(new File("./archivos/cambio.PNG"));
			imgConsulta = ImageIO.read(new File("./archivos/consulta.PNG"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
	    
	    
	    
	    
	    
		
	}
	public void metodoQueRestableceTODO(Component...componentesGraficos) {
		for (Component c: componentesGraficos) {
			if (c instanceof JComboBox) {
				((JComboBox<?>)c).setSelectedIndex(-1);
			}else if (c instanceof JTextField) {
				((JTextField)c).setText("");
			}
		}
	}
	public void panelYFrame(JPanel panel,JInternalFrame frame,Color color,String titulo) {
		panel.setLayout(null);
		panel.setBounds(0, 0, 1300, 800);
		panel.setBackground(color);
		frame.setBounds(0, 0, 1300, 800);
		frame.setTitle(titulo);
		frame.add(panel);
		frame.setResizable(true);
		dp.add(frame);
	}
	public void modificarYAñadirLabel(int x, int y, int width,int height,JLabel label,JPanel panel,Font font) {
		label.setBounds(x, y, width, height);
		label.setFont(font);
		panel.add(label);
	}
	
	public String consultaComprador() {//comprador
		String sql = "SELECT * FROM Comprador ";
		boolean primero=true;
		if(!jtfsComprador[0].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("CompradorId="+jtfsComprador[0].getText());
		}
		if(!jtfsComprador[1].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Nombre='"+jtfsComprador[1].getText()+"'");
		}
		if(!jtfsComprador[2].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Wallet='"+jtfsComprador[2].getText()+"'");
		}
		if(!jtfsComprador[3].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Direccion='"+jtfsComprador[3].getText()+"'");
		}
		if(!jtfsComprador[4].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Ciudad='"+jtfsComprador[4].getText()+"'");
		}
		if(!jtfsComprador[5].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Estado='"+jtfsComprador[5].getText()+"'");
		}
		if(!jtfsComprador[6].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Telefono='"+jtfsComprador[6].getText()+"'");
		}
		if(!jtfsComprador[7].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Email='"+jtfsComprador[7].getText()+"'");
		}
		return sql;
	}
	public void actualizarTablaComprador(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/NiceHash",sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelComprador.remove(sp[0]);
		tablas[0] = new JTable(modeloDatos);
		tablas[0].addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	obtenerRegistroTablaComprador();
		    }
		});
		sp[0] = new JScrollPane(tablas[0]);
		sp[0].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp[0].setBounds(25, 300, 735, 200);
		panelComprador.add(sp[0]);
		panelComprador.setVisible(true);
	}
	public void obtenerRegistroTablaComprador() {
		for (int i = 0; i < jtfsComprador.length; i++) {
			jtfsComprador[i].setText(""+tablas[0].getValueAt(tablas[0].getSelectedRow(),i));
		}
	}
	public String consultaContratista() {//Contratista
		String sql = "SELECT * FROM Contratista ";
		boolean primero=true;
		if(!jtfsContratista[0].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("ContratistaId="+jtfsContratista[0].getText());
		}
		if(!jtfsContratista[1].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("NombreContratista='"+jtfsContratista[1].getText()+"'");
		}
		if(!jtfsContratista[2].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("MesesOperando="+jtfsContratista[2].getText());
		}
		return sql;
	}
	public void actualizarTablaContratista(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/NiceHash",sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelContratista.remove(sp[1]);
		tablas[1] = new JTable(modeloDatos);
		tablas[1].addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	obtenerRegistroTablaContratista();
		    }
		});
		sp[1] = new JScrollPane(tablas[1]);
		sp[1].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp[1].setBounds(25, 245, 800, 200);
		panelContratista.add(sp[1]);
		panelContratista.setVisible(true);
	}
	public void obtenerRegistroTablaContratista() {
		for (int i = 0; i < jtfsContratista.length; i++) {
			jtfsContratista[i].setText(""+tablas[1].getValueAt(tablas[1].getSelectedRow(),i));
		}
	}
	public String consultaCriptomoneda() {//Criptomoneda
		String sql = "SELECT * FROM Criptomoneda ";
		boolean primero=true;
		if(!jtfsCriptomoneda[0].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("CriptomonedaId='"+jtfsCriptomoneda[0].getText()+"'");
		}
		if(!jtfsCriptomoneda[1].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("PrecioUnidad="+jtfsCriptomoneda[1].getText());
		}
		if(!jtfsCriptomoneda[2].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("DescripcionUnidad='"+jtfsCriptomoneda[2].getText()+"'");
		}
		return sql;
	}
	public void actualizarTablaCriptomoneda(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/NiceHash",sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelCriptomoneda.remove(sp[2]);
		tablas[2] = new JTable(modeloDatos);
		tablas[2].addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	obtenerRegistroTablaCriptomoneda();
		    }
		});
		sp[2] = new JScrollPane(tablas[2]);
		sp[2].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp[2].setBounds(25, 245, 750, 200);
		panelCriptomoneda.add(sp[2]);
		panelCriptomoneda.setVisible(true);
	}
	public void obtenerRegistroTablaCriptomoneda() {
		for (int i = 0; i < jtfsCriptomoneda.length; i++) {
			jtfsCriptomoneda[i].setText(""+tablas[2].getValueAt(tablas[2].getSelectedRow(),i));
		}
	}
	public String consultaPool() {//Pool
		String sql = "SELECT * FROM Pool ";
		boolean primero=true;
		if(!jtfsPool[0].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("PoolId='"+jtfsPool[0].getText()+"'");
		}
		if(!jtfsPool[1].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("PotenciaDeMinadoMHs="+jtfsPool[1].getText());
		}
		if(!jtfsPool[2].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("CantidadDeTrabajadores="+jtfsPool[2].getText());
		}
		if(!jtfsPool[3].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("CantidadDeMineros="+jtfsPool[3].getText());
		}
		return sql;
	}
	public void actualizarTablaPool(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/NiceHash",sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelPool.remove(sp[3]);
		tablas[3] = new JTable(modeloDatos);
		tablas[3].addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	obtenerRegistroTablaPool();
		    }
		});
		sp[3] = new JScrollPane(tablas[3]);
		sp[3].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp[3].setBounds(25, 245, 700, 200);
		panelPool.add(sp[3]);
		panelPool.setVisible(true);
	}
	public void obtenerRegistroTablaPool() {
		for (int i = 0; i < jtfsPool.length; i++) {
			jtfsPool[i].setText(""+tablas[3].getValueAt(tablas[3].getSelectedRow(),i));
		}
	}
	public String consultaOrden() {//Orden
		String sql = "SELECT * FROM Orden ";
		boolean primero=true;
		if(!jtfsOrden[0].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("ordenId="+jtfsOrden[0].getText());
		}
		if(!jtfsOrden[1].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("fechaOrden='"+jtfsOrden[1].getText()+"'");
		}
		if(comboCompradorIdOrden.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("compradorId="+comboCompradorIdOrden.getSelectedItem());
		}
		if(!jtfsOrden[2].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("horasDeOperacion="+jtfsOrden[2].getText());
		}
		return sql;
	}
	public void actualizarTablaOrden(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/NiceHash",sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelOrden.remove(sp[4]);
		tablas[4] = new JTable(modeloDatos);
		tablas[4].addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	obtenerRegistroTablaOrden();
		    }
		});
		sp[4] = new JScrollPane(tablas[4]);
		sp[4].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp[4].setBounds(25, 245, 700, 200);
		panelOrden.add(sp[4]);
		panelOrden.setVisible(true);
	}
	public void obtenerRegistroTablaOrden() {
		jtfsOrden[0].setText(""+tablas[4].getValueAt(tablas[4].getSelectedRow(),0));
		jtfsOrden[1].setText(""+tablas[4].getValueAt(tablas[4].getSelectedRow(),1));
		comboFecha[0].setSelectedItem(jtfsOrden[1].getText().substring(0,2));
		comboFecha[1].setSelectedItem(jtfsOrden[1].getText().substring(3,6));
		comboFecha[2].setSelectedItem(jtfsOrden[1].getText().substring(7,11));
		
		comboCompradorIdOrden.setSelectedItem(""+tablas[4].getValueAt(tablas[4].getSelectedRow(),2));
		jtfsOrden[2].setText(""+tablas[4].getValueAt(tablas[4].getSelectedRow(),3));
	}
	public String consultaOrdenDePotencia() {//Orden de potencia
		String sql = "SELECT * FROM OrdenDePotencia ";
		boolean primero=true;
		if(!jtfsOrdenDePotencia[0].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("compraId="+jtfsOrdenDePotencia[0].getText());
		}
		if(comboOrdenIdOrdenDePotencia.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("ordenId="+comboOrdenIdOrdenDePotencia.getSelectedItem());
		}
		if(comboCriptomonedaIdOrdenDePotencia.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("criptomonedaId='"+comboCriptomonedaIdOrdenDePotencia.getSelectedItem()+"'");
		}
		if(comboContratistaIdOrdenDePotencia.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("contratistaId="+comboContratistaIdOrdenDePotencia.getSelectedItem());
		}
		if(comboPoolIdOrdenDePotencia.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("poolId='"+comboPoolIdOrdenDePotencia.getSelectedItem()+"'");
		}
		if(!jtfsOrdenDePotencia[1].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("cantidadDeCriptomonedas="+jtfsOrdenDePotencia[1].getText());
		}
		if(!jtfsOrdenDePotencia[2].getText().equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("precioFiat="+jtfsOrdenDePotencia[2].getText());
		}
		return sql;
	}
	public void actualizarTablaOrdenDePotencia(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/NiceHash",sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelOrdenDePotencia.remove(sp[5]);
		tablas[5] = new JTable(modeloDatos);
		tablas[5].addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	obtenerRegistroTablaOrdenDePotencia();
		    }
		});
		sp[5] = new JScrollPane(tablas[5]);
		sp[5].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp[5].setBounds(25, 275, 700, 200);
		panelOrdenDePotencia.add(sp[5]);
		panelOrdenDePotencia.setVisible(true);
	}
	public void obtenerRegistroTablaOrdenDePotencia() {
		jtfsOrdenDePotencia[0].setText(""+tablas[5].getValueAt(tablas[5].getSelectedRow(),0));
		comboOrdenIdOrdenDePotencia.setSelectedItem(""+tablas[5].getValueAt(tablas[5].getSelectedRow(),1));
		comboCriptomonedaIdOrdenDePotencia.setSelectedItem(""+tablas[5].getValueAt(tablas[5].getSelectedRow(),2));
		comboContratistaIdOrdenDePotencia.setSelectedItem(""+tablas[5].getValueAt(tablas[5].getSelectedRow(),3));
		comboPoolIdOrdenDePotencia.setSelectedItem(""+tablas[5].getValueAt(tablas[5].getSelectedRow(),4));
		jtfsOrdenDePotencia[1].setText(""+tablas[5].getValueAt(tablas[5].getSelectedRow(),5));
		jtfsOrdenDePotencia[2].setText(""+tablas[5].getValueAt(tablas[5].getSelectedRow(),6));
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src =arg0.getSource();
		int plusX = 30;
		int plusY = 30;
		Rectangle subIconos = new Rectangle(90,150,75,75);
		if (src==menuItems[0][0]||src==menuItems[0][1]||src==menuItems[0][2]||src==menuItems[0][3]) {//MenuBar comprador
			interacciones[0][0].setVisible(true);
			interacciones[0][0].setEnabled(true);
			interacciones[0][3].setVisible(true);
			interacciones[0][3].setEnabled(true);
			for(JTextField i:jtfsComprador) {	i.setEditable(true);};
			panelComprador.remove(imgs[0][1]);
			if(src==menuItems[0][0]) {
				interacciones[0][3].setVisible(false);
				interacciones[0][3].setEnabled(false);
				interacciones[0][0].setText("Agregar");
				interacciones[0][0].setToolTipText("Agrega un nuevo comprador a la tabla");
				lblOpComprador.setText("Altas");
				imgs[0][1]=new JLabel(new ImageIcon(imgAlta));
			}else if(src==menuItems[0][1]) {
				metodoQueRestableceTODO(jtfsComprador);
				for(JTextField i:jtfsComprador) {	i.setEditable(false);};
				jtfsComprador[0].setEditable(true);
				interacciones[0][0].setText("Eliminar");
				interacciones[0][0].setToolTipText("Elimina el comprador de la tabla");
				lblOpComprador.setText("Bajas");
				imgs[0][1]=new JLabel(new ImageIcon(imgBaja));
			}else if(src==menuItems[0][2]) {
				interacciones[0][0].setText("Modificar");
				interacciones[0][0].setToolTipText("Modifica el comprador seleccionado");
				lblOpComprador.setText("Cambios");
				imgs[0][1]=new JLabel(new ImageIcon(imgCambio));
			}else if(src==menuItems[0][3]) {
				interacciones[0][0].setVisible(false);
				interacciones[0][0].setEnabled(false);
				lblOpComprador.setText("Consultas");
				imgs[0][1]=new JLabel(new ImageIcon(imgConsulta));
			}
			actualizarTablaComprador("SELECT * FROM Comprador");
			if (frameComprador!=lastOpent) {
				frameComprador.setBounds(x, y, 785, 550);
				x+=plusX;
				y+=plusY;
			}
			imgs[0][1].setBounds(subIconos);
			panelComprador.add(imgs[0][1]);
			panelComprador.remove(imgs[0][0]);
			panelComprador.add(imgs[0][0]);
			frameComprador.toFront();
			frameComprador.setVisible(true);
			lastOpent=frameComprador;
		}else if(src==menuItems[1][0]||src==menuItems[1][1]||src==menuItems[1][2]||src==menuItems[1][3]) {//MenuBar contratista
			interacciones[1][0].setVisible(true);
			interacciones[1][0].setEnabled(true);
			interacciones[1][3].setVisible(true);
			interacciones[1][3].setEnabled(true);
			for(JTextField i:jtfsContratista) {	i.setEditable(true);};
			panelContratista.remove(imgs[1][1]);
			if(src==menuItems[1][0]) {
				interacciones[1][3].setVisible(false);
				interacciones[1][3].setEnabled(false);
				interacciones[1][0].setText("Agregar");
				interacciones[1][0].setToolTipText("Agrega un nuevo contratista a la tabla");
				lblOpContratista.setText("Altas");
				imgs[1][1]=new JLabel(new ImageIcon(imgAlta));
			}else if(src==menuItems[1][1]) {
				metodoQueRestableceTODO(jtfsContratista);
				for(JTextField i:jtfsContratista) {	i.setEditable(false);};
				jtfsContratista[0].setEditable(true);
				interacciones[1][0].setText("Eliminar");
				interacciones[1][0].setToolTipText("Elimina el contratista de la tabla");
				lblOpContratista.setText("Bajas");
				imgs[1][1]=new JLabel(new ImageIcon(imgBaja));
			}else if(src==menuItems[1][2]) {
				interacciones[1][0].setText("Modificar");
				interacciones[1][0].setToolTipText("Modifica el contratista seleccionado");
				lblOpContratista.setText("Cambios");
				imgs[1][1]=new JLabel(new ImageIcon(imgCambio));
			}else if(src==menuItems[1][3]) {
				interacciones[1][0].setVisible(false);
				interacciones[1][0].setEnabled(false);
				lblOpContratista.setText("Consultas");
				imgs[1][1]=new JLabel(new ImageIcon(imgConsulta));
			}
			actualizarTablaContratista("SELECT * FROM Contratista");
			if (frameContratista!=lastOpent) {
				frameContratista.setBounds(x, y, 850, 495);
				x+=plusX;
				y+=plusY;
			}
			imgs[1][1].setBounds(subIconos);
			panelContratista.add(imgs[1][1]);
			panelContratista.remove(imgs[1][0]);
			panelContratista.add(imgs[1][0]);
			frameContratista.toFront();
			frameContratista.setVisible(true);
			lastOpent=frameContratista;
		}else if(src==menuItems[2][0]||src==menuItems[2][1]||src==menuItems[2][2]||src==menuItems[2][3]) {//MenuBar criptomoneda
			interacciones[2][0].setVisible(true);
			interacciones[2][0].setEnabled(true);
			interacciones[2][3].setVisible(true);
			interacciones[2][3].setEnabled(true);
			for(JTextField i:jtfsCriptomoneda) {	i.setEditable(true);};
			panelCriptomoneda.remove(imgs[2][1]);
			if(src==menuItems[2][0]) {
				interacciones[2][3].setVisible(false);
				interacciones[2][3].setEnabled(false);
				interacciones[2][0].setText("Agregar");
				interacciones[2][0].setToolTipText("Agrega una nueva criptomoneda a la tabla");
				lblOpCriptomoneda.setText("Altas");
				imgs[2][1]=new JLabel(new ImageIcon(imgAlta));
			}else if(src==menuItems[2][1]) {
				metodoQueRestableceTODO(jtfsCriptomoneda);
				for(JTextField i:jtfsCriptomoneda) {	i.setEditable(false);};
				jtfsCriptomoneda[0].setEditable(true);
				interacciones[2][0].setText("Eliminar");
				interacciones[2][0].setToolTipText("Elimina la criptomoneda de la tabla");
				lblOpCriptomoneda.setText("Bajas");
				imgs[2][1]=new JLabel(new ImageIcon(imgBaja));
			}else if(src==menuItems[2][2]) {
				interacciones[2][0].setText("Modificar");
				interacciones[2][0].setToolTipText("Modifica la criptomoneda seleccionada");
				lblOpCriptomoneda.setText("Cambios");
				imgs[2][1]=new JLabel(new ImageIcon(imgCambio));
			}else if(src==menuItems[2][3]) {
				interacciones[2][0].setVisible(false);
				interacciones[2][0].setEnabled(false);
				lblOpCriptomoneda.setText("Consultas");
				imgs[2][1]=new JLabel(new ImageIcon(imgConsulta));
			}
			actualizarTablaCriptomoneda("SELECT * FROM Criptomoneda");
			if (frameCriptomoneda!=lastOpent) {
				frameCriptomoneda.setBounds(x, y, 800, 495);
				x+=plusX;
				y+=plusY;
			}
			imgs[2][1].setBounds(subIconos);
			panelCriptomoneda.add(imgs[2][1]);
			panelCriptomoneda.remove(imgs[2][0]);
			panelCriptomoneda.add(imgs[2][0]);
			frameCriptomoneda.toFront();
			frameCriptomoneda.setVisible(true);
			lastOpent = frameCriptomoneda;
		}else if(src==menuItems[3][0]||src==menuItems[3][1]||src==menuItems[3][2]||src==menuItems[3][3]) {//MenuBar pool
			interacciones[3][0].setVisible(true);
			interacciones[3][0].setEnabled(true);
			interacciones[3][3].setVisible(true);
			interacciones[3][3].setEnabled(true);
			for(JTextField i:jtfsPool) {	i.setEditable(true);};
			panelPool.remove(imgs[3][1]);
			if(src==menuItems[3][0]) {
				interacciones[3][3].setVisible(false);
				interacciones[3][3].setEnabled(false);
				interacciones[3][0].setText("Agregar");
				interacciones[3][0].setToolTipText("Agrega una nueva pool a la tabla");
				lblOpPool.setText("Altas");
				imgs[3][1]=new JLabel(new ImageIcon(imgAlta));
			}else if(src==menuItems[3][1]) {
				metodoQueRestableceTODO(jtfsPool);
				for(JTextField i:jtfsPool) {	i.setEditable(false);};
				jtfsPool[0].setEditable(true);
				interacciones[3][0].setText("Eliminar");
				interacciones[3][0].setToolTipText("Elimina la pool de la tabla");
				lblOpPool.setText("Bajas");
				imgs[3][1]=new JLabel(new ImageIcon(imgBaja));
			}else if(src==menuItems[3][2]) {
				interacciones[3][0].setText("Modificar");
				interacciones[3][0].setToolTipText("Modifica la pool seleccionada");
				lblOpPool.setText("Cambios");
				imgs[3][1]=new JLabel(new ImageIcon(imgCambio));
			}else if(src==menuItems[3][3]) {
				interacciones[3][0].setVisible(false);
				interacciones[3][0].setEnabled(false);
				lblOpPool.setText("Consultas");
				imgs[3][1]=new JLabel(new ImageIcon(imgConsulta));
			}
			actualizarTablaPool("SELECT * FROM Pool");
			if (framePool!=lastOpent) {
				framePool.setBounds(x, y, 750, 495);
				x+=plusX;
				y+=plusY;
			}
			imgs[3][1].setBounds(subIconos);
			panelPool.add(imgs[3][1]);
			panelPool.remove(imgs[3][0]);
			panelPool.add(imgs[3][0]);
			framePool.toFront();
			framePool.setVisible(true);
			lastOpent=framePool;
		}else if(src==menuItems[4][0]||src==menuItems[4][1]||src==menuItems[4][2]||src==menuItems[4][3]) {//MenuBar orden
			interacciones[4][0].setVisible(true);
			interacciones[4][0].setEnabled(true);
			interacciones[4][3].setVisible(true);
			interacciones[4][3].setEnabled(true);
			for(JTextField i:jtfsOrden) {	i.setEditable(true);};
			comboCompradorIdOrden.setEnabled(true);
			panelOrden.remove(imgs[4][1]);
			if(src==menuItems[4][0]) {
				interacciones[4][3].setVisible(false);
				interacciones[4][3].setEnabled(false);
				interacciones[4][0].setText("Agregar");
				interacciones[4][0].setToolTipText("Agrega una nueva orden a la tabla");
				lblOpOrden.setText("Altas");
				imgs[4][1]=new JLabel(new ImageIcon(imgAlta));
			}else if(src==menuItems[4][1]) {
				metodoQueRestableceTODO(jtfsOrden);
				for(JTextField i:jtfsOrden) {	i.setEditable(false);};
				jtfsOrden[0].setEditable(true);
				comboCompradorIdOrden.setSelectedIndex(-1);
				comboCompradorIdOrden.setEnabled(false);
				interacciones[4][0].setText("Eliminar");
				interacciones[4][0].setToolTipText("Elimina la orden de la tabla");
				lblOpOrden.setText("Bajas");
				imgs[4][1]=new JLabel(new ImageIcon(imgBaja));
			}else if(src==menuItems[4][2]) {
				interacciones[4][0].setText("Modificar");
				interacciones[4][0].setToolTipText("Modifica la orden seleccionada");
				lblOpOrden.setText("Cambios");
				imgs[4][1]=new JLabel(new ImageIcon(imgCambio));
			}else if(src==menuItems[4][3]) {
				interacciones[4][0].setVisible(false);
				interacciones[4][0].setEnabled(false);
				lblOpOrden.setText("Consultas");
				imgs[4][1]=new JLabel(new ImageIcon(imgConsulta));
			}
			actualizarTablaOrden("SELECT * FROM Orden");
			if (frameOrden!=lastOpent) {
				frameOrden.setBounds(x, y, 750, 495);
				x+=plusX;
				y+=plusY;
			}
			imgs[4][1].setBounds(subIconos);
			panelOrden.add(imgs[4][1]);
			panelOrden.remove(imgs[4][0]);
			panelOrden.add(imgs[4][0]);
			frameOrden.toFront();
			frameOrden.setVisible(true);
			lastOpent=frameOrden;
		}else if(src==menuItems[5][0]||src==menuItems[5][1]||src==menuItems[5][2]||src==menuItems[5][3]) {//MenuBar orden de potencia
			interacciones[5][0].setVisible(true);
			interacciones[5][0].setEnabled(true);
			interacciones[5][3].setVisible(true);
			interacciones[5][3].setEnabled(true);
			for(JTextField i:jtfsOrdenDePotencia) {	i.setEditable(true);};
			comboOrdenIdOrdenDePotencia.setEnabled(true);
			comboCriptomonedaIdOrdenDePotencia.setEnabled(true);
			comboContratistaIdOrdenDePotencia.setEnabled(true);
			comboPoolIdOrdenDePotencia.setEnabled(true);
			panelOrdenDePotencia.remove(imgs[5][1]);
			if(src==menuItems[5][0]) {
				interacciones[5][3].setVisible(false);
				interacciones[5][3].setEnabled(false);
				interacciones[5][0].setText("Agregar");
				interacciones[5][0].setToolTipText("Agrega una nueva orden de potencia a la tabla");
				lblOpOrdenDePotencia.setText("Altas");
				imgs[5][1]=new JLabel(new ImageIcon(imgAlta));
			}else if(src==menuItems[5][1]) {
				metodoQueRestableceTODO(jtfsOrdenDePotencia);
				for(JTextField i:jtfsOrdenDePotencia) {	i.setEditable(false);};
				jtfsOrdenDePotencia[0].setEditable(true);
				comboOrdenIdOrdenDePotencia.setSelectedIndex(-1);
				comboCriptomonedaIdOrdenDePotencia.setSelectedIndex(-1);
				comboContratistaIdOrdenDePotencia.setSelectedIndex(-1);
				comboPoolIdOrdenDePotencia.setSelectedIndex(-1);
				comboOrdenIdOrdenDePotencia.setEnabled(false);
				comboCriptomonedaIdOrdenDePotencia.setEnabled(false);
				comboContratistaIdOrdenDePotencia.setEnabled(false);
				comboPoolIdOrdenDePotencia.setEnabled(false);
				interacciones[5][0].setText("Eliminar");
				interacciones[5][0].setToolTipText("Elimina la orden de potencia de la tabla");
				lblOpOrdenDePotencia.setText("Bajas");
				imgs[5][1]=new JLabel(new ImageIcon(imgBaja));
			}else if(src==menuItems[5][2]) {
				interacciones[5][0].setText("Modificar");
				interacciones[5][0].setToolTipText("Modifica la orden de potencia seleccionada");
				lblOpOrdenDePotencia.setText("Cambios");
				imgs[5][1]=new JLabel(new ImageIcon(imgCambio));
			}else if(src==menuItems[5][3]) {
				interacciones[5][0].setVisible(false);
				interacciones[5][0].setEnabled(false);
				lblOpOrdenDePotencia.setText("Consultas");
				imgs[5][1]=new JLabel(new ImageIcon(imgConsulta));
			}
			actualizarTablaOrdenDePotencia("SELECT * FROM OrdenDePotencia");
			if (frameOrdenDePotencia!=lastOpent) {
				frameOrdenDePotencia.setBounds(x, y, 750, 525);
				x+=plusX;
				y+=plusY;
			}
			imgs[5][1].setBounds(subIconos);
			panelOrdenDePotencia.add(imgs[5][1]);
			panelOrdenDePotencia.remove(imgs[5][0]);
			panelOrdenDePotencia.add(imgs[5][0]);
			frameOrdenDePotencia.toFront();
			frameOrdenDePotencia.setVisible(true);
			lastOpent=frameOrdenDePotencia;
		}
		
		
		if (x==300||y==300) { 
			x=0;
			y=0;
		}
		
		
		if (src==interacciones[0][0]) {
			switch (interacciones[0][0].getText()) {
			case "Agregar":
				int lleno=1;
				for(JTextField i:jtfsComprador) {	if (i.getText().equals("")) {	lleno*=0;}};
				if(!validate(jtfsComprador[7].getText())) {
					JOptionPane.showMessageDialog(null,"Email no válido");
				}else if (lleno==1) {
					Comprador comprador = new Comprador(Integer.parseInt(jtfsComprador[0].getText()),
							jtfsComprador[1].getText(),
							jtfsComprador[2].getText(),
							jtfsComprador[3].getText(),
							jtfsComprador[4].getText(),
							jtfsComprador[5].getText(),
							jtfsComprador[6].getText(),
							jtfsComprador[7].getText());
					try  {
						if (compradorDAO.insertarRegistro(comprador)) {
							JOptionPane.showMessageDialog(null,"Comprador agregado exitosamente");
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"No se pudo agregar el comprador, quizá ya hay uno con el mismo ID");
						}
				}else {	JOptionPane.showMessageDialog(null,"Falta uno o más datos para añadir un comprador");	}
				break;
			case "Eliminar":
				if (jtfsComprador[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID del comprador a eliminar");
				}else {
					if (compradorDAO.eliminarRegistro(Integer.parseInt(jtfsComprador[0].getText()))) {	JOptionPane.showMessageDialog(null,"Comprador eliminado exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo eliminar el comprador, quizá el mismo es llamado en otro tipo de registro");	}
				}
				break;
			case "Modificar":
				int vacio =0;
				boolean flags[]= new boolean[7];
				for (int i = 0; i < flags.length; i++) {
					flags[i]=!jtfsComprador[i+1].getText().equals("");
					if (flags[i]) {	vacio+=1;}
				}
				if (jtfsComprador[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID del comprador");
				}else if(flags[6]&&!validate(jtfsComprador[7].getText())) {	JOptionPane.showMessageDialog(null,"Email no válido");
				}else if(vacio==0){	JOptionPane.showMessageDialog(null,"No se está ingresando nada aparte del ID");
				}else{
					Comprador comprador = new Comprador(Integer.parseInt(jtfsComprador[0].getText()),
							jtfsComprador[1].getText(),
							jtfsComprador[2].getText(),
							jtfsComprador[3].getText(),
							jtfsComprador[4].getText(),
							jtfsComprador[5].getText(),
							jtfsComprador[6].getText(),
							jtfsComprador[7].getText());
					if (compradorDAO.modificarRegistro(comprador,flags)) {	JOptionPane.showMessageDialog(null,"Comprador modificado exitosamente");
					}else{	JOptionPane.showMessageDialog(null,"No se pudo modificar el comprador");	}
				}
				break;
			default:break;
			}
			
			actualizarTablaComprador("SELECT * FROM Comprador");
		}else if(src==interacciones[0][1]) {
			metodoQueRestableceTODO(jtfsComprador);
			actualizarTablaComprador("SELECT * FROM Comprador");
		}else if(src==interacciones[0][2]) {
			frameComprador.setVisible(false);
			panelComprador.setVisible(false);
		}else if(src==interacciones[0][3]) {
			String sql = consultaComprador();
			actualizarTablaComprador(sql);
		}
		
		if (src==interacciones[1][0]) {
			switch (interacciones[1][0].getText()) {
			case "Agregar":
				int lleno=1;
				for(JTextField i:jtfsContratista) {	if (i.getText().equals("")) {	lleno*=0;	}}
				if (lleno==1) {
					Contratista contratista = new Contratista(
							Integer.parseInt(jtfsContratista[0].getText()),
							jtfsContratista[1].getText(),
							Integer.parseInt(jtfsContratista[2].getText()));
					if (contratistaDAO.insertarRegistro(contratista)) {	JOptionPane.showMessageDialog(null,"Contratista agregado exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo agregar el contratista, quizá ya hay uno con el mismo ID");	}
				}else {	JOptionPane.showMessageDialog(null,"Falta uno o más datos para añadir un contratista");	}
				break;
			case "Eliminar":
				if (jtfsContratista[0].getText().equals("")) {	JOptionPane.showMessageDialog(null, "No se esta especificando el ID del contratista a eliminar");
				}else {
					if (contratistaDAO.eliminarRegistro(Integer.parseInt(jtfsContratista[0].getText()))) {	JOptionPane.showMessageDialog(null, "Contratista eliminado exitosamente");
					}else {	JOptionPane.showMessageDialog(null, "No se pudo eliminar el contratista, quizá el mismo es llamado en otro tipo de registro");}
				}
				break;
			case "Modificar":
				int vacio=0;
				boolean flags[]=new boolean[2];
				for (int i = 0; i < flags.length; i++) {
					flags[i]=!jtfsContratista[i+1].getText().equals("");
					if (flags[i]) {	vacio+=1;	}
				}
				
				if (jtfsContratista[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID del contratista");
				}else if (vacio==0) {	JOptionPane.showMessageDialog(null,"No se está ingresando nada aparte del ID");
				}else {
					Contratista contratista = new Contratista(
							Integer.parseInt(jtfsContratista[0].getText()),
							jtfsContratista[1].getText(),
							jtfsContratista[2].getText().equals("")?0:Integer.parseInt(jtfsContratista[2].getText()));
					if (contratistaDAO.modificarRegistro(contratista, flags)) {	JOptionPane.showMessageDialog(null, "Contratista modificado exitosamente");
					}else {	JOptionPane.showMessageDialog(null, "No se pudo modificar el contratista");	}
				}
				
				break;
			default:break;
			}
			actualizarTablaContratista("SELECT * FROM Contratista");
		}else if(src==interacciones[1][1]) {
			metodoQueRestableceTODO(jtfsContratista);
			actualizarTablaContratista("SELECT * FROM Contratista");
		}else if(src==interacciones[1][2]) {
			frameContratista.setVisible(false);
			panelContratista.setVisible(false);
		}else if(src==interacciones[1][3]) {
			String sql = consultaContratista();
			actualizarTablaContratista(sql);
		}
		
		if (src==interacciones[2][0]) {
			switch (interacciones[2][0].getText()) {
			case "Agregar":
				int lleno=1;
				for (JTextField i:jtfsCriptomoneda) {	if (i.getText().equals("")) {	lleno*=0;}}
				if (lleno==1) {
					Criptomoneda criptomoneda = new Criptomoneda(
							jtfsCriptomoneda[0].getText(),
							Double.parseDouble(jtfsCriptomoneda[1].getText()), 
							jtfsCriptomoneda[2].getText());
					if (criptomonedaDAO.insertarRegistro(criptomoneda)) {	JOptionPane.showMessageDialog(null, "Criptomoneda agregada exitosamente");
					}else {	JOptionPane.showMessageDialog(null, "No se pudo agregar la criptomoneda, quizá ya hay una con el mismo ID");	}
				}else {	JOptionPane.showMessageDialog(null,"Falta uno o más datos para añadir una criptomoneda");	}
				break;
			case "Eliminar":
				if (jtfsCriptomoneda[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID de la criptomoneda a eliminar");
				}else {
					if (criptomonedaDAO.eliminarRegistro(jtfsCriptomoneda[0].getText())) {	JOptionPane.showMessageDialog(null,"Criptomoneda eliminada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo eliminar la criptomoneda, quizá la misma es llamada en otro tipo de registro ");}
				}
				
				break;
			case "Modificar":
				int vacio=0;
				boolean flags[]=new boolean[2];
				for (int i = 0; i < flags.length; i++) {
					flags[i]=!jtfsCriptomoneda[i+1].getText().equals("");
					if (flags[i]) {	vacio+=1;}
				}
				if (jtfsCriptomoneda[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID de la criptomoneda");
				}else if (vacio==0) {	JOptionPane.showMessageDialog(null,"No se está ingresando nada aparte del ID");
				}else {
					Criptomoneda criptomoneda = new Criptomoneda(
							jtfsCriptomoneda[0].getText(),
							jtfsCriptomoneda[1].getText().equals("")?0:Double.parseDouble(jtfsCriptomoneda[1].getText()), 
							jtfsCriptomoneda[2].getText());
					if (criptomonedaDAO.modificarRegistro(criptomoneda, flags)) {	JOptionPane.showMessageDialog(null,"Criptomoneda modificada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo modificar la criptomoneda");}
				}
				break;
			default:break;
			}
			actualizarTablaCriptomoneda("SELECT * FROM Criptomoneda");
		}else if(src==interacciones[2][1]) {
			metodoQueRestableceTODO(jtfsCriptomoneda);
			actualizarTablaCriptomoneda("SELECT * FROM Criptomoneda");
		}else if(src==interacciones[2][2]) {
			frameCriptomoneda.setVisible(false);
			panelCriptomoneda.setVisible(false);
		}else if(src==interacciones[2][3]) {
			String sql = consultaCriptomoneda();
			actualizarTablaCriptomoneda(sql);
		}
		
		if (src==interacciones[3][0]) {
			switch (interacciones[3][0].getText()) {
			case "Agregar":
				int lleno=1;
				for(JTextField i:jtfsPool) {	if (i.getText().equals("")) {lleno*=0;}};
				if (lleno==1) {
					Pool pool = new Pool(
							jtfsPool[0].getText(),
							Long.parseLong(jtfsPool[1].getText()),
							Integer.parseInt(jtfsPool[2].getText()),
							Integer.parseInt(jtfsPool[3].getText()));
					if (poolDAO.insertarRegistro(pool)) {	JOptionPane.showMessageDialog(null,"Pool agregada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo agregar la pool, quizá ya hay una con el mismo ID");}
				}else {JOptionPane.showMessageDialog(null,"Falta uno o más datos para añadir una pool");}
				break;
			case "Eliminar":
				if (jtfsPool[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID de la pool a eliminar");
				}else {
					if (poolDAO.eliminarRegistro(jtfsPool[0].getText())) {	JOptionPane.showMessageDialog(null,"Pool eliminada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo eliminar la pool, quizá la misma es llamada en otro tipo de registro ");}
				}
				break;
			case "Modificar":
				int vacio=0;
				boolean flags[]=new boolean[3];
				for (int i = 0; i < flags.length; i++) {
					flags[i]=!jtfsPool[i+1].getText().equals("");
					if (flags[i]) {	vacio+=1;}
				}
				if (jtfsPool[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID de la pool");
				}else if (vacio==0) {	JOptionPane.showMessageDialog(null,"No se está ingresando nada aparte del ID");
				}else {
					Pool pool = new Pool(
							jtfsPool[0].getText(),
							jtfsPool[1].getText().equals("")?0:Long.parseLong(jtfsPool[1].getText()),
							jtfsPool[2].getText().equals("")?0:Integer.parseInt(jtfsPool[2].getText()),
							jtfsPool[3].getText().equals("")?0:Integer.parseInt(jtfsPool[3].getText()));
					if (poolDAO.modificarRegistro(pool, flags)) {	JOptionPane.showMessageDialog(null,"Pool modificada exitosamente");
					}else{	JOptionPane.showMessageDialog(null,"No se pudo modificar la pool, quizá la misma es llamada en otro tipo de registro");}
				}
				break;
			default:break;
			}
			actualizarTablaPool("SELECT * FROM Pool");
		}else if(src==interacciones[3][1]) {
			metodoQueRestableceTODO(jtfsPool);
			actualizarTablaPool("SELECT * FROM Pool");
		}else if(src==interacciones[3][2]) {
			framePool.setVisible(false);
			panelPool.setVisible(false);
		}else if(src==interacciones[3][3]) {
			String sql = consultaPool();
			actualizarTablaPool(sql);
		}
		
		if (src==interacciones[4][0]) {
			jtfsOrden[1].setText(comboFecha[0].getSelectedItem()+"-"+comboFecha[1].getSelectedItem()+"-"+comboFecha[2].getSelectedItem());
			System.out.println(jtfsOrden[1].getText());
			switch (interacciones[4][0].getText()) {
			case "Agregar":
				int lleno=1;
				for(JTextField i:jtfsOrden) {	if (i.getText().equals("")) {	lleno*=0;}};
				if (comboCompradorIdOrden.getSelectedIndex()==-1) {	lleno*=0;	}
				if (lleno==1) {
					Orden orden = new Orden(
							Long.parseLong(jtfsOrden[0].getText()), 
							jtfsOrden[1].getText(), 
							Integer.parseInt((String)comboCompradorIdOrden.getSelectedItem()), 
							Integer.parseInt(jtfsOrden[2].getText()));
					if (ordenDAO.insertarRegistro(orden)) {	JOptionPane.showMessageDialog(null,"Orden agregada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo agregar la orden, quizá ya hay una con el mismo ID");}
				}else {	JOptionPane.showMessageDialog(null,"Falta uno o más datos para añadir una orden");	}
				break;
			case "Eliminar":
				if (jtfsOrden[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID de la orden a eliminar");
				}else {
					if (ordenDAO.eliminarRegistro(Integer.parseInt(jtfsOrden[0].getText()))) {	JOptionPane.showMessageDialog(null,"Orden eliminada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo eliminar la orden, quizá la misma es llamada en otro tipo de registro ");	}
				}
				break;
			case "Modificar":
				int vacio =0;
				boolean flags[]=new boolean[3];
				flags[0]=!jtfsOrden[1].getText().equals("");
				flags[1]=comboCompradorIdOrden.getSelectedIndex()!=-1;
				flags[2]=!jtfsOrden[2].getText().equals("");
				for (boolean i:flags) {	if (i) {	vacio+=1;}}
				if (jtfsOrden[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID de la orden");
				}else if(vacio==0){	JOptionPane.showMessageDialog(null,"No se está ingresando nada aparte del ID");
				}else {
					Orden orden = new Orden(
							Long.parseLong(jtfsOrden[0].getText()),
							jtfsOrden[1].getText(),
							(String)comboCompradorIdOrden.getSelectedItem()==""?0:Integer.parseInt((String)comboCompradorIdOrden.getSelectedItem()),
							jtfsOrden[2].getText().equals("")?0:Integer.parseInt(jtfsOrden[2].getText()));
					if (ordenDAO.modificarRegistro(orden, flags)) {	JOptionPane.showMessageDialog(null,"Comprador modificado exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo modificar la orden, quizá la misma es llamada en otro tipo de registro");}
				}
				break;
			default:break;
			}
			actualizarTablaOrden("SELECT * FROM Orden");
		}else if(src==interacciones[4][1]) {
			metodoQueRestableceTODO(jtfsOrden);
			metodoQueRestableceTODO(comboCompradorIdOrden);
			actualizarTablaOrden("SELECT * FROM Orden");
		}else if(src==interacciones[4][2]) {
			frameOrden.setVisible(false);
			panelOrden.setVisible(false);
		}else if(src==interacciones[4][3]) {
			String sql = consultaOrden();
			actualizarTablaOrden(sql);
		}
		
		if (src==interacciones[5][0]) {
			switch (interacciones[5][0].getText()) {
			case "Agregar":
				int lleno = 1;
				for (JTextField i:jtfsOrdenDePotencia) {	if (i.getText().equals("")) {	lleno*=0;}}
				if (	comboOrdenIdOrdenDePotencia.getSelectedIndex()==-1
						||comboCriptomonedaIdOrdenDePotencia.getSelectedIndex()==-1
						||comboContratistaIdOrdenDePotencia.getSelectedIndex()==-1
						||comboPoolIdOrdenDePotencia.getSelectedIndex()==-1) {
					lleno*=0;
				}
				if (lleno==1) {
					OrdenDePotencia ordenDePotencia= new OrdenDePotencia(
							Long.parseLong(jtfsOrdenDePotencia[0].getText()),
							Long.parseLong((String)comboOrdenIdOrdenDePotencia.getSelectedItem()),
							(String)comboCriptomonedaIdOrdenDePotencia.getSelectedItem(),
							Integer.parseInt((String)comboContratistaIdOrdenDePotencia.getSelectedItem()),
							(String)comboPoolIdOrdenDePotencia.getSelectedItem(),
							Double.parseDouble(jtfsOrdenDePotencia[1].getText()),
							Double.parseDouble(jtfsOrdenDePotencia[2].getText()));
					if (ordenDePotenciaDAO.insertarRegistro(ordenDePotencia)) {	JOptionPane.showMessageDialog(null,"Orden de potencia agregada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo agregar la orden de potencia, quizá ya hay una con el mismo ID");}
				}else {	JOptionPane.showMessageDialog(null,"Falta uno o más datos para añadir una orden de potencia");}
				
				break;
			case "Eliminar":
				if (jtfsOrdenDePotencia[0].getText().equals("")) {JOptionPane.showMessageDialog(null,"No se está especificando el ID de la orden de potencia a eliminar");
				}else {
					if (ordenDePotenciaDAO.eliminarRegistro(Long.parseLong(jtfsOrdenDePotencia[0].getText()))) {	JOptionPane.showMessageDialog(null,"Orden de potencia eliminada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo eliminar la orden de potencia, quizá la misma es llamada en otro tipo de registro ");}
				}
				break;
			case "Modificar":
				int vacio=0;
				boolean flags[]=new boolean[6];
				flags[0]=comboOrdenIdOrdenDePotencia.getSelectedIndex()!=-1;
				flags[1]=comboCriptomonedaIdOrdenDePotencia.getSelectedIndex()!=-1;
				flags[2]=comboContratistaIdOrdenDePotencia.getSelectedIndex()!=-1;
				flags[3]=comboPoolIdOrdenDePotencia.getSelectedIndex()!=-1;
				flags[4]=!jtfsOrdenDePotencia[1].getText().equals("");
				flags[5]=!jtfsOrdenDePotencia[2].getText().equals("");
				for (boolean i:flags) {	if (i) {	vacio+=1;}}
				if (jtfsOrdenDePotencia[0].getText().equals("")) {	JOptionPane.showMessageDialog(null,"No se está especificando el ID de la orden de potencia");
				}else if (vacio==0) {	JOptionPane.showMessageDialog(null,"No se está ingresando nada aparte del ID");
				}else {
					OrdenDePotencia ordenDePotencia= new OrdenDePotencia(
							jtfsOrdenDePotencia[0].getText().equals("")?0:Long.parseLong(jtfsOrdenDePotencia[0].getText()),
							(String)comboOrdenIdOrdenDePotencia.getSelectedItem()==""?0:Long.parseLong((String)comboOrdenIdOrdenDePotencia.getSelectedItem()),
							(String)comboCriptomonedaIdOrdenDePotencia.getSelectedItem(),
							(String)comboContratistaIdOrdenDePotencia.getSelectedItem()==""?0:Integer.parseInt((String)comboContratistaIdOrdenDePotencia.getSelectedItem()),
							(String)comboPoolIdOrdenDePotencia.getSelectedItem(),
							jtfsOrdenDePotencia[1].getText().equals("")?0:Double.parseDouble(jtfsOrdenDePotencia[1].getText()),
							jtfsOrdenDePotencia[2].getText().equals("")?0:Double.parseDouble(jtfsOrdenDePotencia[2].getText()));
					if (ordenDePotenciaDAO.modificarRegistro(ordenDePotencia, flags)) {	JOptionPane.showMessageDialog(null,"Orden de potencia modificada exitosamente");
					}else {	JOptionPane.showMessageDialog(null,"No se pudo modificar la orden de potencia");}
				}
				
				break;
			default:break;
			}
			actualizarTablaOrdenDePotencia("SELECT * FROM OrdenDePotencia");
		}else if(src==interacciones[5][1]) {
			metodoQueRestableceTODO(jtfsOrdenDePotencia);
			metodoQueRestableceTODO(comboOrdenIdOrdenDePotencia,comboCriptomonedaIdOrdenDePotencia,comboContratistaIdOrdenDePotencia,comboPoolIdOrdenDePotencia);
			actualizarTablaOrdenDePotencia("SELECT * FROM OrdenDePotencia");
		}else if(src==interacciones[5][2]) {
			frameOrdenDePotencia.setVisible(false);
			panelOrdenDePotencia.setVisible(false);
		}else if(src==interacciones[5][3]) {
			String sql = consultaOrdenDePotencia();
			System.out.println(sql);
			actualizarTablaOrdenDePotencia(sql);
		}
		
		for (int i = 0; i < interacciones.length; i++) {
			for (int j = 0; j < interacciones[i].length; j++) {
				if ((src==menuItems[i][j])||(src==interacciones[i][0]&&(interacciones[i][0].getText().equals("Agregar")||interacciones[i][0].getText().equals("Eliminar")))) {
					comboCompradorIdOrden.removeAllItems();//debe ir hasta el FINAL
					comboOrdenIdOrdenDePotencia.removeAllItems();
					comboCriptomonedaIdOrdenDePotencia.removeAllItems();
					comboContratistaIdOrdenDePotencia.removeAllItems();
					comboPoolIdOrdenDePotencia.removeAllItems();
					ArrayList<Comprador> compradores = compradorDAO.buscarCompradores("SELECT * FROM Comprador");
					ArrayList<Orden> ordenes = ordenDAO.buscarOrdenes("SELECT * FROM Orden");
					ArrayList<Criptomoneda> criptomonedas = criptomonedaDAO.buscarCriptomonedas("SELECT * FROM Criptomoneda");
					ArrayList<Contratista> contratistas = contratistaDAO.buscarContratistas("SELECT * FROM Contratista");
					ArrayList<Pool> pools = poolDAO.buscarPools("SELECT * FROM Pool");
					for(Comprador k:compradores) {	comboCompradorIdOrden.addItem(""+k.getCompradorId());}
					for(Orden k:ordenes) {	comboOrdenIdOrdenDePotencia.addItem(""+k.getOrdenId());}
					for(Criptomoneda k:criptomonedas) {	comboCriptomonedaIdOrdenDePotencia.addItem(""+k.getCriptomonedaId());}
					for(Contratista k:contratistas) {	comboContratistaIdOrdenDePotencia.addItem(""+k.getContratistaId());}
					for(Pool k:pools) {	comboPoolIdOrdenDePotencia.addItem(""+k.getPoolId());}//debe ir hasta el FINAL
					comboCompradorIdOrden.setSelectedIndex(-1);
					comboOrdenIdOrdenDePotencia.setSelectedIndex(-1);
					comboCriptomonedaIdOrdenDePotencia.setSelectedIndex(-1);
					comboContratistaIdOrdenDePotencia.setSelectedIndex(-1);
					comboPoolIdOrdenDePotencia.setSelectedIndex(-1);
				
				}
			}
		}
		
	}
	
}

class Login extends JFrame implements ActionListener{
	ConexionBD conexion = ConexionBD.getInstance();
	UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
	JLabel lblUsuario = new JLabel("Usuario");
	JLabel lblContraseña = new JLabel("Contraseña");
	JTextField jtfUsuario = new JTextField();
	JPasswordField jpfContraseña = new JPasswordField();
	JButton ingresar = new JButton("Ingresar");
	BufferedImage image = ImageIO.read(new File("./archivos/usuario.PNG"));
    JLabel label = new JLabel(new ImageIcon(image));
	
	public Login() throws IOException {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,430);
		setLocationRelativeTo(null);
		setTitle("Login");
		setVisible(true);
	 
		label.setBounds(75,35,150,150);
		lblUsuario.setBounds(50,200,200,20);
		jtfUsuario.setBounds(50,230,200,20);
		lblContraseña.setBounds(50,260,200,20);
		jpfContraseña.setBounds(50,290,200,20);
		ingresar.setBounds(100,320,100,20);
		
		ingresar.setBackground(Color.RED);
		ingresar.setForeground(Color.WHITE);
		ingresar.addActionListener(this);
		
		add(label);
		add(lblUsuario);
		add(jtfUsuario);
		add(lblContraseña);
		add(jpfContraseña);
		add(ingresar);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (verificar()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new Interfaz();
				}
			});
			setVisible(false);
		}
	}
	
	public boolean verificar() {
		try {
			ArrayList<Usuario> listaUsuarios = usuarioDAO.buscarUsuarios("SELECT * FROM Usuario WHERE nombre = '"+jtfUsuario.getText()+"'");
			Usuario usuario = listaUsuarios.get(0);
			return usuario.getContraseña().equals(jpfContraseña.getText());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
}

public class VentanaInicio {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Login();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}