import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by michall on 21-01.
 */
public class DataSetParser {
    private static DataSetParser ourInstance = new DataSetParser();

    public static DataSetParser getInstance() {
        return ourInstance;
    }

    private DataSetParser() {
    }

    void preprocessData(String inFilePath, String outFilePath){
        BufferedReader reader = null;
        if (inFilePath == null || outFilePath == null) throw new IllegalArgumentException("File name cannot be null!");

        try {
            reader = new BufferedReader(new FileReader(new File(inFilePath)));
            String line = null;
            List<Double[]> matrix = new ArrayList<Double[]>();
            List<Pair<Integer,Integer>> missing = new ArrayList<Pair<Integer,Integer>>();
            int i=0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Double[] parsed = new Double[295];
                if (values[0].equals("")) {
                    continue; // skip if line was empty
                };
                int j=0;
                for(String v : values){
                    if(v.equals("?")){
                        missing.add(new Pair<Integer,Integer>(i,j));
                        v = "0";
                    }
                    parsed[j]=Double.parseDouble(v);
                    ++j;
                }
                int dataClass = Integer.parseInt(values[279]);
                for(i=279;i<295;++i){parsed[i]=.0;}
                parsed[278+dataClass]=1.0;

                matrix.add(parsed);
                ++i;
            }

            reader.close();

            PrintWriter writer = new PrintWriter(outFilePath);
            for(Double[] vec : matrix){
                StringJoiner joiner = new StringJoiner(",");
                for(Double d : vec){
                    joiner.add(d.toString());
                }
                writer.println(joiner.toString());
            }

            writer.close();
        }
        catch(FileNotFoundException ex) {
        }
        catch(IOException ex){
        }


    }
}
