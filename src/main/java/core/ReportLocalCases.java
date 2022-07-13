package core;

import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReportLocalCases {
    public static String newConfigFile = null;

    public static void main(String[] args) throws IOException {
        List<XmlSuite> cases = getXmlSuites();
        final Integer[] countTest = {0};
        cases.forEach(xmlSuite -> {
            System.out.println("Suite:" + xmlSuite.getName());
            xmlSuite.getTests().forEach(t->{

                t.getPackages().forEach(p->{
                    System.out.println("Pack:"+p.getName());
                    p.getXmlClasses().forEach(xmlClass -> {
                        System.out.println("Class:"+xmlClass.getName());
                        for (Method method : xmlClass.getSupportClass().getDeclaredMethods()) {
                            if (method.getAnnotation(org.testng.annotations.Test.class) != null) {
                                System.out.println(method.getName());
                                countTest[0] += 1;
                            }
                        }
                    });

                });
            });
        });

        System.out.println(countTest[0]);
    }

    public static List<XmlSuite> getXmlSuites() {
        List<XmlSuite> list = new ArrayList<>();
        try {
            list = new Parser(ReportLocalCases.class
                    .getClassLoader()
                    .getResourceAsStream("conf/android/CCM.xml")).parseToList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
