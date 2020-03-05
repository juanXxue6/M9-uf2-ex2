
import dames.Damas;
import dames.Moviments;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alumne
 */
public class partida extends javax.swing.JFrame {

    //Declarar variables
    private Moviments movimentsString;
    SessionFactory sessionFactory;
    Session session;
    NewHibernateUtil sessionBD = new NewHibernateUtil();
    private Set set = new HashSet(0);
    private static String[] posicionesTablero = new String[64];
    private static String stringaa = "";
    int cont = 0;
    private Damas partida;
    private boolean nuevo;
    private int contador = 1;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form partida
     */
    //Contructor
    public partida(boolean nuevo) {
        initComponents();
        Date fecha = new Date();

        this.nuevo = nuevo;

        //Creem la sessionj
        sessionFactory = sessionBD.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        //Si la partida és nova, crear un nou objecte Partida. Si no, anar al mètode recuperar partida.
        if (nuevo) {
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (taulell.getModel().getValueAt(i, j).toString().equals("")) {
                        stringaa += " ";
                    } else {
                        stringaa += taulell.getModel().getValueAt(i, j).toString();
                    }

                }
            }

            partida = new Damas(fecha, '?');
            Moviments movimientos = new Moviments(partida, contador, stringaa);
            set.add(movimientos);
            partida.setMovimentses(set);

