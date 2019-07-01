import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JOptionPane;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.FunctionNode;


//
public class Analise  {
	//final Set<String> names = new HashSet<String>();
	//String script = "function abc(x,y) {return x+y;}";
	String script = "var o = {\n" +
            "  _x: 123, " +
            "                       get x() {" +
            "                                           "
            + ""
            + ""
            + ""
            + ""
            + ""
            + "															return this._x;\n" +
            "   }\n" +
            ", \n" +
            "                  set x(value) {\n" +
            "                "
            + ""
            + "this._x = value;\n" +
            "  }" +
            "};";
	private static final int MAX_METHODO_TAMANHO = 50;
	private static final int MIN_OBJECTO_PROPRIEDADES = 3;
	private int profundidadeFuncaoAnterior = 0;
	private static final int TAMANHO_MAXIMO_DO_SCOPE_CHAIN = 3;
	private static final int MAX_NUMBER_OF_SWITCHCASE = 3;
	private static final int Max_LINHAS_GOD_CLASS = 750;
	private static final int MAX_NUMERO_DE_PARAMETROS = 5;
	private int scopeChainLength = 0;
	private AstRoot astRoot;
	private AstNode ASTNode;
	FunctionNode f = (FunctionNode) ASTNode;
	private String linha;
	
	public Analise() throws IOException {
		
				
		// Realizando a análise sintática e gerando a AST
		CompilerEnvirons environment = new CompilerEnvirons();
		Parser parser = new Parser(environment);
		//lendoArquivoComCodigo();
	    astRoot = parser.parse(new StringReader(script), null, 1);
		//astRoot = parser.parse(new StringReader(linha), null, 1);
		
	}
	
	//Lendo o arquivo com o código
	public void lendoArquivoComCodigo() throws IOException {
		try {
			 BufferedReader br = new BufferedReader(new FileReader("/home/gleisson/eclipse-workspace/LendoCodeSmellFinal/src/javascript")); 
			//BufferedReader br = new BufferedReader(new FileReader("/home/gleisson/eclipse-workspace/LendoCodeSmellFinal/src/SmellReport.txt"));
		        while (br.ready()) {
		            linha = br.readLine();
		            System.out.println(linha);  
		        }
			  br.close();
			} catch (FileNotFoundException fnf) {
			  JOptionPane.showMessageDialog(null, "O arquivo não foi encontrado.", "Carregando o arquivo", JOptionPane.INFORMATION_MESSAGE);
			}
	}
	
	public void testando() {
		Context context = Context.enter();
		try {
			ScriptableObject scope = context.initStandardObjects();
			Scriptable that = context.newObject(scope);
			Function fct = context.compileFunction(scope, script, "script", 1, null);
			Object result = fct.call(
					context, scope, that, new Object[] {2, 3});
			//System.out.println(Context.jsToJava(result, int.class));
			System.out.println(Context.jsToJava(result, int.class));
			//Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);
		} finally {
			Context.exit();
		}
	}
	
