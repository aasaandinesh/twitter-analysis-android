package widgets.dinesh.com.paytmproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import widgets.dinesh.com.paytmproject.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwitterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwitterListFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public TwitterListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TwitterListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TwitterListFragment newInstance(String param1, String param2) {
        TwitterListFragment fragment = new TwitterListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_twitter_list;
    }

}
