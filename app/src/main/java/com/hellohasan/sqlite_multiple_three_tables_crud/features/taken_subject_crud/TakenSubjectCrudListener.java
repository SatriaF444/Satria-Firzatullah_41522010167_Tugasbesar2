package com.hellohasan.sqlite_multiple_three_tables_crud.features.taken_subject_crud;

public interface TakenSubjectCrudListener {
    void onSubjectListUpdate(boolean isUpdated);

    void onTakenSubjectUpdated(boolean isUpdated);
}
