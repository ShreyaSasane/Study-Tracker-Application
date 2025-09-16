import java.util.*;
import java.time.LocalDate;
import java.io.*;

//=====================================================================================================
// Class        : StudyLog
// Purpose      : Represents a single study log entry with date, subject, duration, and description
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//=====================================================================================================

class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;

//==============================================================================
// Constructor  : StudyLog
// Parameters   : LocalDate, String (subject), double (duration), String (description)
// Purpose      : Initializes a new study log entry
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

    public StudyLog(LocalDate A, String B, double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
        
    }

//==============================================================================
// Method       : Display (Currently empty)
// Purpose      : Placeholder for future display logic (not implemented)
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

    public void Display()
    {
        System.out.println();
    }

//==============================================================================
// Method       : toString (Overridden)
// Purpose      : Returns a string representation of the study log entry
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

    @Override
    public String toString()
    {
        return Date+" | "+Subject+" | " + Duration+" | " +Description;
    }

//==============================================================================
// Getter methods
// Purpose      : Return individual attributes of the study log
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================
    public LocalDate getDate()
    {
        return Date;
    }

    public String getSubject()
    {
        return Subject;
    }

    public double getDuration()
    {
        return Duration;
    }

    public String getDescription()
    {
        return Description;
    }
}

//==============================================================================
// Class        : StudyTracker
// Purpose      : Manages a collection of study logs with features to insert, display,
//                summarize, and export logs.
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

class StudyTracker
{
    //DataStructure to hold the data about the study
    private ArrayList <StudyLog> Database = new ArrayList<StudyLog>();

//==============================================================================
// Method       : InsertLog
// Purpose      : Takes input from the user and adds a new study log to the database
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

    public void InsertLog()
    {
        Scanner ScannerObj = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------");
        System.out.println("--------Please enter the valid details of your study----------");
        System.out.println("--------------------------------------------------------------");

        LocalDate DateObj = LocalDate.now();

        System.out.println("Please provide the name of subject like C/C++/Java/OS/DS");
        String sub = ScannerObj.nextLine();

        System.out.println("Ente the time period of your study in hours");
        double dur = ScannerObj.nextDouble();
        ScannerObj.nextLine();

        System.out.println("Please provide the description about the study for future reference");
        String desc = ScannerObj.nextLine();

        StudyLog StudyObj = new StudyLog(DateObj, sub, dur, desc);

        Database.add(StudyObj);

        System.out.println("StudyLog gets stored successfully");
        System.out.println("--------------------------------------------------------------");
    }

//==============================================================================
// Method       : DisplayLog
// Purpose      : Displays all study log entries in the database
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

