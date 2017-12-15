package hut.composite.svn;

//import hut.composite.annotationEditor.CompositeDatatype;


import java.io.File;
import java.io.IOException;

import ontology.images.Images;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.dialogs.IFileStoreFilter;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;

import com.gmail.nxhoaf.lib.SvnManagement;


/**
 * class để cung cấp nội dung cho cây thư mục
 * @author Trung
 *
 */
class FileTreeContentProvider implements ITreeContentProvider
{
	/**
	 * Lấy về con cho một nút, ở đây chỉ lấy các con là thư mục
	 */
  public Object[] getChildren(Object element)
  {
    Object[] children = ((File) element).listFiles();
    if (children == null) return new Object[0];
    
    else
    {
    	int countDirectory = 0;
    	for (Object object : children)
    	{
    		if (((File)object).isDirectory()) countDirectory++; 
    	}
    	Object[] tmp = new Object[countDirectory];
    	int index = 0;
    	for (Object object : children)
    	{
    		if (((File)object).isDirectory()){
    			tmp[index] = object;
    			index++;
    		}
    	}
    	
    	if (tmp.length > 0) return tmp;
    	else return new Object[0];
    }
    	
    
    }
    
    
   // return children == null ? new Object[0] : children;
  
 /**
  * lấy thành phần cho gốc khi khởi tạo cây
  */
  public Object[] getElements(Object element)
  {
    return getChildren(element);
  }

  /**
   * kiểm tra nút hiện tại có nút con không
   */
  public boolean hasChildren(Object element)
  {
    return getChildren(element).length > 0;
  }

  /**
   * lấy về nút cha của nút hiện tại
   */
  public Object getParent(Object element)
  {
    return ((File)element).getParent();
  }
  
  public void dispose()
  {
  }

  public void inputChanged(Viewer viewer, Object old_input, Object new_input)
  {
  }
}

/**
 * lớp cung cấp nhãn cho cây thư mục
 * @author Trung
 *
 */
class FileTreeLabelProvider implements ILabelProvider
{
	/**
	 * trả về tên của nút
	 */
  public String getText(Object element)
  {
	 
    return ((File)element).getName();
   
  }
  
  /**
   * trả về ảnh làm icon cho nút
   */
  public Image getImage(Object element)
  {
	  String name = ((File)element).getName();
	  int dot = name.lastIndexOf(".");
	  String extention = name.substring(dot + 1);
	  if (((File)element).isDirectory())
	    {
		  
	      return Images.imageRegistry.get(Images.FOLDER);
	    }
	    else
	    {
	    	if (extention == "java")
	      return Images.imageRegistry.get(Images.FILE);
	    	else return null;
	    }

  }

public void addListener(ILabelProviderListener arg0) {
	// TODO Auto-generated method stub
	
}

public void dispose() {
	// TODO Auto-generated method stub
	
}

public boolean isLabelProperty(Object arg0, String arg1) {
	// TODO Auto-generated method stub
	return false;
}

public void removeListener(ILabelProviderListener arg0) {
	// TODO Auto-generated method stub
	
}

}

/**
 * Lớp cung cấp nội dung cho bảng hiển thị file
 * @author Trung
 *
 */
class FileTableContentProvider implements IStructuredContentProvider
{
	/**
	 * Trả về mảng các thành phần có trong file hiện tại
	 */
  public Object[] getElements(Object element)
  {
	  Object[] children = ((File) element).listFiles();
	    if (children == null) return new Object[0];
	    
	    else
	    {
	    	int countFile = 0;
	    	for (Object object : children)
	    	{
	    		if (((File)object).isFile()) countFile++; 
	    	}
	    	Object[] tmp = new Object[countFile];
	    	int index = 0;
	    	for (Object object : children)
	    	{
	    		if (((File)object).isFile()){
	    			tmp[index] = object;
	    			index++;
	    		}
	    	}
	    	
	    	if (tmp.length > 0) return tmp;
	    	else return new Object[0];
	    }
	    	
	    
  }

  public void dispose()
  {
  }

  public void inputChanged(Viewer viewer, Object old_object, Object new_object)
  {
  }
}

/**
 * lớp cung cấp nhãn cho bảng hiển thị file
 * @author Trung
 *
 */
class FileTableLabelProvider implements ITableLabelProvider
{
	/**
	 * trả về nội dung văn bản cho từng cột
	 */
  public String getColumnText(Object element, int column_index)
  {
	  if (column_index == 0)
	    {
	      return ((File) element).getName();
	    }

	    if (column_index == 1)
	    {
	      return "" + ((File) element).length() + " bytes";
	    }

	    return "";

  }

  public void addListener(ILabelProviderListener ilabelproviderlistener)
  {
  }

  public void dispose()
  {
  }

