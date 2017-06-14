package com.rdlogic;

import com.rdlogic.reports.StreamedFileProcessor;

public class Main {

    public static void main(String[] args) {
        if(args.length!=3)
            throw new IllegalArgumentException("Expected: \n1). Path to existing Report -  \n" +
                    "2). Path to Output Workbook - \n3). Text file delimiter character.\n");
        else {
            StreamedFileProcessor sfp = new StreamedFileProcessor(args[0], args[1], args[2]);
            try {
                sfp.process();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
