package com.example.recursivasantiagogil.view;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.recursivasantiagogil.R;
import com.example.recursivasantiagogil.model.Answer;
import com.example.recursivasantiagogil.model.Question;
import com.example.recursivasantiagogil.model.QuestionsDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleAnswerQuestionFragment extends Fragment {

    public static final String QUESTION_ID = "question id";

    private QuestionsDatabase questionsDatabase;
    private TextView textViewQuestion;
    private RadioButton radioButtonA;
    private RadioButton radioButtonB;
    private RadioButton radioButtonC;
    private Question question;
    private List<Answer> answerList;
    private Button nextButton;
    private RadioGroup radioGroup;
    private SingleAnswerFragmentSubmitAnswerListener singleAnswerFragmentSubmitAnswerListener;

    public static SingleAnswerQuestionFragment newInstance(int questionId, QuestionsDatabase questionsDatabase) {
        Bundle bundle = new Bundle();
        bundle.putInt(QUESTION_ID, questionId);
        SingleAnswerQuestionFragment fragment = new SingleAnswerQuestionFragment();
        fragment.setArguments(bundle);
        fragment.questionsDatabase = questionsDatabase;

        return fragment;
    }


    public SingleAnswerQuestionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_answer_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewQuestion = view.findViewById(R.id.textViewQuestion);
        radioButtonA = view.findViewById(R.id.radioButtonAnswerA);
        radioButtonB = view.findViewById(R.id.radioButtonAnswerB);
        radioButtonC = view.findViewById(R.id.radioButtonAnswerC);
        nextButton = view.findViewById(R.id.buttonNext);
        radioGroup = view.findViewById(R.id.radioGroup);

        loadQuestion();

        loadAnswers();

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == radioButtonA.getId())
                    submitAnswerA();
                else if (selectedId == radioButtonB.getId())
                    submitAnswerB();
                else if (selectedId == radioButtonC.getId())
                    submitAnswerC();
                else
                    submitNoAnswer();

            }
        });


    }

    private void submitAnswerA() {
        singleAnswerFragmentSubmitAnswerListener.submitAnwer(answerList.get(0).isCorrect());
    }

    private void submitAnswerB() {
        singleAnswerFragmentSubmitAnswerListener.submitAnwer(answerList.get(1).isCorrect());
    }

    private void submitAnswerC() {
        singleAnswerFragmentSubmitAnswerListener.submitAnwer(answerList.get(2).isCorrect());
    }

    private void submitNoAnswer() {
        singleAnswerFragmentSubmitAnswerListener.submitAnwer(null);
    }

    public interface SingleAnswerFragmentSubmitAnswerListener {
        void submitAnwer(Boolean isCorrect);
    }

    private void loadAnswers() {
        new LoadAnswersAsyncTask().execute();
    }

    private void loadQuestion() {
        new LoadQuestionAsyncTask().execute();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        singleAnswerFragmentSubmitAnswerListener = (SingleAnswerFragmentSubmitAnswerListener) context;
    }

    private class LoadQuestionAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            question = questionsDatabase.questionDAO().getQuestionById(getArguments().getInt(QUESTION_ID));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textViewQuestion.setText(question.getQuestion());
        }
    }

    private class LoadAnswersAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            answerList = questionsDatabase.answerDAO().getAnswersForQuestion(getArguments().getInt(QUESTION_ID));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            radioButtonA.setText(answerList.get(0).getAnswer());
            radioButtonB.setText(answerList.get(1).getAnswer());
            radioButtonC.setText(answerList.get(2).getAnswer());
        }
    }
}
