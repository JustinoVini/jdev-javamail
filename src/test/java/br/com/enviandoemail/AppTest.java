package br.com.enviandoemail;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	/* Método para testar o email */
	@org.junit.Test
	public void testeEmail() throws Exception {		
		
		ObjetoEnviaEmail enviaEmail = 
				new ObjetoEnviaEmail("visilva569038@gmail.com", 
									 "Vinicius JDEV Treinamento",
									 "Testando ObjetoEmail",
									 "Esse é o teste com os atributos e configurações de envio"
									 + "de mensagem por meio de uma classe");
		
		enviaEmail.enviarEmail();
		
		Thread.sleep(5000);

	}

}