  public boolean isLabelProperty(Object obj, String s)
  {
    return false;
  }

  public void removeListener(ILabelProviderListener ilabelproviderlistener)
  {
  }
  
  /**
   * trả về ảnh làm icon cho từng cột
   */
  public Image getColumnImage(Object element, int column_index) {
	  
	  if (column_index != 0) return null;
	  
	  else
	  {
	  if (((File)element).isDirectory())
	    {
	      return Images.imageRegistry.get(Images.FOLDER);
	    }
	  else{
		  String name = ((File)element).getName();
		  if (!name.contains(".")) return Images.imageRegistry.get(Images.FILE);
		  else {
			  int dot = name.lastIndexOf(".");
			  String extention = name.substring(dot + 1);
			  if (extention.equals("java")) return Images.imageRegistry.get(Images.JAVA);
			  else return Images.imageRegistry.get(Images.FILE);
		  }
	  }
	  
	  }
		  
		 


}
}

/**
 * lớp dùng để sắp xếp các nút trong view theo thứ tự bảng chữ cái
 * @author Trung
 *
 */
class FileSorter extends ViewerSorter
{
  public int category(Object element)
  {
    return ((File) element).isDirectory() ? 0 : 1;
  }
}

/**
 * lớp dùng để lọc ra thư mục đưa vào cây hiển thị thư mục
 * @author Trung
 *
 */
class AllowOnlyFoldersFilter extends ViewerFilter
{
  public boolean select(Viewer viewer, Object parent, Object element)
  {
    return ((File) element).isDirectory();
  }
}


public class SVNViewPart extends ViewPart {

	private String svnRepository = "file:///C:/repository";
	private String workingCopyPath = "C:/WorkingCopy";
	private SvnManagement svnManager;
	private File selected_folder;
	private String newName;
	private Table table;
	private TableViewer tableViewer;
	private Tree tree;
	private TreeViewer treeViewer;
	public static final String ID = "hut.composite.svn.SVNViewPart"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(Display.getCurrent());
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		container.setLayout(new FormLayout());
		toolkit.paintBordersFor(container);
		
		svnManager = new SvnManagement(svnRepository,workingCopyPath);

		final SashForm sashForm = new SashForm(container, SWT.NONE);

		final Composite treeComposite = new Composite(sashForm, SWT.NONE);
		treeComposite.setLayout(new FormLayout());
		toolkit.adapt(treeComposite);

		treeViewer = new TreeViewer(treeComposite, SWT.NONE);
		treeViewer.setContentProvider(new FileTreeContentProvider());
		treeViewer.setLabelProvider(new FileTreeLabelProvider());
		treeViewer.addFilter(new AllowOnlyFoldersFilter());
		treeViewer.setInput(new File("C:\\WorkingCopy"));
		tree = treeViewer.getTree();
		final FormData fd_tree = new FormData();
		fd_tree.bottom = new FormAttachment(100, -5);
		fd_tree.right = new FormAttachment(100, -5);
		fd_tree.left = new FormAttachment(0, 0);
		fd_tree.top = new FormAttachment(0, 5);
		tree.setLayoutData(fd_tree);
		
		toolkit.adapt(tree, true, true);
		final FormData fd_sashForm = new FormData();
		fd_sashForm.bottom = new FormAttachment(100, -5);
		fd_sashForm.right = new FormAttachment(100, -5);
		fd_sashForm.top = new FormAttachment(0, 5);
		fd_sashForm.left = new FormAttachment(0, 5);
		sashForm.setLayoutData(fd_sashForm);
		toolkit.adapt(sashForm, true, true);

		final Composite tableComposite = new Composite(sashForm, SWT.NONE);
		tableComposite.setLayout(new FormLayout());
		toolkit.adapt(tableComposite);

		tableViewer = new TableViewer(tableComposite, SWT.NONE|SWT.FULL_SELECTION|SWT.MULTI);
		tableViewer.setContentProvider(new FileTableContentProvider());
		tableViewer.setLabelProvider(new FileTableLabelProvider());
		tableViewer.setSorter(new FileSorter());
		//tableViewer.setFilters(new AllowOnlyFilesFilter());
		table = tableViewer.getTable();
		
		Menu menu = new Menu(table);
		
