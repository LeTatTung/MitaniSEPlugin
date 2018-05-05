package reorder;

import reorder.SwapOperations.Bias;

public class SwapForwardAction extends AbstractSwapAction {

    @Override
    protected Bias getBias() {
	// Swap with following item
        return Bias.FORWARD;
    }
    
}
