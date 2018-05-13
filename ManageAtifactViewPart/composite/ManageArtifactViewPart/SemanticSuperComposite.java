package composite.ManageArtifactViewPart;

//import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

import controller.ManageArtifactViewPart.SemanticControllerManageArtifactViewPart;

public abstract class SemanticSuperComposite extends Composite {

  private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
  private Object outputData;
  private Object inputData;
  private SemanticControllerManageArtifactViewPart controller;



  public SemanticControllerManageArtifactViewPart getController() {
    return controller;
  }




  public void setController(SemanticControllerManageArtifactViewPart controller) {
    this.controller = controller;
  }


  abstract int updateInterface();

  /**
   * Create the composite
   * @param parent
   * @param style
   */
  public SemanticSuperComposite(Composite parent, int style) {
    super(parent, style);
    toolkit.adapt(this);
    toolkit.paintBordersFor(this);
    //
  }



  public Object getOutputData() {
    return outputData;
  }



  public void setOutputData(Object outputData) {
    this.outputData = outputData;
  }



  public Object getInputData() {
    return inputData;
  }



  public void setInputData(Object inputData) {
    this.inputData = inputData;
  }

}
