package hut.wizard;
import hut.wizard.SelectComponentWizardPage;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Text;
public class SelectComponentWizard extends Wizard {
	private SelectComponentWizardPage selectComponentPage;
	private Text text;
	public SelectComponentWizard(Text text) {
		this.text = text;
		List<String> components = new ArrayList<String>();
		components.add("Method");
		components.add("Class");
		components.add("Package");
		components.add("Field");
		components.add("MethodSignature");
		
		selectComponentPage = new SelectComponentWizardPage(components);
		selectComponentPage.setISelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent e) {
				Object item =  ((IStructuredSelection)e.getSelection()).getFirstElement();
				if(item == null)
					return;
				boolean select = selectComponentPage.getTableViewer().getChecked(item);
				selectComponentPage.getTableViewer().setAllChecked(false);
				selectComponentPage.getTableViewer().setChecked(item, !select);
			}});
		addPage(selectComponentPage);
		
		setWindowTitle("Select a component");
	}

	public boolean performFinish() {
		Object[] checked = selectComponentPage.getSelectedComponent();
		for (Object check : checked) {
			if (check instanceof String[]) {
				String[] row = (String[]) check;
				// lay ve URI, row[0] la checkbox
				text.setText(row[0]);				
			}
		}
		return true;
	}

}