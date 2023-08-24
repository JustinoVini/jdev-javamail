package br.com.enviandoemail;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/* Método para testar o email */
	@org.junit.Test
	public void testeEmail() throws Exception {

		StringBuilder stringBuilderTextoEmail = new StringBuilder();

		stringBuilderTextoEmail.append("Olá <br/><br/>"); /* Inserindo HTML */
		stringBuilderTextoEmail.append("<h2>Você está recebendo o acesso ao curso de Java</h2> <br/><br/>");
		stringBuilderTextoEmail.append("Para ter acesso clique no botão abaixo.<br/><br/>");

		stringBuilderTextoEmail.append("<b>Login: </b> seuacesso@email.com<br/>");
		stringBuilderTextoEmail.append("<b>Senha: </b> asdas12<br/><br/>");
		
		stringBuilderTextoEmail.append(
				"<a target=\"_blank\" href=\"https://www.jdevtreinamento.com.br/\" style=\"color: #2525a7; padding: 14px 25px; text-align: center; text-decoration: none; display: inline-block; border-radius: 30px; size: 20px; font-family:courier; border: 3px solid green; background-color: #99DA39;\">Acessar Portal do Aluno</a><br/><br/>");

		stringBuilderTextoEmail.append("<span style=\"font-size: 8px;\">Ass.: Vinicius teste email</span>");
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("visilva569038@gmail.com", "Vinicius JDEV Treinamento",
				"Testando ObjetoEmail", stringBuilderTextoEmail.toString());

		//enviaEmail.enviarEmail(true);
		
		enviaEmail.enviarEmailAnexo(true);

		Thread.sleep(5000);

	}

}
