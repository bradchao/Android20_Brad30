package tw.org.iii.brad.brad30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MyAdapter myAdapter;
    private LinkedList<Member> members = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMember();
        listView = findViewById(R.id.listView);
        initListView();
    }

    private void createMember(){
        for (int i=0; i<4; i++){
            Member member = new Member("Brad" + i);
            members.add(member);
        }
    }

    private void initListView(){
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    public void test1(View view) {
        for (Member member : members){
            Log.v("brad", member.getName() + ":" + member.getVIP());
        }
    }

    public void test2(View view) {
        Member member = new Member("III" +(int)(Math.random()*100));
        members.add(member);
        myAdapter.notifyDataSetChanged();
    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        MyAdapter(){
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return members.size();
        }

        @Override
        public Object getItem(int position) {
            return members.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final  int pos = position;
            View itemView = inflater.inflate(R.layout.item, null);

            TextView textView = itemView.findViewById(R.id.item_tv);
            Button btn = itemView.findViewById(R.id.item_btn);

            textView.setText(members.get(position).getName());
            btn.setText(position + "");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,
                            members.get(pos).getName(),
                            Toast.LENGTH_SHORT).show();
                }
            });

            SwitchCompat switchCompat = itemView.findViewById(R.id.item_isvip);
            switchCompat.setChecked(members.get(pos).getVIP());
            switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    members.get(pos).setVIP(isChecked);
                }
            });


            return itemView;
        }
    }


}
