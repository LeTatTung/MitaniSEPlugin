package hut.composite.querycreator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class CompositePopUp extends CompositeQuerySuper {

	private Map<String,String> classMap = new HashMap<String, String>();
	private Map<String, ArrayList<String>> classAndMethodMap = new HashMap<String, ArrayList<String>>();
	private Button buttonBindToMethod;
	private Table methodTable;
	private Table classTable;
	private Text text;
	final Label numbersOfClassesLabel;
	final Composite compositeTop;
	final Button okButton;
	final Composite compositeBottom;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	
	public CompositePopUp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		compositeTop = new Composite(this, SWT.NONE);
		final FormData fd_compositeTop = new FormData();
		fd_compositeTop.bottom = new FormAttachment(0, 40);
		fd_compositeTop.right = new FormAttachment(100, -5);
		fd_compositeTop.top = new FormAttachment(0, 5);
		fd_compositeTop.left = new FormAttachment(0, 5);
		compositeTop.setLayoutData(fd_compositeTop);
		compositeTop.setLayout(new FormLayout());
		toolkit.adapt(compositeTop);

		numbersOfClassesLabel = new Label(compositeTop, SWT.NONE);
		final FormData fd_numbersOfClassesLabel = new FormData();
		fd_numbersOfClassesLabel.top = new FormAttachment(0, 5);
		fd_numbersOfClassesLabel.left = new FormAttachment(0, 5);
		numbersOfClassesLabel.setLayoutData(fd_numbersOfClassesLabel);
		toolkit.adapt(numbersOfClassesLabel, true, true);
		numbersOfClassesLabel.setText("Numbers of classes to search:");

		text = new Text(compositeTop, SWT.BORDER);
		final FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(0, 154);
		fd_text.right = new FormAttachment(0, 230);
		fd_text.top = new FormAttachment(numbersOfClassesLabel, 0, SWT.TOP);
		text.setLayoutData(fd_text);
		toolkit.adapt(text, true, true);

		okButton = new Button(compositeTop, SWT.NONE);
		final FormData fd_okButton = new FormData();
		fd_okButton.top = new FormAttachment(text, 0, SWT.TOP);
		fd_okButton.left = new FormAttachment(0, 245);
		okButton.setLayoutData(fd_okButton);
		toolkit.adapt(okButton, true, true);
		okButton.setText("OK");
		okButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
			int numbersOfClass = Integer.valueOf(text.getText());
			for (int i = 0; i < numbersOfClass; i++)
			{
				addRowToClassTable(i + 1);
			}
			
			}
			});
		compositeBottom = new Composite(this, SWT.NONE);
		final FormData fd_compositeBottom = new FormData();
		fd_compositeBottom.bottom = new FormAttachment(100, -5);
		fd_compositeBottom.right = new FormAttachment(compositeTop, 0, SWT.RIGHT);
		fd_compositeBottom.top = new FormAttachment(compositeTop, 5, SWT.BOTTOM);
		fd_compositeBottom.left = new FormAttachment(compositeTop, 0, SWT.LEFT);
		compositeBottom.setLayoutData(fd_compositeBottom);
		compositeBottom.setLayout(new FormLayout());
		toolkit.adapt(compositeBottom);

		classTable = new Table(compositeBottom, SWT.BORDER);
		final FormData fd_classTable = new FormData();
		fd_classTable.bottom = new FormAttachment(100, -40);
		fd_classTable.top = new FormAttachment(0, 5);
		fd_classTable.left = new FormAttachment(0, 5);
		classTable.setLayoutData(fd_classTable);
		toolkit.adapt(classTable, true, true);
		classTable.setLinesVisible(true);
		classTable.setHeaderVisible(true);

		final TableColumn classTableColumn = new TableColumn(classTable, SWT.NONE);
		classTableColumn.setWidth(67);
		classTableColumn.setText("Class");

		final TableColumn nameTableColumn = new TableColumn(classTable, SWT.NONE);
		nameTableColumn.setWidth(55);
		nameTableColumn.setText("Name");

		final TableColumn numbersOfMethodTableColumn = new TableColumn(classTable, SWT.NONE);
		numbersOfMethodTableColumn.setWidth(161);
		numbersOfMethodTableColumn.setText("Numbers of method");

		methodTable = new Table(compositeBottom, SWT.BORDER);
		final FormData fd_methodTable = new FormData();
		fd_methodTable.right = new FormAttachment(100, -5);
		fd_methodTable.top = new FormAttachment(classTable, 0, SWT.TOP);
		methodTable.setLayoutData(fd_methodTable);
		toolkit.adapt(methodTable, true, true);
		methodTable.setLinesVisible(true);
		methodTable.setHeaderVisible(true);

		final TableColumn baseClassTableColumn = new TableColumn(methodTable, SWT.NONE);
		baseClassTableColumn.setWidth(57);
		baseClassTableColumn.setText("Class");

		final TableColumn methodTableColumn = new TableColumn(methodTable, SWT.NONE);
		methodTableColumn.setWidth(71);
		methodTableColumn.setText("Method");

		final TableColumn methodNameTableColumn = new TableColumn(methodTable, SWT.NONE);
		methodNameTableColumn.setWidth(100);
		methodNameTableColumn.setText("Method Name");

		buttonBindToMethod = new Button(compositeBottom, SWT.NONE);
		fd_methodTable.left = new FormAttachment(buttonBindToMethod, 0, SWT.RIGHT);
		fd_classTable.right = new FormAttachment(buttonBindToMethod, 0, SWT.LEFT);
		final FormData fd_buttonBindToMethod = new FormData();
		fd_buttonBindToMethod.left = new FormAttachment(0, 296);
		fd_buttonBindToMethod.right = new FormAttachment(0, 340);
		fd_buttonBindToMethod.top = new FormAttachment(0, 135);
		buttonBindToMethod.setLayoutData(fd_buttonBindToMethod);
		toolkit.adapt(buttonBindToMethod, true, true);
		buttonBindToMethod.setText(">>");
		buttonBindToMethod.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent event){
				TableItem[] itemList = classTable.getItems();
				for (TableItem tmpTableItem : itemList)
				{
					classMap.put((String)tmpTableItem.getData("Name"), (String)tmpTableItem.getData("Numbers"));
				}
				
				addRowToMethodTable();
			}
		});

		Button addButton;
		addButton = new Button(compositeBottom, SWT.NONE);
		fd_methodTable.bottom = new FormAttachment(addButton, -5, SWT.TOP);
		final FormData fd_addButton = new FormData();
		fd_addButton.left = new FormAttachment(buttonBindToMethod, -44, SWT.RIGHT);
		fd_addButton.right = new FormAttachment(buttonBindToMethod, 0, SWT.RIGHT);
		fd_addButton.top = new FormAttachment(classTable, 5, SWT.BOTTOM);
		addButton.setLayoutData(fd_addButton);
		toolkit.adapt(addButton, true, true);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent event){
				TableItem[] methodItemList = methodTable.getItems();
				ArrayList<String> classNameList = new ArrayList<String>(); 
				for (TableItem tmpTableItem : methodItemList)
				{
					
					classNameList.add((String)tmpTableItem.getData("ClassName"));
					
				}
				Collections.sort(classNameList);
				for (String className : classNameList)
				{
					ArrayList<String> methodNameList = new ArrayList<String>();
					for (TableItem tmpTableItem : methodItemList)
					{
						String tmpClassName = (String)tmpTableItem.getData("ClassName");
						if (className.equals(tmpClassName))
						{
							methodNameList.add((String)tmpTableItem.getData("MethodName"));
						}
					}
					classAndMethodMap.put(className,methodNameList);
				}
								
				setOutputData();
			}
		});
	}


	protected void setOutputData() {
		this.setOutputData(classAndMethodMap);
		
		Map<String,ArrayList<String>> tmpMap = (Map<String,ArrayList<String>>)this.getOutputData();
		for (String s : tmpMap.keySet())
		{
			System.out.println(s);
			System.out.println(tmpMap.get(s));
		}
		
		this.getQueryController().addClassFromPopUp();
		this.getQueryController().addRestrictionFromPopUp();
	}


	protected void addRowToMethodTable() {
		List<String> tmpList = new ArrayList<String>();
		for (String s : classMap.keySet())
		{
			tmpList.add(s);
		}
		Collections.sort(tmpList);
		for (String className : tmpList)
		{
			int numberOfMethod = Integer.valueOf(classMap.get(className));
			for (int i = 0; i < numberOfMethod; i++)
			{
				final TableItem item = new TableItem(methodTable,SWT.NONE);
				TableEditor editor = new TableEditor(methodTable);
				final Text classText = new Text(methodTable,SWT.NONE);
				classText.setText(className);
				editor.grabHorizontal = true;
				editor.setEditor(classText, item, 0);
				
				editor = new TableEditor(methodTable);
				final Text methodText = new Text(methodTable,SWT.NONE);
				methodText.setText(String.valueOf(i + 1));
				editor.grabHorizontal = true;
				editor.setEditor(methodText, item, 1);
				
				editor = new TableEditor(methodTable);
				final Text methodNameText = new Text(methodTable,SWT.NONE);
				editor.grabHorizontal = true;
				editor.setEditor(methodNameText, item, 2);
				
				methodNameText.addFocusListener(new FocusListener(){

					public void focusGained(FocusEvent arg0) {
						
						
					}

					public void focusLost(FocusEvent arg0) {
						
						item.setData("ClassName",classText.getText());
						item.setData("MethodName",methodNameText.getText());
						
					}
					
				});
				
			}
			
		}
		
		
	}


	protected void addRowToClassTable(int i) {
		final TableItem item = new TableItem(classTable,SWT.NONE);
		TableEditor editor = new TableEditor(classTable);
		
		final Text classText = new Text(classTable,SWT.NONE);
		classText.setText(String.valueOf(i));
		editor.grabHorizontal = true;
		editor.setEditor(classText, item, 0);
		
		editor = new TableEditor(classTable);
		final Text nameText = new Text(classTable,SWT.NONE);
		editor.grabHorizontal = true;
		editor.setEditor(nameText, item, 1);
		
		editor = new TableEditor(classTable);
		final Text numberText = new Text(classTable,SWT.NONE);
		editor.grabHorizontal = true;
		editor.setEditor(numberText, item, 2);
		
		nameText.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent arg0) {
				
				
			}

			public void focusLost(FocusEvent arg0) {
				item.setData("Name", nameText.getText());
				
			}
			
		});
		
		numberText.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent arg0) {
				
				
			}

			public void focusLost(FocusEvent arg0) {
				item.setData("Numbers", numberText.getText());
				
			}
			
		});
		item.setData("Class", classText.getText());
		
		
	}
	
}
