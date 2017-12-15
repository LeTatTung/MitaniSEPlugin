package hut.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;

public class DateTimeDialog extends Dialog {
	private DateTime calendar;
	private DateTime time;
	
	public int Year;
	public int Month;
	public int Day;
	public int Hour;
	public int Minute;
	public int Second;
	
	public String SYear;
	public String SMonth;
	public String SDay;
	public String SHour;
	public String SMinute;
	public String SSecond;
	
	public DateTimeDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);

		calendar = new DateTime(container, SWT.CALENDAR|SWT.BORDER);
		calendar.setLayoutData(new GridData());

		time = new DateTime(container, SWT.TIME);
		return container;
	}


	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,true);
		createButton(parent, IDialogConstants.CANCEL_ID,IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(289, 240);
	}
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Time Dialog");
	}
	@Override
	protected void okPressed() {
		Year=calendar.getYear();
		Month=calendar.getMonth()+1;//Month trong widget dc tinh tu 0 den 11
		Day=calendar.getDay();
		Hour=time.getHours();
		Minute=time.getMinutes();
		Second=time.getSeconds();
		
		SYear=Year+"";
		SMonth=(Month<10?"0"+Month:""+Month);
		SDay=(Day<10?"0"+Day:""+Day);
		SHour=(Hour<10?"0"+Hour:""+Hour);
		SMinute=(Minute<10?"0"+Minute:""+Minute);
		SSecond=(Second<10?"0"+Second:""+Second);
		super.okPressed();		
	}

}
