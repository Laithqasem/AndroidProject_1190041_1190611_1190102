package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper{
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
//        context.deleteDatabase(name);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ADMIN(email TEXT PRIMARY KEY, password TEXT, " +
                "firstName TEXT, lastName TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE COURSES(ID INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_ID TEXT, COURSE_NAME TEXT, PREREQUISITES TEXT, " +
                "START_DATE TEXT, END_DATE TEXT, START_REG TEXT, END_REG TEXT, IMAGE BLOB)");

//        sqLiteDatabase.execSQL("CREATE TABLE COURSES(COURSE_ID TEXT PRIMARY KEY, COURSE_NAME TEXT, PREREQUISITES TEXT, " +
//                "START_DATE TEXT, END_DATE TEXT, START_REG TEXT, END_REG TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE Trainee(email TEXT PRIMARY KEY, password TEXT, firstName TEXT, " +
                "lastName TEXT, mobileNumber TEXT, address TEXT, image BLOB)");

        sqLiteDatabase.execSQL("CREATE TABLE TraineeToSection(traineeToSectionID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sectionID INTEGER, traineeEmail TEXT, status INTEGER)");

        sqLiteDatabase.execSQL("CREATE TABLE Notifications(notID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " notText TEXT, status INTEGER, traineeEmail TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE TOPICS(TOPIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_ID INTEGER, TOPIC_NAME TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE INSTRUCTOR(" +
                "EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, FIRST_NAME TEXT, LAST_NAME TEXT, MOBILE_NUMBER TEXT," +
                "ADDRESS TEXT, SPECIALIZATION TEXT, DEGREE TEXT, canTeach TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE SECTION(" +
                "SECTION_ID INTEGER PRIMARY KEY AUTOINCREMENT, INSTRUCTOR_EMAIL TEXT, COURSE_ID INTEGER, MAX_TRAINEES INTEGER, " +
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

    // This will return all sections who's course id = courseID
    public Cursor getAllOfferings (String courseID) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE COURSE_ID = ?", new String[]{courseID});
        return cursor;
    }

    public void deleteCourse(String courseID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("COURSES", "ID = ?", new String[]{courseID});
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

    public void insertInstructor(Instructor instructor){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", instructor.getEmail());
        contentValues.put("PASSWORD", instructor.getPassword());
        contentValues.put("FIRST_NAME", instructor.getFirstName());
        contentValues.put("LAST_NAME", instructor.getLastName());
        contentValues.put("MOBILE_NUMBER", instructor.getMobileNumber());
        contentValues.put("ADDRESS", instructor.getAddress());
        contentValues.put("SPECIALIZATION", instructor.getSpecialization());
        contentValues.put("DEGREE", instructor.getDegree());
        sqLiteDatabase.insert("INSTRUCTOR", null, contentValues);
    }


    public void insertTrainee(Trainee trainee){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", trainee.getEmail());
        contentValues.put("password", trainee.getPassword());
        contentValues.put("firstName", trainee.getFirstName());
        contentValues.put("lastName", trainee.getLastName());
        contentValues.put("mobileNumber", trainee.getMobileNumber());
        contentValues.put("address", trainee.getAddress());
        contentValues.put("image", trainee.getImage());
        sqLiteDatabase.insert("Trainee", null, contentValues);

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

    public Cursor getAllTrainees() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Trainee", null);
    }

    public Cursor getOneTrainee(String Email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Trainee Where " + "email = \"" + Email + "\"", null);
    }

    public Cursor getAllInstructor() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR", null);
    }

    public Cursor getAllTopics() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TOPICS", null);
    }

    public Cursor getCourseId(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES Where " + "ID = \"" + id + "\"", null);
    }

    //function to get all sections for a specific course
    public Cursor getSectionsForTrainee(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection Where " + "COURSE_ID = " + id, null);

        if (cursor.moveToNext()){




        }

        Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM SECTION Where " + "COURSE_ID = " + id, null);
        return cursor1;
    }
    //function to delete trainee from trainee2section table based on section id and trainee email
    public void deleteTraineeFromSection(String email, String sectionId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("TraineeToSection", "traineeEmail = ? AND sectionID = ?", new String[]{email, sectionId});
    }
    public Cursor checkExistence(String email, String sectionId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\" " +
                "AND sectionID = " + sectionId, null);

        return cursor;
    }

    public ArrayList<String> checkPrerquesite(int sectionId){
        ArrayList<String> preR = new ArrayList<>();
        String[] pre = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + sectionId, null);
        int CourseId = -1;
        if (!cursor.moveToNext())
            return null;
        else
            CourseId = cursor.getInt(2);

        Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + CourseId, null);

        if (!cursor1.moveToNext())
            return null;
        else
            pre = cursor1.getString(3).split(", ");

        if (pre.length == 1 && pre[0].equals(""))
            return null;
        else{
            for (int i = 0; i < pre.length; i++)
                preR.add(pre[i]);
        }
        //for (String s: pre)
           // System.out.println(s + "hererhere\n");
        return preR;
    }

    public void insertTraineeSection(TraineeToSection traineeToSection){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("traineeEmail", traineeToSection.getTraineeEmail());
        contentValues.put("sectionID", traineeToSection.getSectionID());
        contentValues.put("status", traineeToSection.getStatus());
        sqLiteDatabase.insert("TraineeToSection", null, contentValues);
    }

    public int getTraineeCount(String sectionId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "sectionID = " + sectionId, null);

        return cursor.getCount();
    }

    public int countStudentsInSection(String sectionId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "sectionID = " + sectionId, null);

        return cursor.getCount();
    }

    // This function will return a cursor to show the trainees in a specific section
    public Cursor getTraineesInSection(String sectionId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "sectionID = " + sectionId, null);
    }

    public Cursor getTraineeSections(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\"", null);
    }
  
    public Cursor getSecId(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\" ", null);

        return cursor;
    }

    public Cursor getSecInfo(int SecID){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + SecID, null);

        return cursor;
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

    public String getTrainee(String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Trainee WHERE " +
                "email = \"" + email + "\"", null);
        String name = "";
        while(cursor.moveToNext()){
            name = cursor.getString(2) + " " + cursor.getString(3);
        }
        return name;
    }

    public void updateTraineeInSection(int sectionID, String traineeEmail) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", "1");
        sqLiteDatabase.update("TraineeToSection", contentValues, "sectionID = ? AND traineeEmail = ?", new String[]{String.valueOf(sectionID), traineeEmail});
    }

    public Cursor getSection(String sectionID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + sectionID, null);
    }

    public Cursor getAllSectionsOfCourse(String valueOf) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "COURSE_ID = " + valueOf, null);
    }

    public Cursor getCourseFromId(int valueOf) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + valueOf, null);
    }

    public Cursor getAllSectionsOfTrainee(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\"", null);
    }

    public void UpdateeTrainee(String alteredFirst, String alteredLast, String alteredMobile, String alteredAddress){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", alteredFirst);
        contentValues.put("lastName", alteredLast);
        contentValues.put("mobileNumber", alteredMobile);
        contentValues.put("address", alteredAddress);
        sqLiteDatabase.update("Trainee", contentValues, "email = ?", new String[]{TraineeActivites.getEmail()});

    }

    public Cursor getCourse(String valueOf) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + valueOf, null);
    }
}