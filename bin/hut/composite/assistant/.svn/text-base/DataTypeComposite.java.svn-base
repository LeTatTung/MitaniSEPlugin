package hut.composite.assistant;




import mitani.activator.Activator;
import ontology.images.Images;

import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class DataTypeComposite extends Composite {

	private Text text;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	
	private static final String[] SAVE={"Save", "Ghi lại"};
	private static final String[] EXIT={"Exit", "Thoát"};
	
	private ToolItem newItemToolItem;
	private ToolItem newItemToolItem_1;

	public DataTypeComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite composite = new Composite(this, SWT.BORDER);
		final FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(100, 0);
		fd_composite.bottom = new FormAttachment(0, 45);
		fd_composite.top = new FormAttachment(0, 0);
		fd_composite.left = new FormAttachment(0, 0);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		
		final ToolBar toolBar = new ToolBar(composite, SWT.NONE);
		toolkit.adapt(toolBar, true, true);
		toolBar.setBounds(0, 0, 90, 37);

		newItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem.setImage(Images.imageRegistry.get(Images.SAVE));
		newItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				toolBar.getShell().close();
			}
		});

		newItemToolItem_1 = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				text.setText("");
				toolBar.getShell().close();
			}
		});
		newItemToolItem_1.setImage(Images.imageRegistry.get(Images.DELETE));

		text = new Text(this, SWT.V_SCROLL | SWT.BORDER | SWT.H_SCROLL);
		final FormData fd_text = new FormData();
		fd_text.bottom = new FormAttachment(100, 0);
		fd_text.right = new FormAttachment(100, 0);
		fd_text.top = new FormAttachment(0, 44);
		fd_text.left = new FormAttachment(0, 0);
		text.setLayoutData(fd_text);
		toolkit.adapt(text, true, true);
		
		final IPropertyChangeListener propertyChangeListener
		   = new IPropertyChangeListener() {
		   public void propertyChange(PropertyChangeEvent event) {
		     /* if (event.getProperty().equals(PreferenceConstants.P_LANGUAGE))
		      {*/
		    	  /*int index=Activator.getDefault().getPreferenceStore().getInt(PreferenceConstants.LANGUAGE_INDEX);
		    	  ChangeLanguage(index);*/
		      /*}*/
		   }
		};
		Activator.getDefault().getPluginPreferences().addPropertyChangeListener(propertyChangeListener);
		
		try//Duoc dung de test chuc nang test/preview, thuc chat ko can try catch.
		{
			/*int index=Activator.getDefault().getPreferenceStore().getInt(PreferenceConstants.LANGUAGE_INDEX);
			ChangeLanguage(index);*/
		}
		catch (Exception e)
		{
			ChangeLanguage(0);
		}

	}
	
	public String getValue()
	{
		if (text.getText()=="")
			return null;
		return text.getText();
	}

	public void setValue(String value)
	{
		text.setText(value);
	}
	
	public void ChangeLanguage(int index)
	{
		newItemToolItem.setText(SAVE[index]);
		newItemToolItem_1.setText(EXIT[index]);
	}
}
