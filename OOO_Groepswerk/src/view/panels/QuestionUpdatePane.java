package view.panels;

import controller.DBContext;
import db.CategoryTXT;
import db.QuestionTXT;
import db.Savable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Category;
import model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionUpdatePane extends GridPane {
    private Question originalQuestion;
    private Button btnOK, btnCancel;
    private TextArea statementsArea;
    private TextField questionField, statementField, feedbackField;
    private Button btnAdd, btnRemove;
    private ComboBox categoryField;
    private List<String> statementList = new ArrayList<>();
    private DBContext categoryContext;
    private ObservableList<Savable> questionList, categoryList;
    private ObservableList<String> categoryTitles = FXCollections.observableArrayList(new ArrayList<>());

    public QuestionUpdatePane(ObservableList<Savable> questions, Question question, ObservableList<Savable> categories) {
        this.originalQuestion = question;
        this.questionList = questions;
        this.categoryList = categories;

        this.setPrefHeight(300);
        this.setPrefWidth(320);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        //----------------------------------------------------------------------
        add(new Label("Question: "), 0, 0, 1, 1);
        questionField = new TextField();
        add(questionField, 1, 0, 2, 1);
        //----------------------------------------------------------------------
        add(new Label("Statement: "), 0, 1, 1, 1);
        statementField = new TextField();
        add(statementField, 1, 1, 2, 1);
        //----------------------------------------------------------------------
        add(new Label("Statements: "), 0, 2, 1, 1);
        statementsArea = new TextArea();
        statementsArea.setPrefRowCount(5);
        statementsArea.setEditable(false);
        add(statementsArea, 1, 2, 2, 5);
        //----------------------------------------------------------------------
        Pane addRemove = new HBox();
        btnAdd = new Button("add");
        addRemove.getChildren().add(btnAdd);
        //----------------------------------------------------------------------
        btnRemove = new Button("remove");
        addRemove.getChildren().add(btnRemove);
        add(addRemove, 1, 8, 2, 1);
        //----------------------------------------------------------------------
        add(new Label("Category: "), 0, 9, 1, 1);
        categoryField = new ComboBox();
        add(categoryField, 1, 9, 2, 1);
        categoryContext = new DBContext();
        categoryContext.setStrategy(new CategoryTXT("resources/db/CategoryFile.txt",questionList));
        categoryContext.read();
        for(Savable category : categoryList)
        {
            categoryTitles.add(((Category)category).getName());
        }
        categoryField.setItems(categoryTitles);
        //----------------------------------------------------------------------
        add(new Label("Feedback: "), 0, 10, 1, 1);
        feedbackField = new TextField();
        add(feedbackField, 1, 10, 2, 1);
        //----------------------------------------------------------------------
        btnCancel = new Button("Cancel");
        btnCancel.setText("Cancel");
        add(btnCancel, 0, 11, 1, 1);
        //----------------------------------------------------------------------
        btnOK = new Button("Save");
        btnOK.isDefaultButton();
        btnOK.setText("Save");
        add(btnOK, 1, 11, 2, 1);
        //----------------------------------------------------------------------
        setCancelAction(new Close());
        setSaveAction(new AddQuestion());
        setAddStatementAction(new AddStatement());
        setRemoveStatementAction(new RemoveStatement());
    }

    public void setQuestionField(String question)
    {
        this.questionField.setText(question);
    }

    public void setStatementList(List<String> statements)
    {
        String statement = "";
        this.statementList.addAll(statements);
        for(String text : statementList)
        {
            statement += text + "\n";
        }
        statementsArea.setText(statement);
    }

    public void setFeedbackField(String feedback)
    {
        this.feedbackField.setText(feedback);
    }

    public void setSaveAction(EventHandler<ActionEvent> saveAction) { btnOK.setOnAction(saveAction); }

    public void setCancelAction(EventHandler<ActionEvent> cancelAction) { btnCancel.setOnAction(cancelAction); }

    public void setAddStatementAction(EventHandler<ActionEvent> addStatementAction) { btnAdd.setOnAction(addStatementAction); }

    public void setRemoveStatementAction(EventHandler<ActionEvent> removeStatementAction) { btnRemove.setOnAction(removeStatementAction); }

    class AddQuestion implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Question question = new Question(questionField.getText().trim(),String.valueOf(categoryField.getValue()).trim(), feedbackField.getText().trim(), 1,false,statementList);
            int index = 0;
            for(int i = 0; i < questionList.size(); i++)
            {
                if(originalQuestion.equals(questionList.get(i)))
                {
                    index = i;
                }
            }
            questionList.set(index,question);
            DBContext context = new DBContext();
            context.setStrategy(new QuestionTXT("resources/db/QuestionFile.txt", questionList));
            context.write();
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
        }
    }

    class AddStatement implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            statementList.add(statementField.getText());
            String current = statementsArea.getText();
            for(String statement : statementList)
            {
                statementField.setText("");
                statementsArea.setText(current + statement + "\n");
            }
        }
    }

    class RemoveStatement implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            String toDelete = statementField.getText();
            for(int i = 0; i < statementList.size(); i++)
            {
                if(statementList.get(i).equals(toDelete))
                {
                    statementList.remove(statementList.get(i));
                }
            }

            statementsArea.setText("");
            String current = statementsArea.getText();
            for(String statement : statementList)
            {
                statementField.setText("");
                statementsArea.setText(current + statement + "\n");
                current = statementsArea.getText();
            }
        }
    }

    class Close implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        }
    }
}