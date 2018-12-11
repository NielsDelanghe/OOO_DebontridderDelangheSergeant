package evaluationStates;

import model.Test;

public class NeverCompleted implements EvaluationState {

    Test test;

    public NeverCompleted(Test test){this.test=test;}

    @Override
    public void neverCompleted() {
        throw new IllegalStateException("You never completed this test");
    }

    @Override
    public void completed() {

        test.changeState(test.getCompletedState());

    }
}
