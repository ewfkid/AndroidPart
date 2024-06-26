package com.example.app.ui.sign;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.app.R;
import com.example.app.databinding.SignFragmentBinding;
import com.example.app.ui.utils.OnChangeText;
import com.example.app.ui.utils.Utils;

public class SignFragment extends Fragment {

    private SignFragmentBinding binding;

    private SignViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = SignFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(SignViewModel.class);

        binding.email.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changeLogin(s.toString());
            }
        });
        binding.password.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changePassword(s.toString());
            }
        });
        binding.confirm.setOnClickListener(v -> viewModel.confirm());
        subscribe(viewModel);
    }

    private void subscribe(SignViewModel viewModel) {

        viewModel.errorLiveData.observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        });

        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            binding.confirm.setText(state.getButton());
            binding.title.setText(state.getTitle());
            binding.password.setVisibility(Utils.visibleOrGone(state.isPasswordEnabled()));
        });

        viewModel.homeLiveData.observe(getViewLifecycleOwner(), unused -> {
            View view = getView();
            if (view == null) return;
            Navigation.findNavController(view).navigate(R.id.action_sign_fragment_to_home_fragment);
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }


}
