package com.hellohasan.sqlite_multiple_three_tables_crud.features.taken_subject_crud.subject_assign;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.hellohasan.sqlite_multiple_three_tables_crud.R;
import com.hellohasan.sqlite_multiple_three_tables_crud.database.*;
import com.hellohasan.sqlite_multiple_three_tables_crud.model.TakenSubject;

import java.util.List;

public class SubjectAssignListAdapter extends RecyclerView.Adapter<SubjectAssignViewHolder> {

    private Context context;
    private int studentId;
    private List<TakenSubject> takenSubjectList;

    public SubjectAssignListAdapter(Context context, int studentId, List<TakenSubject> takenSubjectList) {
        this.context = context;
        this.studentId = studentId;
        this.takenSubjectList = takenSubjectList;
    }

    @NonNull
    @Override
    public SubjectAssignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject_assign, parent, false);
        return new SubjectAssignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAssignViewHolder holder,  int position) {
        final TakenSubject subject = takenSubjectList.get(position);

        holder.subjectNameTextView.setText(subject.getName());
        holder.courseCodeTextView.setText(context.getString(R.string.course_code, subject.getCode()));
        holder.creditTextView.setText(context.getString(R.string.course_credit, subject.getCredit()));
        holder.checkBox.setChecked(subject.isTaken());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    assignSubject(subject.getId());
                else
                    removeAssignedSubject(subject.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return takenSubjectList.size();
    }

    private void assignSubject(int subjectId) {
        QueryContract.TakenSubjectQuery query = new TakenSubjectQueryImplementation();
        query.createTakenSubject(studentId, subjectId, new QueryResponse<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                // We did nothing here. because we don't need to update (set checked) CheckBox manually
                // CheckBox is updated by default after clicking on it
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void removeAssignedSubject(int subjectId) {
        QueryContract.TakenSubjectQuery query = new TakenSubjectQueryImplementation();
        query.deleteTakenSubject(studentId, subjectId, new QueryResponse<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                // We did nothing here. because we don't need to update (set unchecked) CheckBox manually
                // CheckBox is updated by default after clicking on it
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}