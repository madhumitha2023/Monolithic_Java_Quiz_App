package com.java.quiz.javaquizapplication.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class QuestionWrapper {
    private Integer id;
    private String javaQuestion;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(Integer id, String javaQuestion, String option1, String option2, String option3, String option4) {
        this.id = id;
        this.javaQuestion = javaQuestion;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public Integer getId() {
        return id;
    }

    public String getJavaQuestion() {
        return javaQuestion;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }
}