            session.save(partida);
            session.save(movimientos);
            session.getTransaction().commit();
            session.close();
            stringaa = "";
            contador++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		taulell = new javax.swing.JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		taulell.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {
						{ "X", "", "X", "", "X", "", "X", "" },
						{ "", "X", "", "X", "", "X", "", "X" },
						{ "", "", "", "", "", "", "", ""},
						{ "", "", "", "", "", "", "", "" },
						{ "", "", "", "", "", "", "", "" },
						{ "", "", "", "", "", "", "", "" },
						{ "O", "", "O", "", "O", "", "O", "" },
						{ "", "O", "", "O", "", "O", "", "O" } },
				new String[] { "Col...", "Col...", "Col...", "Col...",
						"Col...", "Col...", "Col...", "Col..." }));
		taulell.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(taulell);

		jButton1.setText("Sortir");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addContainerGap(
																		15,
																		Short.MAX_VALUE)
																.addComponent(
																		jScrollPane1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		375,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(164,
																										164,
																										164)
																								.addComponent(
																										jButton1))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(172,
																										172,
																										172)
																								.addComponent(
																										jLabel1)))
																.addGap(0,
																		0,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(21, 21, 21)
						.addComponent(jButton1)
						.addGap(18, 18, 18)
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 175,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(jLabel1)
						.addContainerGap(45, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

    //Si dones al botó sortir, es tanca
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    //Clicker de la taula
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTable1MouseClicked
        int fila;
        int columna;
        int numO = 0;
        int numX = 0;

        fila = obtenirFilaClicada();
        columna = obtenirColumnaClicada();
        //Adaptem el pseudocodi al java
        if (noHiHaOrigen()) {
            if (jugaX && EsX(fila, columna)) {
                ActualitzaNouOrigen(fila, columna);
            } else if (jugaO && EsO(fila, columna)) {
                ActualitzaNouOrigen(fila, columna);
            } else {
                mostraError();
            }
        } else if (movimentValid(fila, columna)) {
            if (esBuit(fila, columna) || ocupatContrari(fila, columna)) {
                mou(fila, columna);
            }
        } else if (ocupatPropi(fila, columna)) {
            ActualitzaNouOrigen(fila, columna);
        } else {
            mostrarErrorMoviment();
        }
        //Mirem si després del moviment algú ha guanyat
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (taulell.getModel().getValueAt(i, j).toString().equalsIgnoreCase("O")) {
                    numO++;
                } else if (taulell.getModel().getValueAt(i, j).toString().equalsIgnoreCase("X")) {
                    numX++;
                }
            }
        }
        //En funció de qui hagi guanyat, ho mostra i s'acaba la partida.
        for (int i = 0; i < 8; i++) {
            if (taulell.getModel().getValueAt(0, i).toString().equalsIgnoreCase("O") || numX == 0) {
                taulell.setEnabled(false);
                jLabel1.setText("Ha guanyat el jugador O!!");
                guardarGuanyador('O');
            } else if (taulell.getModel().getValueAt(7, i).toString().equalsIgnoreCase("X") || numO == 0) {
                taulell.setEnabled(false);
                jLabel1.setText("Ha guanyat el jugador X!!");
                guardarGuanyador('X');
            }
        }
    }// GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    private boolean jugaX = true;
    private boolean jugaO = false;
    private int filaOrigen = -1;
    private int columnaOrigen = -1;

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed"
        // desc=" Look and feel setting code (optional) ">
        /*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(partida.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(partida.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(partida.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(partida.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new partida(true).setVisible(true);
            }
        });

    }

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable taulell;

	// End of variables declaration//GEN-END:variables
    
    //Comença la part llògica del programa 
    private int obtenirFilaClicada() {
        return taulell.getSelectedRow();
    }

    private int obtenirColumnaClicada() {
        return taulell.getSelectedColumn();
    }

    private boolean noHiHaOrigen() {
        return (this.filaOrigen == -1);
    }

    private boolean EsX(int fila, int columna) {
        return (taulell.getModel().getValueAt(fila, columna).toString().equalsIgnoreCase("X"));
    }

    private boolean EsO(int fila, int columna) {
        return (taulell.getModel().getValueAt(fila, columna).toString().equalsIgnoreCase("O"));
    }

    private void ActualitzaNouOrigen(int fila, int columna) {
        this.filaOrigen = fila;
        this.columnaOrigen = columna;
    }

    private void mostraError() {
        jLabel1.setText("Tria una fitxa teva");
    }

    private boolean movimentValid(int fila, int columna) {
        if (!ocupatPropi(fila, columna)) {
            if (jugaX && (fila == filaOrigen + 1) && (columna == columnaOrigen + 1 || columna == columnaOrigen - 1)) {
                return true;
            } else if (jugaO && (fila == filaOrigen - 1) && (columna == columnaOrigen + 1 || columna == columnaOrigen - 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private boolean esBuit(int fila, int columna) {
        return (taulell.getModel().getValueAt(fila, columna).equals(""));
    }

    private boolean ocupatContrari(int fila, int columna) {
        return (!taulell.getModel().getValueAt(fila, columna).toString().equalsIgnoreCase(taulell.getModel().getValueAt(filaOrigen, columnaOrigen).toString()));
    }

    private void mou(int fila, int columna) {
        taulell.setValueAt(taulell.getModel().getValueAt(filaOrigen, columnaOrigen).toString(), fila, columna);
        taulell.setValueAt("", filaOrigen, columnaOrigen);
        filaOrigen = -1;
        columnaOrigen = -1;
        jugaX = jugaO;
        jugaO = !jugaX;
        jLabel1.setText("");
        guardarMoviment();
    }

    private boolean ocupatPropi(int fila, int columna) {
        return (taulell.getModel().getValueAt(fila, columna).toString().equalsIgnoreCase(taulell.getModel().getValueAt(filaOrigen, columnaOrigen).toString()));
    }

    private void mostrarErrorMoviment() {
        jLabel1.setText("Moviment erroni");
    }

    //Recuperem la partida
    public void recuperarPartida(){
        Query query = session.createQuery("select max(d.id) from Damas d");
        List idPartidaRecent = query.list();
        //Seleccionem la última partida (id)
        Integer a = (Integer) idPartidaRecent.get(0);
        //Fiquem que puguis seguir jugant a partir de la recuperada
        partida = (Damas)session.get(Damas.class, a);
        
        //Creem la query dels moviments
        Query query2 = session.createQuery("from Moviments m where idPartida = :idMaxima ");
        query2.setParameter("idMaxima", a);
        //Fem una arraylist dels moviments
        List movimientosQuery = query2.list();
        //Per cada moviment, printem el resultat
        for (int i = 0; i < movimientosQuery.size(); i++) {
            movimentsString = (Moviments) movimientosQuery.get(i);
            plenarTaulell(movimentsString.getMoviment());
        }
    }

    private void plenarTaulell(String moviments){
        char[] movimentsChar = moviments.toCharArray();
        
        //Dels moviments, els afegim a la taula
        int aux = 0;
        for (int i = 0; i <= 7; i++) {
            for (int f = 0; f <= 7; f++) {
                taulell.setValueAt( movimentsChar[aux],i,f);
                aux++;
            }
        }
    }

    private void guardarGuanyador(char guanyador) {
        //Depén de qui guanyi, afegim el guanyador
        for (int h = 0; h <= 7; h++) {
            for (int j = 0; j <= 7; j++) {
                if (taulell.getModel().getValueAt(h, j).toString().equals("")) {
                    stringaa += " ";
                } else {
                    stringaa += taulell.getModel().getValueAt(h, j).toString();
                }

            }
        }
        Moviments movimientos = new Moviments(partida, contador, stringaa);
        set.add(movimientos);
        partida.setMovimentses(set);

        partida.setGuanyador(guanyador);
        sessionFactory = sessionBD.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(partida);
        session.save(movimientos);
        session.getTransaction().commit();
        session.close();
        contador++;
    }

    private void guardarMoviment() {
        //Guardem en una String el taulell i el pujem
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (taulell.getModel().getValueAt(i, j).toString().equals("")) {
                    stringaa += " ";
                } else {
                    stringaa += taulell.getModel().getValueAt(i, j).toString();
                }

            }
        }

        Moviments movimientos = new Moviments(partida, contador, stringaa);

        sessionFactory = sessionBD.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(movimientos);
        session.getTransaction().commit();
        session.close();
        stringaa = "";
        contador++;
    }
}