		/**
		 * open a java file in editor
		 */
		MenuItem openJavaFile = new MenuItem(menu,SWT.PUSH);
		openJavaFile.setText("Open");
		openJavaFile.setImage(Images.imageRegistry.get(Images.BOOKMARK));
		openJavaFile.addSelectionListener(new SelectionAdapter(){
			 public void widgetSelected(SelectionEvent event) {
				 IStructuredSelection selection = (IStructuredSelection)tableViewer.getSelection(); 
			     if (selection.size() != 1)
			     {
			       MessageDialog.openInformation(new Shell(), "Caution!", "Please choose a file to open!");
			       return;
			     }

			     File selected_file = (File) selection.getFirstElement();
			     if (selected_file.isFile())
			     {
			    	String fileName = selected_file.getName();
			    	if (!fileName.contains(".")) 
			    		{
			    		MessageDialog.openInformation(new Shell(), "Caution!", "Please choose a file to open!");
			    		return;
			    		}
			    	else {
			    		int dot = fileName.lastIndexOf(".");
			    		String extention = fileName.substring(dot + 1);
			    		if (!extention.equals("java")) 
			    			{
			    			MessageDialog.openInformation(new Shell(), "Caution!", "Please choose a java file to open!");
			    			return;
			    			}
			    		else {
			    			
			    			IFileStore fileStore = EFS.getLocalFileSystem().getStore(selected_file.toURI());
			    		    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			    		    try {
			    		        IDE.openEditorOnFileStore( page, fileStore );
			    		    } catch ( PartInitException e ) {
			    		        
			    		    }

			    		}
			    	}
			     }

			    }
		});
		
		
		/**
		 * delete a file
		 */
		MenuItem deleteItem = new MenuItem(menu,SWT.PUSH);
		deleteItem.setText("Delete");
		deleteItem.setImage(Images.imageRegistry.get(Images.DELETE));
		deleteItem.addSelectionListener(new SelectionAdapter(){
			 public void widgetSelected(SelectionEvent event) {
				 IStructuredSelection selection = (IStructuredSelection)tableViewer.getSelection(); 
			     if (selection.size() != 1)
			     {
			       MessageDialog.openInformation(new Shell(), "Caution!", "Please choose a file to delete!");
			       return;
			     }

			     File selected_file = (File) selection.getFirstElement();
			     if (selected_file.isFile())
			     {
			    	 boolean b = MessageDialog.openConfirm(new Shell(), "Confirm", "Do you really want to delete this file?");
			    	 if (b == true){
			    		 svnManager.delete(selected_file.getAbsolutePath(), true);
			    		 selected_file.delete();
			    		 tableViewer.refresh();
			    	 }
			     }

			    }
		});
		
		MenuItem addFileItem = new MenuItem(menu,SWT.PUSH);
		addFileItem.setText("Add");
		addFileItem.setImage(Images.imageRegistry.get(Images.ADD));
		addFileItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				IStructuredSelection selection = (IStructuredSelection)treeViewer.getSelection();
				selected_folder = (File) selection.getFirstElement();
				Shell shell = new Shell();
				shell.setText("Editor");
				shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
				shell.setLayout(new FillLayout());
				shell.setSize(300,250);
				
				int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
				int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
				shell.setLocation(x,y);
				
