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
	
	JLabel lblOpComprador = new JLabel("");
	JLabel lblOpContratista = new JLabel("");
	JLabel lblOpCriptomoneda = new JLabel("");
	JLabel lblOpPool = new JLabel("");
	JLabel lblOpOrden = new JLabel("");
	JLabel lblOpOrdenDePotencia = new JLabel("");
	
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
		
		modificarYAñadirLabel(25, 25, 250, 40,lblOpComprador,panelComprador,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(25, 25, 250, 40,lblOpContratista,panelContratista,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(25, 25, 250, 40,lblOpCriptomoneda,panelCriptomoneda,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(25, 25, 250, 40,lblOpPool,panelPool,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(25, 25, 250, 40,lblOpOrden,panelOrden,new Font("Calibri", Font.BOLD, 50));
		modificarYAñadirLabel(25, 25, 250, 40,lblOpOrdenDePotencia,panelOrdenDePotencia,new Font("Calibri", Font.BOLD, 50));
		
		String stringsComprador[]= {"ID Comprador","Nombre","Wallet","Dirección","Ciudad","Estado","Teléfono","Email"};
		String stringsContratista[]= {"ID Contratista","Nombre del contratista","Meses operando"};
		String stringsCriptomoneda[]= {"ID Criptomoneda","Precio Unitario","Descripciónn"};
		String stringsPool[]= {"ID Pool","Potencia MH/s","Cantidad de trabajadores","Cantidad de mineros"};
		String stringsOrden[]= {"ID Orden","Fecha de orden","ID Comprador","Horas de operación"};
		String stringsOrdenDePotencia[]= {"ID Comprador","Nombre","Wallet","Direccion","Ciudad","Estado","Telefono","Email"};
		
		for(int i=0;i<lblsComprador.length;i+=1) {
			lblsComprador[i]=new JLabel(stringsComprador[i]);
			lblsComprador[i].setBounds(250, 50+(i*30), 150, 20);
			jtfsComprador[i]=new JTextField();
			jtfsComprador[i].setBounds(400, 50+(i*30), 250, 20);
			panelComprador.add(lblsComprador[i]);
			panelComprador.add(jtfsComprador[i]);
		}
		jtfsComprador[0].addKeyListener(new KeyAdapter() {//validacion
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if (((ke.getKeyChar() >= '0'&&ke.getKeyChar() <= '9'))&&jtfsComprador[0].getText().length()<10||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsComprador[0].setEditable(true);
				}else{
					jtfsComprador[0].setEditable(false);
				}
			}
		});
		jtfsComprador[1].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')||(ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z')))&&jtfsComprador[1].getText().length()<150||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsComprador[1].setEditable(true);
				}else{
					jtfsComprador[1].setEditable(false);
				}
			}
		});
		jtfsComprador[2].addKeyListener(new KeyAdapter() {
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
				if ((((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')||(ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z')))&&jtfsComprador[4].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsComprador[4].setEditable(true);
				}else{
					jtfsComprador[4].setEditable(false);
				}
			}
		});
		jtfsComprador[5].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')||(ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z')))&&jtfsComprador[5].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
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
		
		
		for(int i=0;i<lblsContratista.length;i+=1) {
			lblsContratista[i]=new JLabel(stringsContratista[i]);
			lblsContratista[i].setBounds(250, 50+(i*30), 150, 20);
			jtfsContratista[i]=new JTextField();
			jtfsContratista[i].setBounds(400, 50+(i*30), 250, 20);
			panelContratista.add(lblsContratista[i]);
			panelContratista.add(jtfsContratista[i]);
		}
		jtfsContratista[0].addKeyListener(new KeyAdapter() {//validacion
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
				if ((((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')||(ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z')))&&jtfsContratista[1].getText().length()<50||(code==KeyEvent.VK_BACK_SPACE)) {
					jtfsContratista[1].setEditable(true);
				}else{
					jtfsContratista[1].setEditable(false);
				}
			}
		});
		jtfsContratista[2].addKeyListener(new KeyAdapter() {//validacion
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
			lblsCriptomoneda[i].setBounds(250, 50+(i*30), 150, 20);
			jtfsCriptomoneda[i]=new JTextField();
			jtfsCriptomoneda[i].setBounds(400, 50+(i*30), 250, 20);
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
		jtfsCriptomoneda[1].addKeyListener(new KeyAdapter() {
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
			panelPool.add(lblsPool[i]);
		}
		for(int i=0;i<lblsOrden.length;i+=1) {
			lblsOrden[i]=new JLabel(stringsOrden[i]);
			lblsOrden[i].setBounds(250, 50+(i*30), 150, 20);
			panelOrden.add(lblsOrden[i]);
		}
		for(int i=0;i<lblsOrdenDePotencia.length;i+=1) {
			lblsOrdenDePotencia[i]=new JLabel(stringsOrdenDePotencia[i]);
			lblsOrdenDePotencia[i].setBounds(250, 50+(i*30), 150, 20);
			panelOrdenDePotencia.add(lblsOrdenDePotencia[i]);
		}
		
		dp.setLocation(0, 0);
		dp.setSize(Toolkit. getDefaultToolkit(). getScreenSize());
		add(dp);
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
		//frame.setClosable(true);
		//frame.setAutoscrolls(true);
		dp.add(frame);
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
		
		menuBar.add(comprador);
		menuBar.add(contratista);
		menuBar.add(criptomoneda);
		menuBar.add(pool);
		menuBar.add(orden);
		menuBar.add(ordenDePotencia);
		
		
		
	}
	
	public void modificarYAñadirLabel(int x, int y, int width,int height,JLabel label,JPanel panel,Font font) {
		label.setBounds(x, y, width, height);
		label.setFont(font);
		panel.add(label);
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
			if(src==menuItems[0][0]) {
				lblOpComprador.setText("Altas");
			}else if(src==menuItems[0][1]) {
				lblOpComprador.setText("Bajas");
			}else if(src==menuItems[0][2]) {
				lblOpComprador.setText("Cambios");
			}else if(src==menuItems[0][3]) {
				lblOpComprador.setText("Consultas");
			}
			frameComprador.setBounds(x, x, 1300, 800);
			frameComprador.toFront();
			frameComprador.setVisible(true);
		}else if(src==menuItems[1][0]||src==menuItems[1][1]||src==menuItems[1][2]||src==menuItems[1][3]) {
			if(src==menuItems[1][0]) {
				lblOpContratista.setText("Altas");
			}else if(src==menuItems[1][1]) {
				lblOpContratista.setText("Bajas");
			}else if(src==menuItems[1][2]) {
				lblOpContratista.setText("Cambios");
			}else if(src==menuItems[1][3]) {
				lblOpContratista.setText("Consultas");
			}
			frameContratista.setBounds(x, x, 1300, 800);
			frameContratista.toFront();
			frameContratista.setVisible(true);
		}else if(src==menuItems[2][0]||src==menuItems[2][1]||src==menuItems[2][2]||src==menuItems[2][3]) {
			if(src==menuItems[2][0]) {
				lblOpCriptomoneda.setText("Altas");
			}else if(src==menuItems[2][1]) {
				lblOpCriptomoneda.setText("Bajas");
			}else if(src==menuItems[2][2]) {
				lblOpCriptomoneda.setText("Cambios");
			}else if(src==menuItems[2][3]) {
				lblOpCriptomoneda.setText("Consultas");
			}
			frameCriptomoneda.setBounds(x, x, 1300, 800);
			frameCriptomoneda.toFront();
			frameCriptomoneda.setVisible(true);
		}else if(src==menuItems[3][0]||src==menuItems[3][1]||src==menuItems[3][2]||src==menuItems[3][3]) {
			if(src==menuItems[3][0]) {
				lblOpPool.setText("Altas");
			}else if(src==menuItems[3][1]) {
				lblOpPool.setText("Bajas");
			}else if(src==menuItems[3][2]) {
				lblOpPool.setText("Cambios");
			}else if(src==menuItems[3][3]) {
				lblOpPool.setText("Consultas");
			}
			framePool.setBounds(x, x, 1300, 800);
			framePool.toFront();
			framePool.setVisible(true);
		}else if(src==menuItems[4][0]||src==menuItems[4][1]||src==menuItems[4][2]||src==menuItems[4][3]) {
			if(src==menuItems[4][0]) {
				lblOpOrden.setText("Altas");
			}else if(src==menuItems[4][1]) {
				lblOpOrden.setText("Bajas");
			}else if(src==menuItems[4][2]) {
				lblOpOrden.setText("Cambios");
			}else if(src==menuItems[4][3]) {
				lblOpOrden.setText("Consultas");
			}
			frameOrden.setBounds(x, x, 1300, 800);
			frameOrden.toFront();
			frameOrden.setVisible(true);
		}else if(src==menuItems[5][0]||src==menuItems[5][1]||src==menuItems[5][2]||src==menuItems[5][3]) {
			if(src==menuItems[5][0]) {
				lblOpOrdenDePotencia.setText("Altas");
			}else if(src==menuItems[5][1]) {
				lblOpOrdenDePotencia.setText("Bajas");
			}else if(src==menuItems[5][2]) {
				lblOpOrdenDePotencia.setText("Cambios");
			}else if(src==menuItems[5][3]) {
				lblOpOrdenDePotencia.setText("Consultas");
			}
			frameOrdenDePotencia.setBounds(x, x, 1300, 800);
			frameOrdenDePotencia.toFront();
			frameOrdenDePotencia.setVisible(true);
		}
		
		if (x==300||y==300) {
			x=0;
			y=0;
		}
		x+=plusX;
		y+=plusY;
		
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
