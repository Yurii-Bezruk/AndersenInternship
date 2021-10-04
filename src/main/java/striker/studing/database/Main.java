package striker.studing.database;

public class Main {
    public static void main(String[] args) {
        DBManager manager = new DBManager();
//        manager.readUsers().forEach(user -> {
//            Department department = user.getDepartment();
//            Country country = department.getCountry();
//            System.out.printf("%d %s %d %s %d %s%n",
//                user.getId(), user.getName(),
//                department.getId(), department.getName(),
//                country.getId(), country.getName());
//        });
        User user = new User();
        user.setId(6);
        user.setName("new user2");
        Department department = new Department();
        department.setId(2);
        user.setDepartment(department);
        manager.updateUser(user);
    }
}
