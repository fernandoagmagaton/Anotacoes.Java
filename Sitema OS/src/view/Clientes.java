package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import utils.Validador;
import java.awt.Color;

public class Clientes extends JDialog {
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JTextField txtID;
	
	// Instanciar objetos JDBC
		DAO dao = new DAO();
		private Connection con;
		private PreparedStatement pst;
		private ResultSet rs;
		private JButton btnExcluir;
		private JButton btnEditar;
		private JButton btnAdicionar;
		private JButton btnPesquisar;
		
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		getContentPane().setForeground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/Cadastro Clientes.png")));
		setTitle("Cadastro Clientes");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 44, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblFone = new JLabel("Telefone:");
		lblFone.setBounds(10, 119, 57, 14);
		getContentPane().add(lblFone);
		
		JLabel lblemail = new JLabel("E-mail:");
		lblemail.setBounds(10, 152, 46, 14);
		getContentPane().add(lblemail);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 80, 46, 14);
		getContentPane().add(lblCpf);
		
		txtNome = new JTextField();
		txtNome.setBounds(74, 41, 151, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador (50));
		
		txtFone = new JTextField();
		txtFone.setBounds(74, 116, 151, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);
		txtFone.setDocument(new Validador (15));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(74, 149, 151, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setDocument(new Validador (30));
		
		txtCpf = new JTextField();
		txtCpf.setBounds(74, 77, 151, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);
		txtCpf.setDocument(new Validador (11));
		
		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setDefaultCapable(false);
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setIcon(new ImageIcon(Clientes.class.getResource("/img/pesquisar.png")));
		btnPesquisar.setBounds(253, 61, 48, 48);
		getContentPane().add(btnPesquisar);
		
		getRootPane().setDefaultButton(btnPesquisar);
		
		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setIcon(new ImageIcon(Clientes.class.getResource("/img/clear icon.png")));
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBounds(42, 202, 48, 48);
		getContentPane().add(btnLimpar);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 11, 46, 14);
		getContentPane().add(lblID);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(74, 10, 46, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.setToolTipText("Editar Cliente");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarContato();
			}
		});
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/Editor.png")));
		btnEditar.setBounds(240, 202, 48, 48);
		getContentPane().add(btnEditar);
		
		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.setToolTipText("Adicionar Cliente");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/ADD.png")));
		btnAdicionar.setBounds(339, 202, 48, 48);
		getContentPane().add(btnAdicionar);
		
		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirClientes();
			}
		});
		btnExcluir.setToolTipText("Excluir Cliente");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/Excluir Contato.png")));
		btnExcluir.setBounds(135, 202, 48, 48);
		getContentPane().add(btnExcluir);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(255, 128, 192));
		lblNewLabel.setForeground(new Color(255, 128, 192));
		lblNewLabel.setBounds(0, 190, 434, 71);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/urso medico.png")));
		lblNewLabel_1.setBounds(349, 11, 64, 64);
		getContentPane().add(lblNewLabel_1);

	}//Fim do construtor
	
	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtCpf.setText(null);
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnPesquisar.setEnabled(true);
	}
	
	private void buscar() {
		// Dica - testar o evento preimeiro
		// System.out.println("teste do botão buscar");
		// Criar Mua variável com a query (instruções do banco)
		String read = "select * from clientes where cpf = ?";
		// Tratamento de exceções
		try {
			// Abrir a conexão
			con = dao.conectar();

			// Preparar a exucução da query(instrução sql - CRUD Read)
			// O paraêmtro 1 substitui a ? pelo conteúdo da caixa de texto
			pst = con.prepareStatement(read);
			pst.setString(1, txtCpf.getText());
			// executar a query e buscar o resultado
			rs = pst.executeQuery();
			// uso da estrutura if else parar verificar se existe o contato
			// rs.next() -> se existir um contato no banco
			if (rs.next()) {
				txtID.setText(rs.getString(1)); // 1 campo da tabela
				txtNome.setText(rs.getString(2));
				txtFone.setText(rs.getString(3)); // 3 campo (fone)
				txtEmail.setText(rs.getString(4));// 4 campo (email)
				txtCpf.setText(rs.getString(5));//5 campo (CPF)
				//validação (liberação dos botões alterar e excluir)		
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnPesquisar.setEnabled(false);
				
			} else {

				// se não existir um contato no banco
				JOptionPane.showMessageDialog(null, "Cliente Inexistente");
				//validação (liberação do botão adicionar)
				btnAdicionar.setEnabled(true);
				btnPesquisar.setEnabled(false);	
				
			}

		} catch (Exception e) {
			System.out.print(e);
		}
	}// Fim do método buscar

	
	private void editarContato() {
		// System.out.println("teste do Método");

		// Validação dos campos obrigátorios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o fone do contato");
			txtFone.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digita o CPF");
			txtCpf.requestFocus();
		}else {
			// Lógica principal
			// CRUD - Update
			String upadate = "update clientes set nome=?, fone=?, email=?, cpf=? where idcli=?";
			// tratamentos de exceçoes
			try {

				// como a conexão
				con = dao.conectar();
				// preparar a query (instrução sql)	
				pst = con.prepareStatement(upadate);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtCpf.getText());
				pst.setString(5, txtID.getText());
				// executar a query
				pst.executeUpdate();
				// confirmar para o usuário
				JOptionPane.showMessageDialog(null, "Dados do Cliente editados com sucesso");
				// limpar campos
				limparCampos();
				// fechar conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
		private void adicionar() {
			// System.out.println("teste");
			// Validação de campos obrigatóriios
			if (txtNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o nome do Cliente");
				txtNome.requestFocus();
			} else if (txtCpf.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Cpf do Cliente");
				txtCpf.requestFocus();
			}else if (txtFone.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Telefone do Cliente");
				txtFone.requestFocus();
			} else {
				
				// lógica pricipal
				// CRUD Creat
				String create = "insert into clientes (nome,fone,email,cpf) value (?, ?, ?, ?)";
				// tratamento com exceções
				try {
					//abrir conexão 
					con = dao.conectar();
					//preparar a execução da query(instrução sql - CRUD Create)
					pst = con.prepareStatement(create);
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtFone.getText());
					pst.setString(3, txtEmail.getText());
					pst.setString(4, txtCpf.getText());
					//executar a query(instruição sql (CRUD - Creat))
					pst.executeUpdate();
					//Confirmar
					JOptionPane.showMessageDialog(null, "Clientes adicionado");
					//fechar a conexão
					
				} catch (Exception e) {
					System.out.println(e);
				}
				}
		}
			// Método usado para excluir um contato

			private void excluirClientes() {
				// System.out.println("Teste do botão excluir");
				// validação de exclusão - a variável confima captura a opção escolhida

				int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Clientes ?", "Atenção !",
						JOptionPane.YES_NO_OPTION);
				if (confirma == JOptionPane.YES_NO_OPTION) {
					//CRUD - Delete
					String delete = "delete from clientes where idcli=?";
					//tratamento de exceções
					try {
						//abrir a conexão 
						con = dao.conectar();
						//preparar a query (instrução sql)
						pst = con.prepareStatement(delete);
						//substituir a ? pelo id do contato
						pst.setString(1, txtID.getText());
						//executar a query
						pst.executeUpdate();
						limparCampos();
						//exibir uma mensagem ao usuário
						JOptionPane.showMessageDialog(null, " Cliente excluido");
						//fechar a conexão 
						con.close();
					} catch (Exception e) {
						System.out.println(e);
}
}

	}// Fim do Método editar contato
}//Fim do Codigo

