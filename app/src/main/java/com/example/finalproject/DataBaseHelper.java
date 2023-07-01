package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper{
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
//        context.deleteDatabase(name);
    }

    Context context;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("CREATE TABLE ADMIN(EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, " +
                "FIRST_NAME TEXT, LAST_NAME TEXT, PERSONAL_PHOTO TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE COURSES(COURSE_ID TEXT PRIMARY KEY, COURSE_NAME TEXT, PREREQUISITES TEXT, " +
                                    "START_DATE TEXT, END_DATE TEXT, START_REG TEXT, END_REG TEXT, IMAGE BLOB)");

        sqLiteDatabase.execSQL("CREATE TABLE TRAINEE(EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, " +
                "FIRST_NAME TEXT, LAST_NAME TEXT, PERSONAL_PHOTO TEXT, ADDRESS TEXT,MOBILE_NUMBER TEXT)");


//        sqLiteDatabase.execSQL("CREATE TABLE COURSES(COURSE_ID TEXT PRIMARY KEY, COURSE_NAME TEXT, PREREQUISITES TEXT, " +
//                "START_DATE TEXT, END_DATE TEXT, START_REG TEXT, END_REG TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE TOPICS(TOPIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_ID TEXT, TOPIC_NAME TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE INSTRUCTOR(" +
                "EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, FIRST_NAME TEXT, LAST_NAME TEXT, PERSONAL_PHOTO TEXT, MOBILE_NUMBER TEXT," +
                "ADDRESS TEXT, SPECIALIZATION TEXT, canTeach TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE SECTION(" +
                "SECTION_ID INTEGER PRIMARY KEY AUTOINCREMENT, INSTRUCTOR_EMAIL TEXT, COURSE_ID TEXT, MAX_TRAINEES INTEGER, " +
                "START_TIME TEXT, END_TIME TEXT, DAYS TEXT, ROOM TEXT, START_DATE TEXT, END_DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertSection(Section section) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("INSTRUCTOR_EMAIL", section.getInstructorEmail());
        contentValues.put("COURSE_ID", section.getCourseID());
        contentValues.put("MAX_TRAINEES", section.getMaxTrainees());
        contentValues.put("START_TIME", section.getStartTime());
        contentValues.put("END_TIME", section.getEndTime());
        contentValues.put("DAYS", section.getDays());
        contentValues.put("ROOM", section.getRoom());
        contentValues.put("START_DATE", section.getStartDate());
        contentValues.put("END_DATE", section.getEndDate());
        sqLiteDatabase.insert("SECTION", null, contentValues);
    }
    public Boolean getLoginPassword(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR WHERE " +
                "EMAIL = \"" + email + "\"", null);



        return true;

    }
    public void insertAdmin(Admin admin) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", admin.getEmail());
        contentValues.put("PASSWORD", admin.getPassword());
        contentValues.put("LAST_NAME", admin.getLastName());
        contentValues.put("FIRST_NAME", admin.getFirstName());
        contentValues.put("PERSONAL_PHOTO", admin.getPersonal_photo());
        sqLiteDatabase.insert("ADMIN", null, contentValues);
    }

    public void insertTrainee(Trainee trainee) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", trainee.getEmail());
        contentValues.put("PASSWORD", trainee.getPassword());
        contentValues.put("FIRST_NAME", trainee.getFirstName());
        contentValues.put("LAST_NAME", trainee.getLastName());
        contentValues.put("PERSONAL_PHOTO", trainee.getPersonal_photo());
        contentValues.put("ADDRESS", trainee.getAddress());
        contentValues.put("MOBILE_NUMBER", trainee.getMobileNumber());
        sqLiteDatabase.insert("TRAINEE", null, contentValues);
    }

    // This will return all sections who's course id = courseID
    public Cursor getAllOfferings (String courseID) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE COURSE_ID = ?", new String[]{courseID});
        return cursor;
    }

    public void deleteCourse(String courseID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("COURSES", "COURSE_ID = ?", new String[]{courseID});
        sqLiteDatabase.delete("SECTION", "COURSE_ID = ?", new String[]{courseID});
    }

    public void updateSection(Section section) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("INSTRUCTOR_EMAIL", section.getInstructorEmail());
        contentValues.put("COURSE_ID", section.getCourseID());
        contentValues.put("MAX_TRAINEES", section.getMaxTrainees());
        contentValues.put("START_TIME", section.getStartTime());
        contentValues.put("END_TIME", section.getEndTime());
        contentValues.put("DAYS", section.getDays());
        contentValues.put("ROOM", section.getRoom());
        contentValues.put("START_DATE", section.getStartDate());
        contentValues.put("END_DATE", section.getEndDate());
        sqLiteDatabase.update("SECTION", contentValues, "SECTION_ID = ?", new String[]{String.valueOf(section.getSectionID())});
    }
