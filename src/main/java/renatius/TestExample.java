package renatius;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class TestExample {

    private static final String XML_FILE = "dept-info.xml";

    public static void main(String[] args) {
        Employee emp1 = new Employee("E01", "Tom", null);
        Employee emp2 = new Employee("E02", "Mary", "E01");
        Employee emp3 = new Employee("E03", "John", null);
        List<Employee> list = new ArrayList<Employee>();

        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

        Department dept = new Department("D01", "ACCOUNTING", "NEW YORK");
        List<Department> list1 = new ArrayList<Department>();
        list1.add(dept);
        dept.setEmployees(list);

        Department dept1 = new Department("D02", "ACCOUNTING", "NEW YORK");

        Department dept2 = new Department("D03", "ACCOUNTING", "NEW YORK");

        Organization organization = new Organization();
        organization.setName("ОАО ДЕРЖИ ДВЕРЬ");
        organization.setAddress("Проспект Космонавтов 32 кв 79");
        organization.setDepartments(list1);
        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(organization, System.out);
            File outFile = new File(XML_FILE);
            m.marshal(organization, outFile);
            System.err.println("Write to file: " + outFile.getAbsolutePath());
            Unmarshaller um = context.createUnmarshaller();
            Organization organization1 = (Organization) um.unmarshal(new FileReader(XML_FILE));
            List<Department> departments = organization1.getDepartments();
            for (Department dep : departments) {
                System.out.println(dep.getDepartmentName());
                List<Employee> employees = dep.getEmployees();
                for (Employee emp : employees) {
                    System.out.println(emp.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
