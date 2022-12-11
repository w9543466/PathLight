package uk.ac.tees.w9543466.pathlight.employer.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import uk.ac.tees.w9543466.pathlight.databinding.FragmentHomeBinding;
import uk.ac.tees.w9543466.pathlight.employer.profile.ProfileActivity;
import uk.ac.tees.w9543466.pathlight.employer.profile.ProfileViewModel;
import uk.ac.tees.w9543466.pathlight.employer.works.MyWorkAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private final MyWorkAdapter myWorkAdapter = new MyWorkAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(homeViewModel);
        binding.setProfileVm(profileViewModel);
        setupWorkAdapter();
        setupClickers();
        profileViewModel.getEmployerProfile();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observeWorkData();
    }

    private void setupClickers() {
        binding.fabNewWork.setOnClickListener(v -> startActivity(new Intent(getContext(), NewWorkActivity.class)));
        binding.noDataView.btnAction.setOnClickListener(v -> startActivity(new Intent(getContext(), NewWorkActivity.class)));
        binding.errorView.btnAction.setOnClickListener(v -> homeViewModel.retryWorks());
        binding.settings.setOnClickListener(v -> startActivity(new Intent(getContext(), ProfileActivity.class)));
    }

    private void observeWorkData() {
        homeViewModel.getWorkLiveData().observe(getViewLifecycleOwner(), myWorkAdapter::submitList);
    }

    private void setupWorkAdapter() {
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.rvWorkList.addItemDecoration(decor);
        binding.rvWorkList.setAdapter(myWorkAdapter);
        myWorkAdapter.setListener(position -> {
            long workId = homeViewModel.getItemWorkId(position);
            Intent intent = new Intent(getContext(), WorkDetailActivity.class);
            intent.putExtra(WorkDetailActivity.BUNDLE_KEY_SELECTED_WORK_ID, workId);
            startActivity(intent);
        });
    }
}