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
        sqLiteDatabase.execSQL("CREATE TABLE ADMIN(EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, " +
                "FIRST_NAME TEXT, LAST_NAME TEXT, PERSONAL_PHOTO BLOB)");

        sqLiteDatabase.execSQL("CREATE TABLE COURSES(ID INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_ID TEXT, COURSE_NAME TEXT, PREREQUISITES TEXT, " +
                "START_DATE TEXT, END_DATE TEXT, START_REG TEXT, END_REG TEXT, IMAGE BLOB)");

//        sqLiteDatabase.execSQL("CREATE TABLE COURSES(COURSE_ID TEXT PRIMARY KEY, COURSE_NAME TEXT, PREREQUISITES TEXT, " +
//                "START_DATE TEXT, END_DATE TEXT, START_REG TEXT, END_REG TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE TRAINEE(EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, FIRST_NAME TEXT, " +
                "LAST_NAME TEXT, MOBILE_NUMBER TEXT, ADDRESS TEXT, IMAGE BLOB)");

        sqLiteDatabase.execSQL("CREATE TABLE TraineeToSection(traineeToSectionID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sectionID INTEGER, traineeEmail TEXT, status INTEGER)");

        sqLiteDatabase.execSQL("CREATE TABLE Notifications(notID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " notText TEXT, status INTEGER, traineeEmail TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE TOPICS(TOPIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_ID INTEGER, TOPIC_NAME TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE INSTRUCTOR(" +
                "EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, FIRST_NAME TEXT, LAST_NAME TEXT, MOBILE_NUMBER TEXT," +
                "ADDRESS TEXT, SPECIALIZATION TEXT, DEGREE TEXT, canTeach TEXT, IMAGE BLOB)");

        sqLiteDatabase.execSQL("CREATE TABLE SECTION(" +
                "SECTION_ID INTEGER PRIMARY KEY AUTOINCREMENT, INSTRUCTOR_EMAIL TEXT, COURSE_ID INTEGER, MAX_TRAINEES INTEGER, " +
                "START_TIME TEXT, END_TIME TEXT, DAYS TEXT, ROOM TEXT, START_DATE TEXT, END_DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void deleteALLSections() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE SECTION");
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

    public boolean vaildSignUp(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ADMIN WHERE " +
                "EMAIL = \"" + email + "\"", null);

        Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM TRAINEE WHERE " +
                "EMAIL = \"" + email + "\"", null);

        Cursor cursor3 = sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR WHERE " +
                "EMAIL = \"" + email + "\"", null);

        return cursor.getCount() + cursor2.getCount() + cursor3.getCount() == 0;
    }

    public Cursor getInstructorData(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR WHERE " +
                "EMAIL = \"" + email + "\"", null);

        return cursor;

    }




    public String getLoginPassword(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ADMIN WHERE " +
                "EMAIL = \"" + email + "\"", null);

        while (cursor.moveToNext()){

            if(cursor.getString(0).equals(email) && cursor.getString(1).equals(password)){
                return "ADMIN";
            }
        }
        Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR WHERE " +
                "EMAIL = \"" + email + "\"", null);

        while (cursor2.moveToNext()){

            if(cursor2.getString(0).equals(email) && cursor2.getString(1).equals(password)){
                return "INSTRUCTOR";
            }
        }
        Cursor cursor3 = sqLiteDatabase.rawQuery("SELECT * FROM TRAINEE WHERE " +
                "EMAIL = \"" + email + "\"", null);

        while (cursor3.moveToNext()){

            if(cursor3.getString(0).equals(email) && cursor3.getString(1).equals(password)){
                return "TRAINEE";
            }
        }

        return "NOTVALID";
    }

    // This will return all sections who's course id = courseID
    public Cursor getAllOfferings (String courseID) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE COURSE_ID = ?", new String[]{courseID});
        return cursor;
    }
    public Cursor getAllSectionsBasedOnInstructor (String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION  WHERE INSTRUCTOR_EMAIL = '" + email + "'", null);
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
        contentValues.put("IMAGE", instructor.getImage());
        contentValues.put("MOBILE_NUMBER", instructor.getMobileNumber());
        contentValues.put("ADDRESS", instructor.getAddress());
        contentValues.put("SPECIALIZATION", instructor.getSpecialization());
        contentValues.put("DEGREE", instructor.getDegree());
        contentValues.put("canTeach", instructor.getCanTeach());
        sqLiteDatabase.insert("INSTRUCTOR", null, contentValues);
    }

    public boolean updateInstructor(String column,String value, String Email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column,value);
        sqLiteDatabase.update("INSTRUCTOR", cv, "EMAIL = ?", new String[]{Email});
        return true;
    }
    public boolean updateInstructorProfile(String column,byte[] value, String Email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column,value);
        sqLiteDatabase.update("INSTRUCTOR", cv, "EMAIL = ?", new String[]{Email});
        return true;
    }
    public Cursor getAllTrainee() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TRAINEE", null);
    }
    public Cursor getAllAdmin() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ADMIN", null);
    }

    public void insertAdmin(Admin admin) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", admin.getEmail());
        contentValues.put("PASSWORD", admin.getPassword());
        contentValues.put("FIRST_NAME", admin.getFirstName());
        contentValues.put("LAST_NAME", admin.getLastName());
        contentValues.put("PERSONAL_PHOTO", admin.getImage());
        sqLiteDatabase.insert("ADMIN", null, contentValues);
    }


    public void insertTrainee(Trainee trainee) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", trainee.getEmail());
        contentValues.put("PASSWORD", trainee.getPassword());
        contentValues.put("FIRST_NAME", trainee.getFirstName());
        contentValues.put("LAST_NAME", trainee.getLastName());
        contentValues.put("ADDRESS", trainee.getAddress());
        contentValues.put("MOBILE_NUMBER", trainee.getMobileNumber());
        contentValues.put("IMAGE", trainee.getImage());
        sqLiteDatabase.insert("TRAINEE", null, contentValues);
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
        return cursor.getCount() == 0;
    }

    public String getCourseName(int course_id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String res="asd";
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSES", null);

        while (cursor.moveToNext()){

            System.out.println( "qweqweqweewq   " +cursor.getInt(0));
            if(cursor.getInt(0) == course_id){
                return cursor.getString(2) ;
            }

                return "Course";

        }


        return res;
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
        return sqLiteDatabase.rawQuery("SELECT * FROM TRAINEE", null);
    }

    public Cursor getOneTrainee(String Email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TRAINEE Where " + "EMAIL = \"" + Email + "\"", null);
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

    public Cursor getSectionsForTrainee(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection Where " + "sectionID = " + id, null);
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
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TRAINEE WHERE " +
                "EMAIL = \"" + email + "\"", null);
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


    public Cursor getAllSectionsOfTrainee(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\"", null);
    }

    public Cursor getCourse(String valueOf) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + valueOf, null);
    }

    public void insertNotifications(Notification notification) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("notText", notification.getNotText());
        contentValues.put("status", notification.getStatus());
        contentValues.put("traineeEmail", notification.getTraineeEmail());
        sqLiteDatabase.insert("Notifications", null, contentValues);
    }

    public Cursor getAllNotifications() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Notifications", null);
    }

    public int getCourseIDForSection(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + id, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getInt(2);
    }

    public Cursor getTraineeToSection() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection", null);
    }

    public String getStartDate(int courseID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + courseID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(4);
    }

    public String getEndDate(int courseID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + courseID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(5);
    }

    public String getStartTime(int otherSectionID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + otherSectionID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(4);
    }

    public String getEndTime(int otherSectionID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + otherSectionID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(5);
    }

    public String getDays(int otherSectionID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + otherSectionID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(6);
    }


    public Cursor getAdmin(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ADMIN WHERE " +
                "EMAIL = \"" + email + "\"", null);
    }

    public boolean check(String emailString, String passwordString) {
        boolean ok = vaildSignUp(emailString);
        boolean upper = false, lower = false, digit = false;
        for(int i = 0; i < passwordString.length(); i++){
            if(Character.isUpperCase(passwordString.charAt(i))){
                upper = true;
            }
            if(Character.isLowerCase(passwordString.charAt(i))){
                lower = true;
            }
            if(Character.isDigit(passwordString.charAt(i))){
                digit = true;
            }
        }
        return ok && upper && lower && digit && passwordString.length() >= 8 && passwordString.length() <= 15;
    }

    public void updateAdmin(String origin, String emailString, String passwordString, String firstNameString, String lastNameString, byte[] array) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", emailString);
        contentValues.put("PASSWORD", passwordString);
        contentValues.put("FIRST_NAME", firstNameString);
        contentValues.put("LAST_NAME", lastNameString);
        contentValues.put("PERSONAL_PHOTO", array);
        sqLiteDatabase.update("ADMIN", contentValues, "EMAIL = ?", new String[]{origin});
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

    public Cursor getCourseFromId(int valueOf) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + valueOf, null);
    }

     public void UpdateeTrainee(String alteredFirst, String alteredLast, String alteredMobile, String alteredAddress){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", alteredFirst);
        contentValues.put("LAST_NAME", alteredLast);
        contentValues.put("MOBILE_NUMBER", alteredMobile);
        contentValues.put("ADDRESS", alteredAddress);
        sqLiteDatabase.update("TRAINEE", contentValues, "EMAIL = ?", new String[]{TraineeActivites.getEmail()});

    }


    public Cursor getNotificationsForTrainee(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Notifications WHERE " +
                "traineeEmail = \"" + email + "\" and status = 0" , null);
    }

    public void updateNotification(int idNot) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", 1);
        sqLiteDatabase.update("Notifications", contentValues, "notID = ?", new String[]{String.valueOf(idNot)});
    }
}