				final NameComposite nameComposite = new NameComposite(shell,SWT.NONE);
				shell.addListener(SWT.Close, new Listener() {
					public void handleEvent(Event e) {
						String s = nameComposite.getValue();
						if (s!= null)
						{
							newName = s + ".java";
							File newFile = new File(selected_folder,newName);
							
							//svnManager.addEntry(newFile.getAbsolutePath());
							try {
								System.out.println(newFile.createNewFile());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							svnManager.addEntry(newFile.getAbsolutePath());
							tableViewer.refresh();
						}
						nameComposite.dispose();
					}
				});
				
				shell.open();
				
			}
		});
		
		
		table.setMenu(menu);
		
		

	    //tbv.getTable().setMenu(menu_manager.createContextMenu(tbv.getTable()));

		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(0, 270);
		fd_table.right = new FormAttachment(100, -5);
		fd_table.top = new FormAttachment(0, 0);
		fd_table.left = new FormAttachment(0, 0);
		table.setLayoutData(fd_table);
		toolkit.adapt(table, true, true);
		table.setLinesVisible(false);
		table.setHeaderVisible(true);
		TableColumn column1 = new TableColumn(table, SWT.LEFT);
	    column1.setText("Name");
	    column1.setWidth(200);
	    TableColumn column2 = new TableColumn(table, SWT.LEFT);
	    column2.setText("Size");
	    column2.setWidth(200);

		Composite buttonComposite;
		buttonComposite = new Composite(tableComposite, SWT.NONE);
		final FormData fd_buttonComposite = new FormData();
		fd_buttonComposite.top = new FormAttachment(table, 5, SWT.BOTTOM);
		fd_buttonComposite.right = new FormAttachment(table, 0, SWT.RIGHT);
		fd_buttonComposite.left = new FormAttachment(table, 0, SWT.LEFT);
		fd_buttonComposite.bottom = new FormAttachment(100, -5);
		buttonComposite.setLayoutData(fd_buttonComposite);
		buttonComposite.setLayout(new FormLayout());
		toolkit.adapt(buttonComposite);

		final Button checkOutButton = new Button(buttonComposite, SWT.NONE);
		checkOutButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent event) {
				boolean isRecursive = true;
				try {
					long revision = svnManager.checkout(svnManager.getSvnRepositoryUrl().toString(), SVNRevision.HEAD, svnManager.getWorkingCopyPath(), isRecursive);
					treeViewer.refresh();
					MessageDialog.openInformation(new Shell(), "Checkout successfully !", "This working copy has been checkouted at revision " + revision);
					
				} catch (Exception e) {
					MessageDialog.openError(new Shell(), "Caution !", "This working copy has been checkouted before!");
				}
			}
		});
		final FormData fd_checkOutButton = new FormData();
		fd_checkOutButton.right = new FormAttachment(25, 0);
		fd_checkOutButton.left = new FormAttachment(2, 0);
		fd_checkOutButton.top = new FormAttachment(100, -28);
		fd_checkOutButton.bottom = new FormAttachment(100, -5);
		checkOutButton.setLayoutData(fd_checkOutButton);
		toolkit.adapt(checkOutButton, true, true);
		checkOutButton.setText("CheckOut");
		checkOutButton.setImage(Images.imageRegistry.get(Images.DESCRIBE));

		Button updateButton;
		updateButton = new Button(buttonComposite, SWT.NONE);
		updateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				boolean isRecursive = true;
				try {
					long revision = svnManager.update(svnManager.getWorkingCopyPath(), SVNRevision.HEAD, isRecursive);
					MessageDialog.openInformation(new Shell(), "Update successfully !", "This working copy has been updated to revision " + revision);
				}
				catch(Exception exception){
					
				}
				
			}
		});
		final FormData fd_updateButton = new FormData();
		fd_updateButton.left = new FormAttachment(checkOutButton, 34, SWT.DEFAULT);
		fd_updateButton.top = new FormAttachment(100, -28);
		fd_updateButton.bottom = new FormAttachment(100, -5);
		updateButton.setLayoutData(fd_updateButton);
		toolkit.adapt(updateButton, true, true);
		updateButton.setText("Update");
		updateButton.setImage(Images.imageRegistry.get(Images.CHANGE_LEFT));

		Button commitButton;
		commitButton = new Button(buttonComposite, SWT.NONE);
		fd_updateButton.right = new FormAttachment(commitButton, -39, SWT.LEFT);
		commitButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String commitMessage = "Commit";
				boolean isKeepLock = false;
				svnManager.filesToCommit(svnManager.getWorkingCopyPath(), isKeepLock, commitMessage);
				SVNCommitInfo commitInfo = svnManager.commit(svnManager.getWorkingCopyPath(), isKeepLock, commitMessage);
				if (commitInfo.getNewRevision() != -1) MessageDialog.openInformation(new Shell(), "Commit successfully !", "Committed to revision " + commitInfo.getNewRevision());
				else MessageDialog.openInformation(new Shell(), "Caution !", "There's nothing to commit!");
			}
		});
		final FormData fd_commitButton = new FormData();
		fd_commitButton.left = new FormAttachment(78, 0);
		fd_commitButton.right = new FormAttachment(98, 0);
		fd_commitButton.bottom = new FormAttachment(updateButton, 23, SWT.TOP);
		fd_commitButton.top = new FormAttachment(updateButton, 0, SWT.TOP);
		commitButton.setLayoutData(fd_commitButton);
		toolkit.adapt(commitButton, true, true);
		commitButton.setText("Commit");
		commitButton.setImage(Images.imageRegistry.get(Images.CHANGE_RIGHT));

		Button refreshButton;
		refreshButton = new Button(buttonComposite, SWT.NONE);
		final FormData fd_refreshButton = new FormData();
		fd_refreshButton.right = new FormAttachment(updateButton, 0, SWT.RIGHT);
		fd_refreshButton.bottom = new FormAttachment(updateButton, -2, SWT.DEFAULT);
		fd_refreshButton.left = new FormAttachment(updateButton, 0, SWT.LEFT);
		refreshButton.setLayoutData(fd_refreshButton);
		refreshButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				treeViewer.refresh();
				tableViewer.refresh();
			}
		});
		toolkit.adapt(refreshButton, true, true);
		refreshButton.setText("Refresh");
		refreshButton.setImage(Images.imageRegistry.get(Images.REFRESH));

		
		sashForm.setWeights(new int[] {1, 1 });
		
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
	    {
	      public void selectionChanged(SelectionChangedEvent event)
	      {
	        IStructuredSelection selection =
	          (IStructuredSelection) event.getSelection();

	        Object selected_file = selection.getFirstElement();
	        tableViewer.setInput(selected_file);
	      }
	    });

		//
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
