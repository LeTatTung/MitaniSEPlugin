package hut.eclipse;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

public class SourceFind {
	public static StringBuffer getParametersList(IMethodBinding methodBinding)
    {
    	if(methodBinding == null)
    		return new StringBuffer("()");
    	ITypeBinding[] paraTypes = methodBinding.getParameterTypes();
		StringBuffer paraList = new StringBuffer("(");
		// vi du paraList = , para0, para1, para2 
		boolean found = false;
		for (ITypeBinding paraBinding : paraTypes) {   
			if(paraBinding == null)
				continue;
			found = true;
			if(paraBinding.isPrimitive())
				paraList.append(",").append(paraBinding.getName());
			else if(paraBinding.isArray())   					
				paraList.append(",Array_").append(paraBinding.getElementType().getQualifiedName());    				
			else
				paraList.append(",").append(paraBinding.getBinaryName());    				
		}
		if(found) // neu nhu co it nhat mot parameter thi xoa 2 ki tu "," o dau di va them dau ) o cuoi
			paraList.delete(1, 2);
		paraList.append(')');
		return paraList;
    }
}
