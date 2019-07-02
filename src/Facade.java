import java.io.IOException;

public class Facade {
	private Analise analise;
	private Analise analise2;
	private Analise analise3;
	
	public Facade() throws IOException {
		analise = new Analise();
		analise2 = new Analise();
		analise3 = new Analise();
	}
	public void executaIdentificaCodeSmell() {
		
		try {
			//Caso de teste que identifica falta de identação e closure smell, ou seja funções aninhadas
			analise.geraASTScript();
			analise.identificaFaltaDeIdentacaoeIdenta();
			analise.detectaClosureSmell();
			
			//Caso de teste cujo objetivo é identificar GodClass
			analise2.geraASTScript2();
			analise2.detectaGodClass();
			
			//Caso de teste cujo objetivo é identificar o Long Menthod
			analise3.geraASTScript3();
			analise3.detectaLongMethod();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
