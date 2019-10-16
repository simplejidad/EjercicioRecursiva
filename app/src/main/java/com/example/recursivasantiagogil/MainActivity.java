package com.example.recursivasantiagogil;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.recursivasantiagogil.model.QuestionsDatabase;
import com.example.recursivasantiagogil.model.Test;
import com.example.recursivasantiagogil.view.LoginFragment;
import com.example.recursivasantiagogil.view.SingleAnswerQuestionFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoginFragment.FragmentLoginListener
        , SingleAnswerQuestionFragment.SingleAnswerFragmentSubmitAnswerListener {

    private Test test;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       test = new Test();

       loadFragments();

       showLoginFragment();

    }

    private void showLoginFragment() {
        showFragment(fragmentList.get(0));
    }

    private void loadFragments() {
        fragmentList.add(new LoginFragment());
        QuestionsDatabase questionsDatabase = QuestionsDatabase.getInstance(MainActivity.this);
        for (int i = 0; i < 5; i++) {
            fragmentList.add(SingleAnswerQuestionFragment.newInstance(i + 1, questionsDatabase));
        }

    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentHolder, fragment)
                .commit();
    }

    @Override
    public void onLoginNextClicked(String name, String email) {
       test.setName(name);
       test.setEmail(email);
       test.nextQuestion();
       loadQuestionFragment();
    }

    private void loadQuestionFragment() {
        showFragment(fragmentList.get(test.getCurrentQuestion()));
    }

    @Override
    public void submitAnwer(Boolean isCorrect) {
        test.submitAnswer(isCorrect);
        test.nextQuestion();
        loadQuestionFragment();
    }
}
