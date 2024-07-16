package br.edu.unifei.ecot13.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.unifei.ecot13.especie.*;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class juparaExcluir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("juparaPU");
	private EntityManager em = emf.createEntityManager();
	private Jupara jupara;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					juparaExcluir frame = new juparaExcluir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public juparaExcluir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		// Label e input
		JLabel lbl_id = new JLabel("Id do Jupará");
		lbl_id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner spinner_id = new JSpinner();
		
		// Não tenho ideia
		JList list = new JList();
		list.setToolTipText("");
		

		// Botão excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jupara=em.find(Jupara.class,(Integer)spinner_id.getValue());
				if(jupara!=null) {
					em.getTransaction().begin();
					em.remove(jupara.getCaracteristicas());
					em.remove(jupara.getGenero());
					
//					jupara.getGrupo().getLista().clear();
					Iterator<Jupara> iterator = jupara.getGrupo().getLista().iterator();
					while (iterator.hasNext()) {
					    Jupara item = iterator.next();
					    if (item.getCodigo() == jupara.getCodigo()) {
					        iterator.remove();
					    }
					}
					em.merge(jupara.getGrupo());					
					jupara.setGrupo(null);
					
					jupara.setHumor(null);
					em.merge(jupara);
					em.remove(jupara);
					em.getTransaction().commit();
				}else {
					JOptionPane.showMessageDialog(null, "Jupara não existe");
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(162))
						.addComponent(spinner_id, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(164))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_id)
						.addComponent(spinner_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(196)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(114, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
