package xyz.zood.george;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.util.Linkify;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.pijun.george.L;
import xyz.zood.george.databinding.FragmentLicensesBinding;

public class LicensesFragment extends Fragment implements LibrariesAdapter.LibrariesAdapterListener {

    public LicensesFragment() {}

    public static LicensesFragment newInstance() {
        return new LicensesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentLicensesBinding binding = FragmentLicensesBinding.inflate(inflater, container, false);
        binding.librariesList.setAdapter(new LibrariesAdapter(this));
        binding.back.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return binding.getRoot();
    }

    //region LibrariesAdapterListener

    @Override
    public void onLibraryItemSelected(Pair<String, Integer> libItem) {
        LicenseTextFragment f = LicenseTextFragment.newInstance(libItem.second);
        FragmentManager mgr = getParentFragmentManager();
        mgr.beginTransaction()
                .setCustomAnimations(R.animator.new_enter_from_right,
                        R.animator.new_exit_to_left,
                        R.animator.new_enter_from_left,
                        R.animator.new_exit_to_right)
                .replace(R.id.fragment_host, f)
                .addToBackStack(null)
                .commit();
    }
}