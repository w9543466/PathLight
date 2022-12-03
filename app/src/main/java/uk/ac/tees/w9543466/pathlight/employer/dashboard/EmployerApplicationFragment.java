package uk.ac.tees.w9543466.pathlight.employer.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9543466.pathlight.databinding.FragmentEmpApplicationsBinding;

public class EmployerApplicationFragment extends Fragment {

    private FragmentEmpApplicationsBinding binding;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentEmpApplicationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

}