	public void testeAvaliandoString() {
		Context cx = Context.enter();
        Scriptable scope = cx.initStandardObjects();
        cx.evaluateString(scope, "function f(x,y){ return x+y}", "<cmd>", 1, null);
        try {
            String result = (String) cx.evaluateString(scope, "f.toString()", "<cmd>", 1, null);
        	//String result = (String) cx.newObject(scope, "f.toString()", "<cmd>", 1, null);
            System.out.println(result);
        } catch (org.mozilla.javascript.EcmaError ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public void identificaFaltaDeIdentacaoeIdenta() throws IOException {
		//CompilerEnvirons environment = new CompilerEnvirons();
		//Parser parser = new Parser(environment);
	    //astRoot = parser.parse(new StringReader(script), null, 1);
		System.out.println(astRoot.toSource());
	    
	}
	
	//Detecta LongMethod
	public void detectaLongMethod() {
		//FunctionNode f = (FunctionNode) ASTNode;
		//CompilerEnvirons environment = new CompilerEnvirons();
		//Parser parser = new Parser(environment);
	    //astRoot = parser.parse(new StringReader(script), null, 1);
		
		
		int tamanhodaFuncao = astRoot.getEndLineno() - astRoot.getLineno();
		if (tamanhodaFuncao > MAX_METHODO_TAMANHO) {
			System.out.println(tamanhodaFuncao);
			
		}
		else {
			System.out.println("O método não possui o code smell Long Method");
			
		}
		System.out.println("O tamanho da função é:" + tamanhodaFuncao);
	}
	
	//dúvida
	//Detecta nested function
	public void detectaClosureSmell() throws IOException {
		/*script = "foo(42) { (x: Int) in\n" + 
				"    bar(x) { (x: Int) in\n" + 
				"      foobar(x) { // Noncompliant\n" + 
				"        print(x * 42)\n" + 
				"			foobar(x,y) {"+
				"      			foobar(x,y,g) {\"+\n" + 
				"							}\\n\" + 	" +
				"						}\n" + 
				"      print(x + 42)\n" + 
				"    }\n" + 
				"    print(x - 42)\n" + 
				"}";
		*/		
		
		script = "function A(x) { "+
			   "function B(y) {"+
				      "function C(z) {"+
				        " alert(x + y + z);"+
				      "}"+
				      "C(3);"+
				   "}"+
				   "B(2);"+
				"}"+
				"A(1);";
				
		//int fDepth = ASTNode.depth(); //astRoot.depth();
		int fDepth = astRoot.depth();
		//String name = astRoot.shortName();
		
		if(fDepth > profundidadeFuncaoAnterior) {
			scopeChainLength++;
			if(scopeChainLength>TAMANHO_MAXIMO_DO_SCOPE_CHAIN) {
				System.out.println("O método possui closure smell" );
				System.out.println(fDepth);
			}
			else {
				System.out.println("O método não possui closure smell");
				System.out.println(fDepth);
			}
		}
		else {
			System.out.println(fDepth);
			System.out.println("O método não possui closure smell");
			scopeChainLength = 1;
		}
		profundidadeFuncaoAnterior = fDepth;
		
	}
	
	//Detecta GodClass
	public void detectaGodClass() {
		int tamanhodaClasse = astRoot.getEndLineno() - astRoot.getLineno();
		if (tamanhodaClasse > Max_LINHAS_GOD_CLASS) {
			System.out.println("O método possui o code smell God Class");
			
		}
		else {
			System.out.println("O método não possui o code smell God Class");
			
		}
		System.out.println("O tamanho da classe é: " + tamanhodaClasse);
	}
	
	
	
	//testando
	/*public void detectaSwitchSmell() {

		//astRoot.getCases();
		
		//System.out.println("switch found at line: " + (ASTNode.getLineno()+1) + " and has " + s.getCases().size() + " statements");

		if (((Object) ASTNode).getCases().size() >3 ){
		
		}	
	}*/
	
	
	//testando
	//checa css
	// checking css in javascript 
	/*
	public void detectaCSSSmell(
			if(ASTNode)
			//if (((Name)ASTNode).getIdentifier().equals("style")){
				//SmellLocation sl = new SmellLocation("CSS in JavaScript", jsFileName,(ASTNode.getLineno()+1));
				//System.out.println("CSSinJS : at line " + (ASTNode.getLineno()+1) + " of file: " + jsFileName);
				//CSSinJS.add(sl);
			} else {
				System.out.println("O código não possui CSS dentro dele.");
			}
	}*/
	
	
	//Detecta long parameter list
	/*
	public void detectaLongParameterList() {
		//FunctionNode f = (FunctionNode) astRoot;
		int numOfParam = f.getParams().size();
		if (numOfParam >= MAX_NUMERO_DE_PARAMETROS){
			System.out.println("A funcao possui o code smell long parameter list");
		}
		else {
			System.out.println("A funcao nao possui o code smell long parameter list");
		}
	}
	*/
	
	/*
	//Detecta lazy objects
	/*public void detectaLazyObjects() {
		astRoot.size();
		if (astRoot.size() < MIN_OBJECTO_PROPRIEDADES) {
			
		}
	}
	
	/*
	@Override
	public boolean visit(AstNode no) {
		String script = "(V1ND < 0 ? Math.abs(V1ND) : 0)";
	    AstNode node = new Parser().parse(script, "<cmd>", 1);
	    node.visit(new Visitor());
	    System.out.println(names);
		
	}*/
}
	
