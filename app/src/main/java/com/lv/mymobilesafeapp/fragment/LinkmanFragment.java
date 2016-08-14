package com.lv.mymobilesafeapp.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.LinkMan;
import com.lv.mymobilesafeapp.listener.MyRadioListener;
import com.lv.mymobilesafeapp.view.CharacterParser;
import com.lv.mymobilesafeapp.view.PinyinComparator;
import com.lv.mymobilesafeapp.view.SideBar;
import com.lv.mymobilesafeapp.view.SortAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LinkmanFragment extends Fragment implements MyRadioListener{

    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private CharacterParser characterParser;
    private List<LinkMan> SourceDateList;
    private PinyinComparator pinyinComparator;
    private List<String> name=new ArrayList<>();
    private List<LinkMan> linkmanList=new ArrayList<>();
    //public static boolean flag=false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main1,container,false);
        if(linkmanList.size()==0){
            initdata();
        }

        initViews(view);
        //if(flag){adapter.shown();}


        return view;
    }

    private void initdata(){
        //获取联系人
        ContentResolver mResolver=getActivity().getContentResolver();

        Cursor cursor=mResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        while(cursor.moveToNext()){
            String contact_name=cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            String contact_id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            LinkMan man=new LinkMan();
            man.setName(contact_name);
           man.setId(contact_id);
            Cursor phone=mResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+'='+contact_id,
                    null, null);
            phone.moveToNext();
            String number=phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            man.setNumber(number);
            linkmanList.add(man);
        }

        for(LinkMan man:linkmanList){
            name.add(man.getName());

        }

    }

    private void initViews(View view) {

        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) view.findViewById(R.id.sidrbar);
        dialog = (TextView) view.findViewById(R.id.dialog);
        sideBar.setTextView(dialog);


        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {

                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView)view. findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(getActivity(), ((LinkMan) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //SourceDateList = filledData(getResources().getStringArray(R.array.date));
      // SourceDateList = filledData(new String[]{"李四","王五","大表哥"});
        SourceDateList = filledData(name);


        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(getActivity(), SourceDateList);
        sortListView.setAdapter(adapter);


    }

    private List<LinkMan> filledData(List<String> name){
        List<LinkMan> mSortList = new ArrayList<LinkMan>();

        for(int i=0; i<name.size(); i++){
            LinkMan sortModel = new LinkMan();
            sortModel.setName(name.get(i));
            sortModel.setId(linkmanList.get(i).getId());
            sortModel.setNumber(linkmanList.get(i).getNumber());

            String pinyin = characterParser.getSelling(name.get(i));
            String sortString = pinyin.substring(0, 1).toUpperCase();


            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /*private List<LinkMan> filledData(String [] date){
        List<LinkMan> mSortList = new ArrayList<LinkMan>();

        for(int i=0; i<date.length; i++){
            LinkMan sortModel = new LinkMan();
            sortModel.setName(date[i]);
            sortModel.setId(linkmanList.get(i).getId());
            sortModel.setNumber(linkmanList.get(i).getNumber());

            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();


            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }*/




    private void filterData(String filterStr){
        List<LinkMan> filterDateList = new ArrayList<LinkMan>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(LinkMan sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }


        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }


    @Override
    public boolean isShown(boolean flag) {
        if(flag==true){
            adapter.shown();
            System.out.println("+++++++++++++++_______444444444444444____________----+++++++++++++++++++++++++=");
            adapter.notifyDataSetChanged();
        }
        return flag;
    }
}
