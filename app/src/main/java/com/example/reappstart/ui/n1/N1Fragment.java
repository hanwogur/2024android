package com.example.reappstart.ui.n1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.DataShareWriteAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reappstart.DetailActivity;
import com.example.reappstart.R;
import com.example.reappstart.databinding.FragmentN1Binding;

import java.util.ArrayList;
import java.util.List;

public class N1Fragment extends Fragment {
    RecyclerView recyclerView;
    DataAdapter adapter;
    Intent i;

    private List<DataModel> dataList;

    private FragmentN1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        N1ViewModel n1ViewModel =
                new ViewModelProvider(this).get(N1ViewModel.class);

        binding = FragmentN1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();



        recyclerView = root.findViewById(R.id.recycler_view);
        dataList = new ArrayList<>();
        adapter = new DataAdapter(dataList);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataModel item) {
                // 클릭된 아이템에 대한 처리를 여기에 추가
                i = new Intent(getActivity(), DetailActivity.class);
                i.putExtra("tit", item.getTitle().toString());
                startActivity(i);
            }
        });

        // 예시 데이터 추가
        dataList.add(new DataModel("Item 1"));
        dataList.add(new DataModel("Item 2"));
        dataList.add(new DataModel("Item 3"));

        // 변경된 데이터 적용
        adapter.notifyDataSetChanged();




        final TextView textView = binding.textN1;
        n1ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}

