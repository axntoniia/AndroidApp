package com.example.androidapp;

import android.provider.BaseColumns;

public final class UserContract {

    private UserContract(){}

        public static class UserTable implements BaseColumns {
            public static final String TABLE_NAME = "quiz_user";
            public static final String COLUMN_USER = "user";
            public static final String COLUMN_PASSWD = "passwd";
            public static final String COLUMN_EMAIL = "email";
            public static final String COLUMN_SCORE = "score";
            public static final String COLUMN_SCORETOTAL = "scoreTotal";
        }

}
