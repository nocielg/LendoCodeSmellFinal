import java.io.IOException;

public class Main {
	
	public static void main (String [] args) {
		System.out.println("rodou");
		//SmellDetector.setJSName("javascript");
		//SmellDetector smell = new SmellDetector();
		//smell.analyseObjectLiteralNode();
		//SmellDetector.writeReportTofile();
		//SmellDetector.getcandidateJSObjectList();
		//SmellDetector.generateReport(true);
		//DetectandoSmell smell= new DetectandoSmell();
		//String scopename;
		//SmellDetector.setJSName("/src/javascript");
		//SmellDetector.analyseCoupling("/src/javascript", "", "");
		//SmellDetector.generateReport(true);
		//AstRoot ast = null;
		//Context cx = Context.enter();
		//Parser rhinoParser = new Parser(new CompilerEnvirons(), cx.getErrorReporter());
		//ast = rhinoParser.parse(new String(input), scopename, 0);
		//JSASTModifier modifier; //.setScopeName(scopename);
//
		//modifier.start();
		//ast.visit(modifier);
		//SmellDetector.generateReport(false);
		
		//DetectandoSmell detectando = new DetectandoSmell();
		//detectando.teste();
		
		try {
			Analise print = new Analise();
			print.identificaFaltaDeIdentacaoeIdenta();
			//print.testando();
			//print.testePegarArgumentos();
			//print.testePegarArgumentos();
			
			//print.detectaLongMethod();
			//print.detectaClosureSmell();
			//print.lendoArquivoComCodigo();
			//print.lendoArquivoComCodigo();
			//print.identificaFaltaDeIdentacaoeIdenta();
			print.detectaGodClass();
			print.detectaLongMethod();
			print.detectaClosureSmell();
			//print.detectaLongParameterList();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			//Analisa analisa = new Analisa();
		//Carro carro = new Carro();
			// TODO Auto-generated catch block
		
		}
		
			
		
		
		//print.testando();
		//print.testePegarArgumentos();
		//print.testePegarArgumentos();
		
		//print.detectaLongMethod();
		//print.detectaClosureSmell();
		//print.lendoArquivoComCodigo();
		//print.lendoArquivoComCodigo();
		//print.identificaFaltaDeIdentacaoeIdenta();
		//print.detectaGodClass();
		//print.detectaLongMethod();
		//print.detectaClosureSmell();
		//print.detectaLongParameterList();
		
	}
