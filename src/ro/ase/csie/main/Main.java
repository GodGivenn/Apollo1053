package ro.ase.csie.main;

import ro.ase.csie.classes.Lander;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        int[] readings = { -20, -30, - 15};
        Lander apolloLander = new Lander("Apollo1053", true, readings);
        System.out.println(apolloLander.toString());

        List<Lander> landers = new ArrayList<>();


        //read from csv file
        BufferedReader bufferedReader = null;
        try{
            FileInputStream fileInputStream = new FileInputStream("landers.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.readLine();
            String csvLine;
            while((csvLine = bufferedReader.readLine()) != null){
                String[] fields = csvLine.split(",");

                String name = fields[0];
                float weight = Float.parseFloat(fields[1]);
                String landingSite = fields[2];

                Lander lander = new Lander(name,weight,landingSite,false,null);
                landers.add(lander);
                System.out.println(lander);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        landers.getFirst().setHasLanded(true);
        landers.getFirst().setTemperatureReadings(readings);
        landers.getLast().setHasLanded(true);

        //binary
//        TreeSet<Lander> sortedLanders = new TreeSet<>(landers);
//
//        try (FileInputStream fileInputStream = new FileInputStream("landers.bin");
//             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
//            Lander l = new Lander();
//            l.setName(dataInputStream.readUTF());
//            l.setWeight(dataInputStream.readFloat());
//            l.setLandingSite(dataInputStream.readUTF());
//            l.setHasLanded(dataInputStream.readBoolean());
//            int length = dataInputStream.readInt();
//            int[] temps = new int[length];
//            for (int i = 0; i < length; i++) {
//                temps[i] = dataInputStream.readInt();
//            }
//            l.setTemperatureReadings(temps);
//            System.out.println(l);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println(landers.getFirst());
        //write to txt file
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("landed_landers.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (Lander l : landers){
                if(l.isHasLanded()) {
                    bufferedWriter.write(l.getName());
                    bufferedWriter.write(" ");
                    bufferedWriter.write(l.getLandingSite());
                    bufferedWriter.write(" ");
                    bufferedWriter.write(String.valueOf(l.getAverageTemperature()));
                    bufferedWriter.write(System.lineSeparator());
                }
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        System.out.println("PRIMUL: "+ landers.getFirst());
        //seriazlie
        try{
            landers.getFirst().serialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //deserialize
        Lander landerDes = new Lander();
        try{
            landerDes.deserialize();
            System.out.println("deserialize:" + landerDes);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}