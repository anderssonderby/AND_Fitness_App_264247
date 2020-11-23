package com.example.myapplication.Model.RetroFit;

import java.util.List;

public class ExerciseResponse {

    // TODO: Delete unused
    public int count;
    public String next;
    public Object previous;
    public List<Result> results;

    public class Result {
        public int id;
        public String license_author;
        public String status;
        public String description;
        public String name;
        public String name_original;
        public String creation_date;
        public String uuid;
        public int license;
        public int category;
        public int language;
        public List<Integer> muscles;
        public List<Integer> muscles_secondary;
        public List<Integer> equipment;
    }



}
