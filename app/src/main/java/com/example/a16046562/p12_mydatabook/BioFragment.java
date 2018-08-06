package com.example.a16046562.p12_mydatabook;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import static android.content.Context.MODE_PRIVATE;
/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment {
    Button btnedit;
    TextView tv;
    FloatingActionButton fab;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    SharedPreferences prefs;

    public BioFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bio, container, false);

        tv = view.findViewById(R.id.tv);
        btnedit = view.findViewById(R.id.btnedit);

        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawerList = (ListView) getActivity().findViewById(R.id.left_drawer);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerList);
            }
        });
        prefs = getActivity().getSharedPreferences("prefsBio", MODE_PRIVATE);
        String str = prefs.getString("bio", "");
        tv.setText(str);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout p = (LinearLayout) inflater.inflate(R.layout.bioedit_dialog,null);
                final EditText etbio = (EditText) p.findViewById(R.id.editText);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit Bio")
                        .setView(p)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv.setText(etbio.getText().toString());
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("bio", etbio.getText().toString());
                                editor.apply();
                            }
                        })
                        //1 way of dismiss dialog
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        //2nd way of doing nothing
                        //.setNeutralButton("Cancel",null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        return view;
    }

}