//
//    public void insertInstructor(Instructor instructor){
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("EMAIL", instructor.getEmail());
//        contentValues.put("PASSWORD", instructor.getPassword());
//        contentValues.put("FIRST_NAME", instructor.getFirstName());
//        contentValues.put("LAST_NAME", instructor.getLastName());
//        contentValues.put("PERSONAL_PHOTO", instructor.getPersonal_photo());
//        contentValues.put("MOBILE_NUMBER", instructor.getMobileNumber());
//        contentValues.put("ADDRESS", instructor.getAddress());
//        contentValues.put("SPECIALIZATION", instructor.getSpecialization());
//        contentValues.put("canTeach", instructor.getCanTeach());
//        sqLiteDatabase.insert("INSTRUCTOR", null, contentValues);
//    }
    public void insertInstructor(Instructor instructor){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("canTeach", instructor.getCanTeach());
        contentValues.put("PASSWORD", instructor.getPassword());
        contentValues.put("MOBILE_NUMBER", instructor.getMobileNumber());
        contentValues.put("ADDRESS", instructor.getAddress());
        contentValues.put("EMAIL", instructor.getEmail());
        contentValues.put("LAST_NAME", instructor.getLastName());
        contentValues.put("SPECIALIZATION", instructor.getSpecialization());

        contentValues.put("FIRST_NAME", instructor.getFirstName());
        contentValues.put("PERSONAL_PHOTO", instructor.getPersonal_photo());
        sqLiteDatabase.insert("INSTRUCTOR", null, contentValues);
    }

    public void insertCourses(Course course){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COURSE_ID", course.getCourseID());
        contentValues.put("COURSE_NAME", course.getCourseName());
        contentValues.put("PREREQUISITES", course.getPrerequisites());
        contentValues.put("START_DATE", course.getStartDate());
        contentValues.put("END_DATE", course.getEndDate());
        contentValues.put("START_REG", course.getRegistrationStart());
        contentValues.put("END_REG", course.getRegistrationEnd());
        contentValues.put("IMAGE", course.getImage());
        sqLiteDatabase.insert("COURSES", null, contentValues);
    }

    public void insertTopics(Topic topic){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COURSE_ID", topic.getCourseID());
        contentValues.put("TOPIC_NAME", topic.getTopicName());

        sqLiteDatabase.insert("TOPICS", null, contentValues);
    }

    public boolean checkCourseId(String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE COURSE_ID = \"" + id + "\"", null);
        int cnt = 0;
        while(cursor.moveToNext()){
            cnt++;
        }
        return cnt == 0;
    }

    public Cursor getSections() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM SECTION", null);
    }

    public Cursor getAllCourses() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES", null);
    }

    public Cursor getAllInstructor() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR", null);
    }
    public Cursor getAllTrainee() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TRAINEE", null);
    }
    public Cursor getAllAdmin() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ADMIN", null);
    }
    public Cursor getAllTopics() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TOPICS", null);
    }


    public boolean checkInstructor(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR WHERE " +
                "EMAIL = \"" + email + "\"", null);
        int cnt = 0;
        while(cursor.moveToNext()){
            cnt++;
        }
        return cnt == 1;
    }

    public void deleteSection(String valueOf) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("SECTION", "SECTION_ID = ?", new String[]{valueOf});
    }
}