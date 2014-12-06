package org.rinsoz.climbtracker;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.UUID;


/**
 * A fragment representing a single Route detail screen.
 * This fragment is either contained in a {@link RouteListActivity}
 * in two-pane mode (on tablets) or a {@link RouteDetailActivity}
 * on handsets.
 */
public class RouteDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String EXTRA_ROUTE_ID = "route_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Route _route;



    public static RouteDetailFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ROUTE_ID, crimeId);

        RouteDetailFragment fragment = new RouteDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RouteDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments().containsKey(EXTRA_ROUTE_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            UUID uuid = (UUID) getArguments().getSerializable(EXTRA_ROUTE_ID);
            _route = RouteStorage.get(getActivity()).getRoute(uuid);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_route_detail, container, false);

        TextView titleEdit = (TextView) rootView.findViewById(R.id.route_title);
        titleEdit.setText(_route.getTitle());
        titleEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _route.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextView hintEdit = (TextView) rootView.findViewById(R.id.route_hint);
        hintEdit.setText(_route.getHint());
        hintEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _route.setHint(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TextView creatorEdit = (TextView) rootView.findViewById(R.id.route_creator);
        creatorEdit.setText(_route.getCreator());
        creatorEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _route.setCreator(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TextView personalCommentEdit = (TextView) rootView.findViewById(R.id.route_personal_comment);
        personalCommentEdit.setText(_route.getPersonalComment());
        personalCommentEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _route.setPersonalComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Spinner quotation = (Spinner) rootView.findViewById(R.id.route_quotation);

        Spinner color = (Spinner) rootView.findViewById(R.id.route_color);
        Spinner progress = (Spinner) rootView.findViewById(R.id.route_progress);
        CheckBox insecond = (CheckBox) rootView.findViewById(R.id.route_in_second);
        insecond.setChecked(_route.inSecond());
        insecond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _route.setInSecond(isChecked);
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        RouteStorage.get(getActivity()).save();
    }
}
