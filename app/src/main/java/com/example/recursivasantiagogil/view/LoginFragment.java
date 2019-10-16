package com.example.recursivasantiagogil.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recursivasantiagogil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText editTextName;
    private EditText editTextEmail;
    private Button buttonNext;
    private FragmentLoginListener fragmentLoginListener;


    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName = view.findViewById(R.id.editTextUserName);
        editTextEmail = view.findViewById(R.id.editTextUserEmail);
        buttonNext = view.findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentLoginListener.onLoginNextClicked(
                        editTextName.getText().toString(), editTextEmail.getText().toString()
                );
            }
        });

    }

    public interface FragmentLoginListener{
        void onLoginNextClicked(String name, String email);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentLoginListener = (FragmentLoginListener) context;
    }
}
