import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		String nome = JOptionPane.showInputDialog("Por favor informe seu nome");

		Cliente cliente = new Cliente();
		cliente.setNome(nome);
	
		Conta cc = new ContaCorrente(cliente);
		Conta poupanca = new ContaPoupanca(cliente);
		
		int opcao=0;
		
		do {
			String resp = JOptionPane.showInputDialog(
					"Olá, " + nome + ". Informe a opção desejada\n" + "1- Depósito\n" + "2- Saque\n" + "3- Transferência\n" + "4- Sair");
			opcao = Integer.parseInt(resp);
	
			if (opcao < 1 && opcao > 3) {
				JOptionPane.showMessageDialog(null, "Informe uma opcão válida");
			} 
			else {
				switch (opcao) {
				case 1: 
					String tipo = JOptionPane.showInputDialog("Conta corrente ou poupança? (c/p)");
					resp = JOptionPane.showInputDialog("Informe a quantia a ser depositada");
					double quantia = Double.parseDouble(resp);
					
					if(tipo.charAt(0) == 'c') {
						cc.depositar(quantia);
					}
					else if (tipo.charAt(0) == 'p'){
						poupanca.depositar(quantia);
					}
					else {
						JOptionPane.showMessageDialog(null, "Informe uma opcão válida");
					}
					break;
					
				case 2: 
					tipo = JOptionPane.showInputDialog("Conta corrente ou poupança? (c/p)");
					resp = JOptionPane.showInputDialog("Informe a quantia a ser sacada");
					quantia = Double.parseDouble(resp);
					
					if(tipo.charAt(0) == 'c') {
						if(quantia<cc.saldo)cc.sacar(quantia);
						else {JOptionPane.showMessageDialog(null, "Valor inválido");}
					}
					else if (tipo.charAt(0) == 'p'){
						if(quantia<poupanca.saldo) poupanca.sacar(quantia);
						else {JOptionPane.showMessageDialog(null, "Valor inválido");}
					}
					else {
						JOptionPane.showMessageDialog(null, "Informe uma opcão válida");
					}
					break;
					
				case 3: 
					tipo = JOptionPane.showInputDialog("A conta de destino é corrente ou poupança? (c/p)");
					resp = JOptionPane.showInputDialog("Informe a quantia a ser transferida");
					quantia = Integer.parseInt(resp);
									
					if(tipo.charAt(0) == 'c') {
						if(quantia<poupanca.saldo) poupanca.transferir(quantia, cc);
						else {JOptionPane.showMessageDialog(null, "Valor ultrapassa o saldo");}
					}
					else if (tipo.charAt(0) == 'p'){
						if(quantia<cc.saldo)cc.transferir(quantia, poupanca);
						else {JOptionPane.showMessageDialog(null, "Valor ultrapassa o saldo");}
					}
					else {
						JOptionPane.showMessageDialog(null, "Informe uma opcão válida");
					}
					break;
				
				case 4:
					break;
					
				
				default: 
					JOptionPane.showMessageDialog(null, "Informe uma opcão válida");
					break;
				}
				
				
											
			}
		}while(opcao!=4);
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}

}
