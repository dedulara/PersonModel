import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args)
    {
        ArrayList<String> people = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        String personRecord = "";
        boolean done = false;
        String idString = "";
        String firstName = "";
        String lastName = "";
        String titleString = "";
        int birthYear = 0;

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        do
        {
            idString = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]" );
            firstName = SafeInput.getNonZeroLenString(in, "Enter first name" );
            lastName = SafeInput.getNonZeroLenString(in, "Enter last name" );
            titleString = SafeInput.getNonZeroLenString(in, "Enter the title" );
            birthYear = SafeInput.getRangedInt(in, "Enter the birth year", 1000, 9999);

            personRecord = idString + ", " + firstName + ", " + lastName + ", " + titleString + ", " + birthYear;
            people.add(personRecord);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        }
        while(!done);

        for(String p: people)
            System.out.println(p);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));
            for(String rec : people)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}