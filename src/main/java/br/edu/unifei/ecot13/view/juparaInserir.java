package br.edu.unifei.ecot13.view;

import br.edu.unifei.ecot13.especie.*;
import br.edu.unifei.ecot13.grupo.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;

public class juparaInserir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nome;
	private JTextField textField_pelo;
	private JLabel lbl_altura;
	
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
					juparaInserir frame = new juparaInserir();
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
	public juparaInserir() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Inserir Jupará");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		// --- Labels + Inputs ---
		
		// Nome
		JLabel lbl_nome = new JLabel("Nome:");
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_nome = new JTextField();
		textField_nome.setColumns(10);
		
		// Cor do pelo
		JLabel lbl_pelo = new JLabel("Cor do Pelo:");
		lbl_pelo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_pelo = new JTextField();
		textField_pelo.setColumns(10);
		
		// Altura
		lbl_altura = new JLabel("Altura:");
		lbl_altura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner spinner_altura = new JSpinner();
		
		// Tamanho do rabo
		JLabel lbl_rabo = new JLabel("Tamanho do Rabo:");
		lbl_rabo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner spinner_rabo = new JSpinner();
		
		// Gênero
		JComboBox comboBox_genero = new JComboBox();
		comboBox_genero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_genero.setModel(new DefaultComboBoxModel(new String[] {"Femea", "Macho"}));
		
		// Se tem deficiência
		JCheckBox chckbx_deficiencia = new JCheckBox("Tem deficiência?");
		chckbx_deficiencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		// não sei mas quebra quando eu tiro
		JList list = new JList();
		list.setToolTipText("");
		
		// Definição do grupo do jupará
		JLabel lbl_grupo = new JLabel("Id do Grupo:");
		lbl_grupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner spinner_grupo = new JSpinner();
		
		// Humor
		JLabel lbl_humor = new JLabel("Humor:");
		lbl_humor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JComboBox comboBox_humor = new JComboBox();
		comboBox_humor.setModel(new DefaultComboBoxModel(new String[] {"Atiçado", "Irritado", "Tranquilo"}));
		comboBox_humor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		// Botão de Inserir
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				em.getTransaction().begin();
				// Adicionar tudo dos inputs ao BD
				jupara= new Jupara(textField_nome.getText());
				CaracteristicasFisicas caracteristicas = new CaracteristicasFisicas();
				caracteristicas.setCorDoPelo(textField_pelo.getText());
				caracteristicas.setAltura((Integer)spinner_altura.getValue());
				caracteristicas.setTamanhoRabo((Integer)spinner_rabo.getValue());
				caracteristicas.setTemDeficiencia(chckbx_deficiencia.isSelected());
				em.persist(caracteristicas);
				jupara.setCaracteristicas(caracteristicas);
				
				String genero = comboBox_genero.getSelectedItem().toString();
				if(genero == "Femea") {
					Femea femea = new Femea();
					em.persist(femea);
					jupara.setGenero(femea);
				} else {
					Macho macho = new Macho();
					em.persist(macho);
					jupara.setGenero(macho);
				}
				
				Grupo grupo = em.find(Grupo.class, (Integer)spinner_grupo.getValue());
				if(grupo == null) {
					// criação de um grupo
					grupo = new Grupo();
					Natural natural = new Natural();
					em.persist(natural);
					grupo.setHabitat(natural);
					Desmatamento desmatamento = new Desmatamento();
					em.persist(desmatamento);
					grupo.setAmeaca(desmatamento);
					em.persist(grupo);
					jupara.setGrupo(grupo);
				} else {
					jupara.setGrupo(grupo);
				}
				
				String humor = comboBox_humor.getSelectedItem().toString();
				if(humor == "Tranquilo") {
					Tranquilo tranquilo = new Tranquilo();
					em.persist(tranquilo);
					jupara.setHumor(tranquilo);
				} else if(humor == "Atiçado") {
					Aticado aticado = new Aticado();
					em.persist(aticado);
					jupara.setHumor(aticado);
				} else {
					// modo de ataque se irritado
					Arranhar arranhar = new Arranhar();
					em.persist(arranhar);
					Morder morder = new Morder();
					em.persist(morder);
					Segurar segurar = new Segurar();
					em.persist(segurar);
					Irritado irritado = new Irritado();
					irritado.setTipo(segurar);
					em.persist(irritado);
					jupara.setHumor(irritado);
				}
				
//				em.getTransaction().begin();
				em.persist(jupara);
				grupo.conhecer(jupara);
				em.merge(grupo);
				em.getTransaction().commit();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(comboBox_genero, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lbl_rabo, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
							.addComponent(lbl_pelo)
							.addComponent(lbl_nome)
							.addComponent(lbl_altura, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl_grupo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lbl_humor, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(comboBox_humor, 0, 169, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
								.addComponent(chckbx_deficiencia, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinner_rabo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(spinner_altura, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(textField_pelo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(textField_nome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
							.addGap(162))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(spinner_grupo, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(123)
					.addComponent(btnInserir, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(271, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_nome)
						.addComponent(textField_nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_pelo)
						.addComponent(textField_pelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_altura, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_altura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_rabo, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_rabo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbx_deficiencia)
						.addComponent(comboBox_genero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_grupo)
						.addComponent(spinner_grupo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_humor, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_humor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnInserir, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
