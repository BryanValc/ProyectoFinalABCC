package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.AllPermission;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.border.Border;

import controlador.*;
import modelo.*;



class Interfaz extends JFrame implements ActionListener, ItemListener{
	int x=0;
	int y=0;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu comprador = new JMenu("Comprador");
	JMenu contratista = new JMenu("Contratista");
	JMenu criptomoneda = new JMenu("Criptomoneda");
	JMenu pool = new JMenu("Pool");
	JMenu orden = new JMenu("Orden");
	JMenu ordenDePotencia = new JMenu("Orden de potencia");
	JMenuItem menuItems[][]=new JMenuItem[6][4];
	
	JInternalFrame frameComprador=new JInternalFrame();
	JInternalFrame frameContratista=new JInternalFrame();
	JInternalFrame frameCriptomoneda=new JInternalFrame();
	JInternalFrame framePool=new JInternalFrame();
	JInternalFrame frameOrden=new JInternalFrame();
	JInternalFrame frameOrdenDePotencia=new JInternalFrame();
	JPanel panelComprador = new JPanel();
	JPanel panelContratista = new JPanel();
	JPanel panelCriptomoneda = new JPanel();
	JPanel panelPool = new JPanel();
	JPanel panelOrden = new JPanel();
	JPanel panelOrdenDePotencia = new JPanel();
	
	JDesktopPane dp = new JDesktopPane();
	
	JLabel lblOpComprador = new JLabel("", SwingConstants.CENTER);
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
	
