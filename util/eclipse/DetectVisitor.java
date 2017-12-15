package eclipse;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class DetectVisitor extends ASTVisitor{
	private String ID;
	private CompilationUnit ast;
	private EnumCodeComponentTYPE codeTYPE;
	private int offset;
	private int length;
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public DetectVisitor(CompilationUnit ast, String ID, EnumCodeComponentTYPE codeTYPE) {
		this.ID = ID;
		this.ast = ast;
		this.codeTYPE = codeTYPE;
		ast.accept(this);
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		String s = node.getName().toString();
		if (codeTYPE.equals(EnumCodeComponentTYPE.METHOD) && s.equals(ID))
		{
			offset = node.getStartPosition();
			length = node.getLength();
		}
		return super.visit(node);
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		String s = node.getName().toString();
		if (codeTYPE.equals(EnumCodeComponentTYPE.TYPEDECLARATION) && s.equals(ID))
		{
			offset = node.getStartPosition();
			length = node.getLength();
		}
		
		return super.visit(node);
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		String s = node.fragments().get(0).toString();
		s = s.contains("=") ? s.substring(0, s.indexOf('=')): s;
		if (codeTYPE.equals(EnumCodeComponentTYPE.FIELD) && s.equals(ID))
		{
			offset = node.getStartPosition();
			length = node.getLength();
		}
		return super.visit(node);
	}
}