    public void DisplayLog()
    {
        System.out.println("--------------------------------------------------------------");
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("--------------------------------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("-----------Log report from Marvellous Study Tracker-----------");
        System.out.println("--------------------------------------------------------------");

        for(StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }
        System.out.println("--------------------------------------------------------------");
    }

//==============================================================================
// Method       : ExportCSV
// Purpose      : Exports all study logs into a CSV file
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

    public void ExportCSV()
    {
        if(Database.isEmpty())
        {
            System.out.println("Nothing to EXPORT as database is empty");
            System.out.println("--------------------------------------------------------------");
            return;
        }

        String FileName = "MarvellousStudy.csv";

        //Create new CSV
        try(FileWriter fwobj = new FileWriter(FileName))
        {
            //write CSV header
            fwobj.write("Date,Subject,Duration,Description\n");

            //Travel data base
            for(StudyLog sobj : Database)
            {
                //write each record in CSV
                fwobj.write(sobj.getDate() + "," +
                            sobj.getSubject().replace(","," ") + "," +
                            sobj.getDuration() + "," +
                            sobj.getDescription().replace(","," ") + ","

                           );

            }

            System.out.println("Log created SUCCESSFULLY");
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured while creating the CSV");
            System.out.println("Report this issue to marvellous Inforsystems");
        }
    }

//==============================================================================
// Method       : SummaryByDate
// Purpose      : Summarizes total study time per date
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

    public void SummaryByDate()
    {
        System.out.println("--------------------------------------------------------------");
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("--------------------------------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("--------Summary by DATE from Marvellous Study Tracker---------");
        System.out.println("--------------------------------------------------------------");

        TreeMap <LocalDate, Double> tobj = new TreeMap <LocalDate, Double> (); 

        LocalDate lobj = null;
        double d, old;

        for(StudyLog sobj : Database)
        {
            lobj = sobj.getDate();
            d = sobj.getDuration();
            

            if(tobj.containsKey(lobj))
            {
                old = tobj.get(lobj);
                tobj.put(lobj,d+old);
            }
            else
            {
                tobj.put(lobj,d);
            }
        }

        //Display the details as per date
        for(LocalDate ldobj : tobj.keySet())
        {
            System.out.println("Date : "+lobj+" Total Study "+tobj.get(ldobj));
        }
        System.out.println("--------------------------------------------------------------");
    }

//==============================================================================
// Method       : SummaryBySubject
// Purpose      : Summarizes total study time per subject
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

    public void SummaryBySubject()
    {
        System.out.println("--------------------------------------------------------------");
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("--------------------------------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("------Summary by SUBJECT from Marvellous Study Tracker--------");
        System.out.println("--------------------------------------------------------------");

        TreeMap <String, Double> tobj = new TreeMap <String, Double> (); 

        double d, old;
        String s;

        for(StudyLog sobj : Database)
        {
            s = sobj.getSubject();
            d = sobj.getDuration();
            

            if(tobj.containsKey(s))
            {
                old = tobj.get(s);
                tobj.put(s,d+old);
            }
            else
            {
                tobj.put(s,d);
            }
        }

        //Display the details as per SUBJECT
        for(String str  : tobj.keySet())
        {
            System.out.println("SUBJECT : "+str+" Total Study "+tobj.get(str));
        }
        System.out.println("--------------------------------------------------------------");
    }

}

//==============================================================================
// Class        : program557 (Main Class)
// Purpose      : Entry point of the application that handles user interaction
// Date         : 28/07/2025
// AuthorName   : Shreya Siddharth Sasane
//==============================================================================

class program557   //StudyTrackerStarter
{
    public static void main(String A[]) 
    {
        StudyTracker stobj = new StudyTracker();

        Scanner ScannerObj = new Scanner(System.in);

        int iChoice = 0;

        System.out.println("--------------------------------------------------------------");
        System.out.println("---------Welcome to Marvellous Study Tracker Application------");
        System.out.println("--------------------------------------------------------------");

        do
        {
            System.out.println("Please select the appropriate option");
            System.out.println("1 : Insert new Study log into DATABASE");
            System.out.println("2 : View All STUDY LOGS ");
            System.out.println("3 : Summary of study log by Date ");
            System.out.println("4 : Summary of study log by SUBJECT ");
            System.out.println("5 : Export study log to CVS file ");
            System.out.println("6 : Exit the application ");

            iChoice =  ScannerObj.nextInt();

           switch(iChoice)
           {
                case 1: //Insert new Study log into DATABASE
                    stobj.InsertLog();
                    break;

                case 2: //View All STUDY LOGS
                    stobj.DisplayLog();
                    break;

                case 3: //Summary of study log by Date
                    stobj.SummaryByDate();
                    break;

                case 4: //Summary of study log by SUBJECT
                    stobj.SummaryBySubject();
                    break;

                case 5: //Export study log to CVS file
                    stobj.ExportCSV();
                    break;

                case 6: //Exit the application
                    System.out.println("--------------------------------------------------------------");
                    System.out.println("Thank you for using Marvellous Study log application");
                    System.out.println("--------------------------------------------------------------");    
                    break;

                default :
                    System.out.println("Please select the valid option");
                    
           }

        }while(iChoice != 6);
       
    }
}