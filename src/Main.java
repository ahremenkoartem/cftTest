import java.io.*;
import java.util.ArrayList;

public class Main {
    static ArrayList<String> strings = new ArrayList<>();
    static ArrayList<Integer> integers = new ArrayList<>();
    static BufferedReader reader;
    static BufferedWriter writer;
    private static ArrayList<Integer> inserSortIntA(ArrayList<Integer> arrayList){
        for (int i =1; i<arrayList.size();i++)
            for (int j = i; j>0 && arrayList.get(j-1)>arrayList.get(j); j--){
                int temp = arrayList.get(j);
                arrayList.set(j,arrayList.get(j-1));
                arrayList.set(j-1,temp);
            }
        return arrayList;
    }
    private static ArrayList<Integer> inserSortIntD(ArrayList<Integer> arrayList){
        for (int i =1; i<arrayList.size();i++)
            for (int j = i; j>0 && arrayList.get(j-1)<arrayList.get(j); j--){
                int temp = arrayList.get(j);
                arrayList.set(j,arrayList.get(j-1));
                arrayList.set(j-1,temp);
            }
        return arrayList;
    }
    private static ArrayList<String> inserSortStrA(ArrayList<String> arrayList){
        for (int i =1; i<arrayList.size();i++)
            for (int j = i; j>0 && arrayList.get(j-1).compareTo(arrayList.get(j))>0; j--){
                String temp = arrayList.get(j);
                arrayList.set(j,arrayList.get(j-1));
                arrayList.set(j-1,temp);
            }
        return arrayList;
    }
    private static ArrayList<String> inserSortStrD(ArrayList<String> arrayList){
        for (int i =1; i<arrayList.size();i++)
            for (int j = i; j>0 && arrayList.get(j-1).compareTo(arrayList.get(j))<0; j--){
                String temp = arrayList.get(j);
                arrayList.set(j,arrayList.get(j-1));
                arrayList.set(j-1,temp);
            }
        return arrayList;
    }
    private static void read(String file, String args2){
        try {
            reader = new BufferedReader(new FileReader(file));
            String s;
            while ((s=reader.readLine())!=null){
                strings.add(s);
                if(args2.equals("-i"))integers.add(Integer.parseInt(s));
            }
            reader.close();
        }
        catch (NumberFormatException e){
            System.out.println("одно из чисел в входном файле -не integer!");
            try {
                System.in.read();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
        catch (FileNotFoundException e){
            System.out.println("Входной файл не найден!");
            try {
                System.in.read();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception!");
            try {
                System.in.read();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
    private static void write(String file, ArrayList arrayList){
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i=0;i<arrayList.size();i++)
                writer.write(arrayList.get(i).toString()+"\n");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        try {
            read(args[0],args[2]);
            if (args[2].equals("-s")) {
                System.out.println("SortStr " + args[3]);
                if (args[3].equals("-a"))
                    strings = inserSortStrA(strings);
                else if (args[3].equals("-d"))
                    strings = inserSortStrD(strings);
                else {
                    System.out.println("Ошибка - неверный 4й аргумент!");
                    System.in.read();
                    System.exit(0);
                }
                write(args[1], strings);
            } else if (args[2].equals("-i")) {
                System.out.println("SortInt " + args[3]);
                if (args[3].equals("-a"))
                    integers = inserSortIntA(integers);
                else if (args[3].equals("-d"))
                    integers = inserSortIntD(integers);
                else {
                    System.out.println("Ошибка - неверный 4й аргумент!");
                    System.in.read();
                    System.exit(0);
                }
                write(args[1], integers);
            }
            else {
                System.out.println("Ошибка - неверный 3й аргумент!");
                System.in.read();
                System.exit(0);
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Недостаточное количество аргументов для запуска!");
            System.in.read();
            System.exit(0);
        }
    }
}
