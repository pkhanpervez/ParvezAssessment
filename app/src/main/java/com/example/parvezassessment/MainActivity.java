package com.example.parvezassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG="ParvezAssessment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<MyListData> myListData = new ArrayList<MyListData>();

        final PackageManager pm = getPackageManager();
        final List<ApplicationInfo> installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for ( ApplicationInfo app : installedApps ) {
            //Details:
            Log.d(TAG, "Package: " + app.packageName);
            Log.d(TAG, "UID: " + app.uid);
            Log.d(TAG, "Directory: " + app.sourceDir);

            //Permissions:
            StringBuffer permissions = new StringBuffer();

            try {
                PackageInfo packageInfo = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);

                String[] requestedPermissions = packageInfo.requestedPermissions;
                if ( requestedPermissions != null ) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        permissions.append(requestedPermissions[i] + "\n");
                    }

                    Log.d(TAG, "Permissions: " + permissions);

                    myListData.add(new MyListData(app.packageName, permissions.toString()));

                }
            }
            catch ( PackageManager.NameNotFoundException e ) {
                e.printStackTrace();
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}