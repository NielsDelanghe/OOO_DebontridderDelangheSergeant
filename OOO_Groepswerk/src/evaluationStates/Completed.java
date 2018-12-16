package evaluationStates;

import model.Test;

public class Completed implements EvaluationState{

    private Test test;

    public Completed(Test test){this.test=test;}

    @Override
    public void neverCompleted() {
        //throw new IllegalStateException("You have already completed this test, you cant set it to not completed");
        test.changeState(test.getCompletedState());
    }

    @Override
    public void completed() {
        //throw new IllegalStateException("You have already completed this test");
        test.changeState(test.getCompletedState());
    }

    @Override
    public String toString() {
        return "You have completed this test before";
    }
}
