package reorder;

import reorder.SwapOperations.Bias;

public class SwapBackwardAction extends AbstractSwapAction {

    @Override
    protected Bias getBias() {
	// Swap with preceding item
        return Bias.BACKWARD;
    }
    
}
