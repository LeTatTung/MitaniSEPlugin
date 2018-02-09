package mintaniseplugin.views.assistantView;

import hut.composite.assistant.CommentManager;
import hut.composite.assistant.MethodCallHierarchy;
import hut.composite.assistant.SeCommentManager;
import hut.composite.metricChart.MetricChart;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.part.ViewPart;
import hut.composite.login.CompositeLogin;

public class AssistantView extends ViewPart {

	public static final String ID = "mintaniseplugin.views.artifactView.ArtifactView"; //$NON-NLS-1$
    private CommentManager commentManager;
    private SeCommentManager seCommentManager;
    private MethodCallHierarchy methodCallHierarchy;
    private MetricChart chart; 
	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		String  pathProject = System.getProperty("user.dir");
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IPath location = root.getLocation();
		/*location.toString();
		ResourcesPlugin.getWorkspace().getRoot();*/
		
		final TabFolder tabFolder = new TabFolder(parent, SWT.BOTTOM);

		final TabItem manageCommentTabItem = new TabItem(tabFolder, SWT.NONE);
		manageCommentTabItem.setText("Manage Comment");

		commentManager = new CommentManager(tabFolder, SWT.NONE);
		manageCommentTabItem.setControl(commentManager);
		
		final TabItem seManageCommentTabItem = new TabItem(tabFolder, SWT.NONE);
		seManageCommentTabItem.setText("Semantic Comment");

		seCommentManager = new SeCommentManager(tabFolder, SWT.NONE);
		seManageCommentTabItem.setControl(seCommentManager);
		
		final TabItem callHireTabItem = new TabItem(tabFolder, SWT.NONE);
		callHireTabItem.setText("Call Hierachy");

		methodCallHierarchy = new MethodCallHierarchy(tabFolder, SWT.NONE);
		callHireTabItem.setControl(methodCallHierarchy);

		final TabItem metricViewTabItem = new TabItem(tabFolder, SWT.NONE);
		metricViewTabItem.setText("Metric View");

		final Composite compositeMetric = new Composite(tabFolder, SWT.NONE);
		compositeMetric.setLayout(new FillLayout());
		metricViewTabItem.setControl(compositeMetric);
		chart = new MetricChart(compositeMetric, SWT.NONE);

		final TabItem loginTabItem = new TabItem(tabFolder, SWT.NONE);
		loginTabItem.setText("login");

		final CompositeLogin compositeLogin = new CompositeLogin(tabFolder, SWT.NONE);
		loginTabItem.setControl(compositeLogin);
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
