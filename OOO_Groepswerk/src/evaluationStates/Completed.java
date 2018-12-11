package evaluationStates;

import model.Test;

public class Completed implements EvaluationState{

    private Test test;

    public Completed(Test test){this.test=test;}

    @Override
    public void neverCompleted() {
        throw new IllegalStateException("You have already completed this test, you cant set it to not completed");
    }

    @Override
    public void completed() {
        throw new IllegalStateException("You have already completed this test");
    }
}
