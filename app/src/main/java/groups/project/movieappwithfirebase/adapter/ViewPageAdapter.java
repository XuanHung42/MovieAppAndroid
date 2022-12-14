package groups.project.movieappwithfirebase.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import groups.project.movieappwithfirebase.fragment.ComingFragment;
import groups.project.movieappwithfirebase.fragment.HomeFragment;
import groups.project.movieappwithfirebase.fragment.HotFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new HotFragment();
            case 2:
                return new ComingFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title = "Home";
                break;
            case 1:
                title= "Trending";
                break;
            case 2:
                title = "Coming soon";
                break;
        }
        return title;
    }
}
