package br.edu.unifei.ecot13.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.unifei.ecot13.especie.*;
import br.edu.unifei.ecot13.grupo.Desmatamento;
import br.edu.unifei.ecot13.grupo.Grupo;
import br.edu.unifei.ecot13.grupo.Natural;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class juparaAlterar extends JFrame {

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
					juparaAlterar frame = new juparaAlterar();
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
	public juparaAlterar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		// Input ID procurado
		JLabel lbl_id = new JLabel("Id do Jupara:");
		lbl_id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner spinner_id = new JSpinner();
		
		// --- Labels + Inputs ---
		
		// Nome	
		JLabel lbl_nome = new JLabel("Nome:");
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_nome = new JTextField();
		textField_nome.setEnabled(false);
		textField_nome.setColumns(10);
		
		//	Cor do Pelo
		JLabel lbl_pelo = new JLabel("Cor do Pelo:");
		lbl_pelo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_pelo = new JTextField();
		textField_pelo.setEnabled(false);
		textField_pelo.setColumns(10);
		
		// Altura
		lbl_altura = new JLabel("Altura:");
		lbl_altura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner spinner_altura = new JSpinner();
		spinner_altura.setEnabled(false);
		
		// Tamnho do Rabo
		JLabel lbl_rabo = new JLabel("Tamanho do Rabo:");
		lbl_rabo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner spinner_rabo = new JSpinner();
		spinner_rabo.setEnabled(false);
		
		// Se tem deficiência
		JCheckBox chckbx_deficiencia = new JCheckBox("Tem deficiência?");
		chckbx_deficiencia.setEnabled(false);
		chckbx_deficiencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		// Sigo sem saber o que é
		JList list = new JList();
		list.setToolTipText("");
		
		// Id do Grupo
		JLabel lbl_grupo = new JLabel("Id do Grupo:");
		lbl_grupo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner spinner_grupo = new JSpinner();
		spinner_grupo.setEnabled(false);
		
		// Gênero
		JComboBox comboBox_genero = new JComboBox();
		comboBox_genero.setEnabled(false);
		comboBox_genero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_genero.setModel(new DefaultComboBoxModel(new String[] {"Femea", "Macho"}));
		
		// Humor
		JLabel lbl_humor = new JLabel("Humor:");
		lbl_humor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JComboBox comboBox_humor = new JComboBox();
		comboBox_humor.setEnabled(false);
		comboBox_humor.setModel(new DefaultComboBoxModel(new String[] {"Atiçado", "Irritado", "Tranquilo"}));
		comboBox_humor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		// --- Botões ---
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jupara.setNome(textField_nome.getText());
				CaracteristicasFisicas caracteristicas = new CaracteristicasFisicas();
				caracteristicas.setCorDoPelo(textField_pelo.getText());
				caracteristicas.setAltura((Integer)spinner_altura.getValue());
				caracteristicas.setTamanhoRabo((Integer)spinner_rabo.getValue());
				caracteristicas.setTemDeficiencia(chckbx_deficiencia.isSelected());
				em.persist(caracteristicas);
				jupara.setCaracteristicas(caracteristicas);
				
				em.remove(jupara.getGenero());
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
				
				jupara.getGrupo().getLista().remove(jupara);
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
					em.remove(jupara.getHumor());
					Tranquilo tranquilo = new Tranquilo();
					em.persist(tranquilo);
					jupara.setHumor(tranquilo);
				} else if(humor == "Atiçado") {
					em.remove(jupara.getHumor());
					Aticado aticado = new Aticado();
					em.persist(aticado);
					jupara.setHumor(aticado);
				} else {
					// modo de ataque se irritado
//					em.remove(((Irritado)jupara.getHumor()).getTipo());
					em.remove(jupara.getHumor());
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
				
				spinner_id.setEnabled(true);
				textField_nome.setEnabled(false);
				textField_pelo.setEnabled(false);
				spinner_altura.setEnabled(false);
				spinner_rabo.setEnabled(false);
				chckbx_deficiencia.setEnabled(false);
				spinner_grupo.setEnabled(false);
				comboBox_genero.setEnabled(false);
				comboBox_humor.setEnabled(false);
				btnAlterar.setEnabled(false);
				
				em.getTransaction().begin();
				em.merge(jupara);
				grupo.conhecer(jupara);
				em.merge(grupo);
				em.getTransaction().commit();
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jupara=em.find(Jupara.class,(Integer)spinner_id.getValue());
				if(jupara!=null) {
					spinner_id.setEnabled(false);
					textField_nome.setEnabled(true);
					textField_pelo.setEnabled(true);
					spinner_altura.setEnabled(true);
					spinner_rabo.setEnabled(true);
					chckbx_deficiencia.setEnabled(true);
					spinner_grupo.setEnabled(true);
					comboBox_genero.setEnabled(true);
					comboBox_humor.setEnabled(true);
					btnAlterar.setEnabled(true);
					
					textField_nome.setText(jupara.getNome());
					textField_pelo.setText(jupara.getCaracteristicas().getCorDoPelo());
					spinner_altura.setValue(jupara.getCaracteristicas().getAltura());
					spinner_rabo.setValue(jupara.getCaracteristicas().getTamanhoRabo());
					chckbx_deficiencia.setSelected(jupara.getCaracteristicas().getTemDeficiencia());
					spinner_grupo.setValue((Integer)jupara.getGrupo().getCodigo());
					if(jupara.getGenero() instanceof Femea) {
						comboBox_genero.setSelectedItem("Femea");
					} else {
						comboBox_genero.setSelectedItem("Macho");
					}
					if(jupara.getHumor() instanceof Tranquilo) {
						comboBox_humor.setSelectedItem("Tranquilo");
					} else if(jupara.getHumor() instanceof Irritado) {
						comboBox_humor.setSelectedItem("Irritado");
					} else {
						comboBox_humor.setSelectedItem("Atiçado");
					}
					
		
				} else {
					JOptionPane.showMessageDialog(null, "Jupara não existe");
				}
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(comboBox_genero, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lbl_rabo, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
									.addComponent(lbl_pelo)
									.addComponent(lbl_nome)
									.addComponent(lbl_altura, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbl_grupo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lbl_humor, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(spinner_id, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(162, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(chckbx_deficiencia, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
										.addComponent(spinner_rabo, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(spinner_altura, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(textField_pelo, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(textField_nome, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox_humor, 0, 169, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
									.addGap(162))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(spinner_grupo, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
