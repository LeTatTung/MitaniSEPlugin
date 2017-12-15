package hut.views.notification;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import hut.dialog.FavoritesDialog;
import hut.wizard.SelectComponentWizard;
import mintani.valueconst.ConsistentOntology;




import ontology.images.Images;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;


import swing2swt.layout.BorderLayout;

public class ViewNotification extends Composite {
	private Text text; 
	private ResultNotificationView resultNotificationView;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Button selectButton;
	private Button addToFavoriteButton;
	private Button selectFromFavoriteButton;
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public ViewNotification(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final CTabFolder tabFolder = new CTabFolder(this, SWT.NONE);
		toolkit.adapt(tabFolder, true, true);
		tabFolder.setSimple(false);
		final CTabItem viewNotificationTabItem = new CTabItem(tabFolder, SWT.NONE);
		viewNotificationTabItem.setImage(Images.imageRegistry.get(Images.WARNING));
		viewNotificationTabItem.setText("View Notification");

		final Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite);
		viewNotificationTabItem.setControl(composite);

		final Composite notificationComposite = new Composite(composite, SWT.NONE);
		notificationComposite.setLayoutData(BorderLayout.NORTH);

		text = new Text(notificationComposite, SWT.BORDER);		
		text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		text.setEditable(false);
		text.setBounds(79, 7, 481, 20);
		toolkit.adapt(notificationComposite);
		

		selectButton = new Button(notificationComposite, SWT.NONE | SWT.WRAP);
		selectButton.setImage(Images.imageRegistry.get(Images.SELECT));
		selectButton.setText("Select");
		selectButton.setBounds(8, 5, 65, 26);
		
		
		addToFavoriteButton = new Button(notificationComposite, SWT.NONE);
		addToFavoriteButton.setImage(Images.imageRegistry.get(Images.STAR));
		addToFavoriteButton.setText("Add to list");
		addToFavoriteButton.setBounds(79, 33, 117, 23);
		
		
		selectFromFavoriteButton = new Button(notificationComposite, SWT.NONE);
		selectFromFavoriteButton.setImage(Images.imageRegistry.get(Images.SELECTFAVORITES));
		selectFromFavoriteButton.setText("Select from list");
		selectFromFavoriteButton.setBounds(202, 33, 131, 23);
		
		
		final Button queryButton = new Button(notificationComposite, SWT.NONE);
		queryButton.setImage(Images.imageRegistry.get(Images.RUN));
		queryButton.setText("View");
		queryButton.setBounds(342, 33, 63, 23);
		
		
		
		resultNotificationView = new ResultNotificationView(composite, SWT.NONE);
		resultNotificationView.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(resultNotificationView);
		resultNotificationView.setLayoutData(BorderLayout.CENTER);
		
		tabFolder.setSelection(0);
		//
		
		registerAction();
	}
	
	public void registerAction(){
		
		//Chon 1 thanh phan trong ontology
		selectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				SelectComponentWizard wizard = new SelectComponentWizard(text);
				WizardDialog dialog = new WizardDialog(((Button)e.widget).getShell(),wizard);
				dialog.setPageSize(500, 300);
				dialog.open();
			}
		});
		//Add vao file list file select.property
		addToFavoriteButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if(text.getText().equals(""))
					return;
				try {
					InputDialog dialog = new InputDialog(text.getShell(), "Enter favorites name","Name"
							,text.getText().substring(text.getText().indexOf('#')+1),
							new IInputValidator(){
								public String isValid(String text) {
									if(text.trim().equals(""))
										return "Name can't be empty";
									return null;
								}});
					if(dialog.open() == InputDialog.OK)
					{
						String input = dialog.getValue();
						Properties props = new Properties();
						try {
							InputStream in = new FileInputStream(new File(ConsistentOntology.selectFile));
							props.load(in);
							
							props.put(text.getText(), input);
							
							OutputStream out = new FileOutputStream(new File(ConsistentOntology.selectFile));
							props.store(out, "favorites file");
							
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				catch(NullPointerException ex) // press cancel
				{}
			}
		});	
		
		//Select tu list
		selectFromFavoriteButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				FavoritesDialog dialog = new FavoritesDialog(((Button)e.widget).getShell(), text);
				dialog.open();
			}
		});
		
	}

}
