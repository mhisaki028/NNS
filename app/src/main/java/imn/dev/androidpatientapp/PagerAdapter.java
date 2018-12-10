package imn.dev.androidpatientapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FirstFragment tab1 = new FirstFragment();
                return tab1;
            case 1:
                SecondFragment tab2 = new SecondFragment();
                return tab2;
            case 2:
                ThirdFragment tab3 = new ThirdFragment();
                return tab3;
            case 3:
                FourthFragment tab4 = new FourthFragment();
                return tab4;
            case 4:
                FifthFragment tab5 = new FifthFragment();
                return tab5;
            case 5:
                SixthFragment tab6 = new SixthFragment();
                return tab6;
            case 6:
                SeventhFragment tab7 = new SeventhFragment();
                return tab7;
            case 7:
                EightFragment tab8 = new EightFragment();
                return tab8;
            case 8:
                NinthFragment tab9 = new NinthFragment();
                return tab9;
            case 9:
                TenthFragment tab10 = new TenthFragment();
                return tab10;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