	JButton interacciones[][] = new JButton[6][4];
	
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
				if ((((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')||(ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z')||(code==KeyEvent.VK_SPACE)))&&jtfsComprador[1].getText().length()<150||(code==KeyEvent.VK_BACK_SPACE)) {
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
				if (jtfsComprador[3].getText().length()<100||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsComprador[3].setEditable(true);
				}else{
					jtfsComprador[3].setEditable(false);
				}
			}
		});
		jtfsComprador[4].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')||(ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z')||(code==KeyEvent.VK_SPACE)))&&jtfsComprador[4].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsComprador[4].setEditable(true);
				}else{
					jtfsComprador[4].setEditable(false);
				}
			}
		});
		jtfsComprador[5].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')||(ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z')||(code==KeyEvent.VK_SPACE)))&&jtfsComprador[5].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
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
				if (jtfsComprador[7].getText().length()<50||code==KeyEvent.VK_BACK_SPACE) {
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
				if ((((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')||(ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z')||(code==KeyEvent.VK_SPACE)))&&jtfsContratista[1].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
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
				if (jtfsCriptomoneda[0].getText().length()<20||(code==KeyEvent.VK_BACK_SPACE)) {
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
				if (jtfsCriptomoneda[2].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
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
				if (jtfsPool[0].getText().length()<10||code==KeyEvent.VK_BACK_SPACE) {
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
		jtfsOrden[1]=new JTextField();
		jtfsOrden[1].setBounds(400, 80, 150, 20);
		panelOrden.add(jtfsOrden[1]);
		comboCompradorIdOrden.setBounds(400, 110, 150, 20);
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
		panelOrdenDePotencia.add(comboOrdenIdOrdenDePotencia);
		comboCriptomonedaIdOrdenDePotencia.setBounds(430, 110, 150, 20);
		panelOrdenDePotencia.add(comboCriptomonedaIdOrdenDePotencia);
		comboContratistaIdOrdenDePotencia.setBounds(430, 140, 150, 20);
		panelOrdenDePotencia.add(comboContratistaIdOrdenDePotencia);
		comboPoolIdOrdenDePotencia.setBounds(430, 170, 150, 20);
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
		
		dp.setLocation(0, 0);
		dp.setSize(Toolkit. getDefaultToolkit(). getScreenSize());
		add(dp);
	}
	
	public String consultaComprador() {
		String sql = "SELECT * FROM Comprador ";
		boolean primero=true;
		if(!jtfsComprador[0].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("CompradorId="+jtfsComprador[0].getText());
		}
		if(!jtfsComprador[1].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Nombre='"+jtfsComprador[1].getText()+"'");
		}
		if(!jtfsComprador[2].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Wallet='"+jtfsComprador[2].getText()+"'");
		}
		if(!jtfsComprador[3].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Direccion='"+jtfsComprador[3].getText()+"'");
		}
		if(!jtfsComprador[4].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Ciudad='"+jtfsComprador[4].getText()+"'");
		}
		if(!jtfsComprador[5].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Estado='"+jtfsComprador[5].getText()+"'");
		}
		if(!jtfsComprador[6].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Telefono='"+jtfsComprador[6].getText()+"'");
		}
		if(!jtfsComprador[7].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("Email='"+jtfsComprador[7].getText()+"'");
		}
		return sql;
	}
	public String consultaContratista() {
		String sql = "SELECT * FROM Contratista ";
		boolean primero=true;
		if(!jtfsContratista[0].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("ContratistaId="+jtfsContratista[0].getText());
		}
		if(!jtfsContratista[1].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("NombreContratista='"+jtfsContratista[1].getText()+"'");
		}
		if(!jtfsContratista[2].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("MesesOperando="+jtfsContratista[2].getText());
		}
		return sql;
	}
	public String consultaCriptomoneda() {
		String sql = "SELECT * FROM Criptomoneda ";
		boolean primero=true;
		if(!jtfsCriptomoneda[0].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("CriptomonedaId='"+jtfsCriptomoneda[0].getText()+"'");
		}
		if(!jtfsCriptomoneda[1].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("PrecioUnidad="+jtfsCriptomoneda[1].getText());
		}
		if(!jtfsCriptomoneda[2].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("DescripcionUnidad='"+jtfsCriptomoneda[2].getText()+"'");
		}
		return sql;
	}
	public String consultaPool() {
		String sql = "SELECT * FROM Pool ";
		boolean primero=true;
		if(!jtfsPool[0].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("PoolId='"+jtfsPool[0].getText()+"'");
		}
		if(!jtfsPool[1].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("PotenciaDeMinadoMHs="+jtfsPool[1].getText());
		}
		if(!jtfsPool[2].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("CantidadDeTrabajadores="+jtfsPool[2].getText());
		}
		if(!jtfsPool[3].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("CantidadDeMineros="+jtfsPool[3].getText());
		}
		return sql;
	}
	public String consultaOrden() {
		String sql = "SELECT * FROM Orden ";
		boolean primero=true;
		if(!jtfsOrden[0].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("ordenId="+jtfsOrden[0].getText());
		}
		if(!jtfsOrden[1].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("fechaOrden='"+jtfsOrden[1].getText()+"'");
		}
		if(comboCompradorIdOrden.getSelectedIndex()!=-1) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("compradorId="+comboCompradorIdOrden.getSelectedItem());
		}
		if(!jtfsOrden[2].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("horasDeOperacion="+jtfsOrden[2].getText());
		}
		return sql;
	}
	public String consultaOrdenDePotencia() {
		String sql = "SELECT * FROM Orden ";
		boolean primero=true;
		if(!jtfsOrdenDePotencia[0].equals("")) {
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
		if(!jtfsOrdenDePotencia[1].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("cantidadDeCriptomonedas="+jtfsOrdenDePotencia[1].getText());
		}
		if(!jtfsOrdenDePotencia[2].equals("")) {
			if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
			primero=false;
			sql+=("precioFiat="+jtfsOrdenDePotencia[2].getText());
		}
		return sql;
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
		
		menuBar.add(comprador);
		menuBar.add(contratista);
		menuBar.add(criptomoneda);
		menuBar.add(pool);
		menuBar.add(orden);
		menuBar.add(ordenDePotencia);
		
		
		
	}
	
	public void panelYFrame(JPanel panel,JInternalFrame frame,Color color,String titulo) {
		panel.setLayout(null);
		panel.setBounds(0, 0, 1300, 800);
		panel.setBackground(color);
		frame.setBounds(0, 0, 1300, 800);
		frame.setTitle(titulo);
		frame.add(panel);
		frame.setResizable(true);
		frame.setMaximumSize(new Dimension(1300,800));
		dp.add(frame);
	}
	
	public void modificarYAñadirLabel(int x, int y, int width,int height,JLabel label,JPanel panel,Font font) {
		label.setBounds(x, y, width, height);
		label.setFont(font);
		panel.add(label);
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
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src =arg0.getSource();
		int plusX = 30;
		int plusY = 30;
		if (src==menuItems[0][0]||src==menuItems[0][1]||src==menuItems[0][2]||src==menuItems[0][3]) {
			interacciones[0][0].setVisible(true);
			interacciones[0][0].setEnabled(true);
			interacciones[0][3].setVisible(true);
			interacciones[0][3].setEnabled(true);
			if(src==menuItems[0][0]) {
				interacciones[0][3].setVisible(false);
				interacciones[0][3].setEnabled(false);
				interacciones[0][0].setText("Agregar");
				interacciones[0][0].setToolTipText("Agrega un nuevo comprador a la tabla");
				lblOpComprador.setText("Altas");
			}else if(src==menuItems[0][1]) {
				interacciones[0][0].setText("Eliminar");
				interacciones[0][0].setToolTipText("Elimina el comprador de la tabla");
				lblOpComprador.setText("Bajas");
			}else if(src==menuItems[0][2]) {
				interacciones[0][0].setText("Modificar");
				interacciones[0][0].setToolTipText("Modifica el comprador seleccionado");
				lblOpComprador.setText("Cambios");
			}else if(src==menuItems[0][3]) {
				interacciones[0][0].setVisible(false);
				interacciones[0][0].setEnabled(false);
				lblOpComprador.setText("Consultas");
			}
			frameComprador.setBounds(x, y, 1300, 800);
			frameComprador.toFront();
			frameComprador.setVisible(true);
		}else if(src==menuItems[1][0]||src==menuItems[1][1]||src==menuItems[1][2]||src==menuItems[1][3]) {
			interacciones[1][0].setVisible(true);
			interacciones[1][0].setEnabled(true);
			interacciones[1][3].setVisible(true);
			interacciones[1][3].setEnabled(true);
			if(src==menuItems[1][0]) {
				interacciones[1][3].setVisible(false);
				interacciones[1][3].setEnabled(false);
				interacciones[1][0].setText("Agregar");
				interacciones[1][0].setToolTipText("Agrega un nuevo contratista a la tabla");
				lblOpContratista.setText("Altas");
			}else if(src==menuItems[1][1]) {
				interacciones[1][0].setText("Eliminar");
				interacciones[1][0].setToolTipText("Elimina el contratista de la tabla");
				lblOpContratista.setText("Bajas");
			}else if(src==menuItems[1][2]) {
				interacciones[1][0].setText("Modificar");
				interacciones[1][0].setToolTipText("Modifica el contratista seleccionado");
				lblOpContratista.setText("Cambios");
			}else if(src==menuItems[1][3]) {
				interacciones[1][0].setVisible(false);
				interacciones[1][0].setEnabled(false);
				lblOpContratista.setText("Consultas");
			}
			frameContratista.setBounds(x, y, 1300, 800);
			frameContratista.toFront();
			frameContratista.setVisible(true);
		}else if(src==menuItems[2][0]||src==menuItems[2][1]||src==menuItems[2][2]||src==menuItems[2][3]) {
			interacciones[2][0].setVisible(true);
			interacciones[2][0].setEnabled(true);
			interacciones[2][3].setVisible(true);
			interacciones[2][3].setEnabled(true);
			if(src==menuItems[2][0]) {
				interacciones[2][3].setVisible(false);
				interacciones[2][3].setEnabled(false);
				interacciones[2][0].setText("Agregar");
				interacciones[2][0].setToolTipText("Agrega una nueva criptomoneda a la tabla");
				lblOpCriptomoneda.setText("Altas");
			}else if(src==menuItems[2][1]) {
				interacciones[2][0].setText("Eliminar");
				interacciones[2][0].setToolTipText("Elimina la criptomoneda de la tabla");
				lblOpCriptomoneda.setText("Bajas");
			}else if(src==menuItems[2][2]) {
				interacciones[2][0].setText("Modificar");
				interacciones[2][0].setToolTipText("Modifica la criptomoneda seleccionada");
				lblOpCriptomoneda.setText("Cambios");
			}else if(src==menuItems[2][3]) {
				interacciones[2][0].setVisible(false);
				interacciones[2][0].setEnabled(false);
				lblOpCriptomoneda.setText("Consultas");
			}
			frameCriptomoneda.setBounds(x, y, 1300, 800);
			frameCriptomoneda.toFront();
			frameCriptomoneda.setVisible(true);
		}else if(src==menuItems[3][0]||src==menuItems[3][1]||src==menuItems[3][2]||src==menuItems[3][3]) {
			interacciones[3][0].setVisible(true);
			interacciones[3][0].setEnabled(true);
			interacciones[3][3].setVisible(true);
			interacciones[3][3].setEnabled(true);
			if(src==menuItems[3][0]) {
				interacciones[3][3].setVisible(false);
				interacciones[3][3].setEnabled(false);
				interacciones[3][0].setText("Agregar");
				interacciones[3][0].setToolTipText("Agrega una nueva pool a la tabla");
				lblOpPool.setText("Altas");
			}else if(src==menuItems[3][1]) {
				interacciones[3][0].setText("Eliminar");
				interacciones[3][0].setToolTipText("Elimina la pool de la tabla");
				lblOpPool.setText("Bajas");
			}else if(src==menuItems[3][2]) {
				interacciones[3][0].setText("Modificar");
				interacciones[3][0].setToolTipText("Modifica la pool seleccionada");
				lblOpPool.setText("Cambios");
			}else if(src==menuItems[3][3]) {
				interacciones[3][0].setVisible(false);
				interacciones[3][0].setEnabled(false);
				lblOpPool.setText("Consultas");
			}
			framePool.setBounds(x, y, 1300, 800);
			framePool.toFront();
			framePool.setVisible(true);
		}else if(src==menuItems[4][0]||src==menuItems[4][1]||src==menuItems[4][2]||src==menuItems[4][3]) {
			interacciones[4][0].setVisible(true);
			interacciones[4][0].setEnabled(true);
			interacciones[4][3].setVisible(true);
			interacciones[4][3].setEnabled(true);
			if(src==menuItems[4][0]) {
				interacciones[4][3].setVisible(false);
				interacciones[4][3].setEnabled(false);
				interacciones[4][0].setText("Agregar");
				interacciones[4][0].setToolTipText("Agrega una nueva orden a la tabla");
				lblOpOrden.setText("Altas");
			}else if(src==menuItems[4][1]) {
				interacciones[4][0].setText("Eliminar");
				interacciones[4][0].setToolTipText("Elimina la orden de la tabla");
				lblOpOrden.setText("Bajas");
			}else if(src==menuItems[4][2]) {
				interacciones[4][0].setText("Modificar");
				interacciones[4][0].setToolTipText("Modifica la orden seleccionada");
				lblOpOrden.setText("Cambios");
			}else if(src==menuItems[4][3]) {
				interacciones[4][0].setVisible(false);
				interacciones[4][0].setEnabled(false);
				lblOpOrden.setText("Consultas");
			}
			frameOrden.setBounds(x, y, 1300, 800);
			frameOrden.toFront();
			frameOrden.setVisible(true);
		}else if(src==menuItems[5][0]||src==menuItems[5][1]||src==menuItems[5][2]||src==menuItems[5][3]) {
			interacciones[5][0].setVisible(true);
			interacciones[5][0].setEnabled(true);
			interacciones[5][3].setVisible(true);
			interacciones[5][3].setEnabled(true);
			if(src==menuItems[5][0]) {
				interacciones[5][3].setVisible(false);
				interacciones[5][3].setEnabled(false);
				interacciones[5][0].setText("Agregar");
				interacciones[5][0].setToolTipText("Agrega una nueva orden de potencia a la tabla");
				lblOpOrdenDePotencia.setText("Altas");
			}else if(src==menuItems[5][1]) {
				interacciones[5][0].setText("Eliminar");
				interacciones[5][0].setToolTipText("Elimina la orden de potencia de la tabla");
				lblOpOrdenDePotencia.setText("Bajas");
			}else if(src==menuItems[5][2]) {
				interacciones[5][0].setText("Modificar");
				interacciones[5][0].setToolTipText("Modifica la orden de potencia seleccionada");
				lblOpOrdenDePotencia.setText("Cambios");
			}else if(src==menuItems[5][3]) {
				interacciones[5][0].setVisible(false);
				interacciones[5][0].setEnabled(false);
				lblOpOrdenDePotencia.setText("Consultas");
			}
			frameOrdenDePotencia.setBounds(x, y, 1300, 800);
			frameOrdenDePotencia.toFront();
			frameOrdenDePotencia.setVisible(true);
		}
		
		
		x+=plusX;
		y+=plusY;
		if (x==300||y==300) {
			x=0;
			y=0;
		}
		
		comboCompradorIdOrden.removeAllItems();//debe ir hasta el FINAL
		comboOrdenIdOrdenDePotencia.removeAllItems();
		comboCriptomonedaIdOrdenDePotencia.removeAllItems();
		comboContratistaIdOrdenDePotencia.removeAllItems();
		comboPoolIdOrdenDePotencia.removeAllItems();
		CompradorDAO compradorDAO = new CompradorDAO();
		OrdenDAO ordenDAO = new OrdenDAO();
		CriptomonedaDAO criptomonedaDAO = new CriptomonedaDAO();
		ContratistaDAO contratistaDAO = new ContratistaDAO();
		PoolDAO poolDAO = new PoolDAO();
		ArrayList<Comprador> compradores = compradorDAO.buscarCompradores("SELECT * FROM Comprador");
		ArrayList<Orden> ordenes = ordenDAO.buscarOrdenes("SELECT * FROM Orden");
		ArrayList<Criptomoneda> criptomonedas = criptomonedaDAO.buscarCriptomonedas("SELECT * FROM Criptomoneda");
		ArrayList<Contratista> contratistas = contratistaDAO.buscarContratistas("SELECT * FROM Contratista");
		ArrayList<Pool> pools = poolDAO.buscarPools("SELECT * FROM Pool");
		for(Comprador i:compradores) {	comboCompradorIdOrden.addItem(""+i.getCompradorId());}
		for(Orden i:ordenes) {	comboOrdenIdOrdenDePotencia.addItem(""+i.getOrdenId());}
		for(Criptomoneda i:criptomonedas) {	comboCriptomonedaIdOrdenDePotencia.addItem(""+i.getCriptomonedaId());}
		for(Contratista i:contratistas) {	comboContratistaIdOrdenDePotencia.addItem(""+i.getContratistaId());}
		for(Pool i:pools) {	comboPoolIdOrdenDePotencia.addItem(""+i.getPoolId());}//debe ir hasta el FINAL
		
	}
	
}

public class VentanaInicio {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Interfaz();
			}
		});
		
		/*
		CompradorDAO compradorDAO = new CompradorDAO();
		Comprador comprador = new Comprador(1212, "Bryan", "1x1E1SA123123", "Arq Damaso", "Jerez", "Zacatecas", "494-118-9287", "bryan.valdez117@outlook.es");
		compradorDAO.insertarRegistro(comprador);
		compradorDAO.eliminarRegistro(1212);
		Comprador comprador2 = new Comprador(1212, "Juan", "1x1E1SA123123", "Arq Damaso", "Zacatecas", "Zacatecas", "494-118-9287", "bryan.valdez117@outlook.es");
		boolean flags[]=new boolean[7];
		flags[0]=true;
		compradorDAO.modificarRegistro(comprador2, flags);
		ArrayList<Comprador> compradores = compradorDAO.buscarCompradores("SELECT * FROM Comprador WHERE nombre = 'Bryan'");
		System.out.println(compradores);
		*/
		
		/*
		ContratistaDAO contratistaDAO = new ContratistaDAO();
		Contratista contratista = new Contratista(2121, "Bryan",1177);
		contratistaDAO.insertarRegistro(contratista);
		contratistaDAO.eliminarRegistro(2121);
		Contratista contratista2 = new Contratista(2121, "Leo",11);
		boolean flags[]=new boolean[2];
		flags[0]=true;
		contratistaDAO.modificarRegistro(contratista2, flags);
		ArrayList<Contratista> contratistas = contratistaDAO.buscarContratistas("SELECT * FROM Contratista WHERE nombreContratista = 'Leo'");
		System.out.println(contratistas);
		*/
		
		/*
		CriptomonedaDAO criptomonedaDAO = new CriptomonedaDAO();
		Criptomoneda criptomoneda = new Criptomoneda("SUSHI",16.36,"defi Token");
		criptomonedaDAO.insertarRegistro(criptomoneda);
		criptomonedaDAO.eliminarRegistro("SUSHI");
		boolean flags[]=new boolean[2];
		flags[0]=true;
		criptomoneda.setPrecioUnidad(17);
		criptomonedaDAO.modificarRegistro(criptomoneda, flags);
		ArrayList<Criptomoneda> criptomonedas = criptomonedaDAO.buscarCriptomonedas("SELECT * FROM Criptomoneda WHERE precioUnidad = 17");
		System.out.println(criptomonedas);
		*/
		
		/*
		OrdenDAO ordenDAO = new OrdenDAO();
		Orden orden = new Orden(9000000000000L,"08-May-2021",1212,5);
		ordenDAO.insertarRegistro(orden);
		ordenDAO.eliminarRegistro(9000000000000L);
		orden.setFechaOrden("08-Jun-2021");
		boolean flags[]=new boolean[3];
		flags[0]=true;
		ordenDAO.modificarRegistro(orden, flags);
		ArrayList<Orden> ordenes = ordenDAO.buscarOrdenes("SELECT * FROM Orden WHERE fechaOrden = '08-Jun-2021'");
		System.out.println(ordenes);
		*/
		
		/*
		PoolDAO poolDAO = new PoolDAO();
		Pool pool = new Pool("F2P", 70000000L, 32000, 16000);
		poolDAO.insertarRegistro(pool);
		poolDAO.eliminarRegistro("F2P");
		pool.setPotenciaDeMinadoMHs(160000000L);
		pool.setCantidadDeMineros(32000);
		boolean flags[]=new boolean[3];
		flags[0]=true;
		poolDAO.modificarRegistro(pool, flags);
		ArrayList<Pool> pools = poolDAO.buscarPools("SELECT * FROM Pool WHERE poolId = 'F2P'");
		System.out.println(pools);
		*/
		
		/*
		OrdenDePotencia odp = new OrdenDePotencia(12345678910111213L, 9000000000000L, "SUSHI", 2121, "F2P", 5, 80);
		OrdenDePotenciaDAO odpDAO = new OrdenDePotenciaDAO();
		odpDAO.insertarRegistro(odp);	
		odpDAO.eliminarRegistro(12345678910111213L);
		odp.setPrecioFiat(160);
		boolean flags[]=new boolean[6];
		flags[5]=true;
		odpDAO.modificarRegistro(odp, flags);
		ArrayList<OrdenDePotencia> ordenesDePotencia = odpDAO.buscarOrdenesDePotencia("SELECT * FROM OrdenDePotencia WHERE compraId = 12345678910111213");
		System.out.println(ordenesDePotencia);
		*/
	}

}
