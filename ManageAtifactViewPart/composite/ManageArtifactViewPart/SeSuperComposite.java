package composite.ManageArtifactViewPart;

//import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

import controller.ManageArtifactViewPart.SeControllerArtifactViewPart;

public abstract class SeSuperComposite extends Composite {

  private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
  private Object outputData;
  private Object inputData;
  private SeControllerArtifactViewPart controller;



  public SeControllerArtifactViewPart getController() {
    return controller;
  }




  public void setController(SeControllerArtifactViewPart controller) {
    this.controller = controller;
  }


  abstract int updateInterface();

  /**
   * Create the composite
   * @param parent
   * @param style
   */
  public SeSuperComposite(Composite parent, int style) {
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
