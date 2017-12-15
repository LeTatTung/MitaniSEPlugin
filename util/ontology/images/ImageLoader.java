package ontology.images;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
public class ImageLoader 
{
	static public ImageIcon createImageIcon(String path)
	{
		java.net.URL url = ImageLoader.class.getResource(path);	    
		if(url!=null)
			return new ImageIcon(url);
		System.out.println("can't not file image at path: " + path);
		return null;
	}
	static public java.awt.Image createImage(String path)
	{
		return Toolkit.getDefaultToolkit().getImage(path);
	}
	static public org.eclipse.swt.graphics.Image createJFaceImage(String path)
	{
//		 try {
			return new org.eclipse.swt.graphics.Image(null, path); 
					//new FileInputStream(path));
//		} catch (FileNotFoundException e) {		
//			e.printStackTrace();
//			return null;
//		}
	}
}
