package hut.eclipse;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class GetMethod extends ASTVisitor{		
	private String methodID;
	private int start = 0;
	private int length = 0;
	private boolean find = false;
	public boolean visit(MethodDeclaration node) 
	{			
		String id = node.getName() + SourceFind.getParametersList(node.resolveBinding()).toString();
		if(id.equals(methodID) && (find == false))
		{
			start = node.getStartPosition();
			length = node.getLength();
			find = true;
		}
		return false;
	}
	public boolean find()
	{
		return find;
	}
	// method ID bat dau tu tenMethod(danh sach parameter)
	public void parse(ICompilationUnit unit, String methodID)
	{
		this.methodID = methodID;
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);    	
		parser.setSource(unit);
		CompilationUnit ast = (CompilationUnit) parser.createAST(null);		
		ast.accept(this);		
	}
	public int getStart() {
		return start;
	}
	public int getLength() {
		return length;
	}
}