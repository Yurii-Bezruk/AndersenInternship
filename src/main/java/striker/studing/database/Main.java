package striker.studing.database;

public class Main {
    public static void main(String[] args) {
        DBManager manager = new DBManager();
        manager.readUsers().forEach(user -> {
            Department department = user.getDepartment();
            Country country = department.getCountry();
            System.out.printf("%d %s %d %s %d %s%n",
                user.getId(), user.getName(),
                department.getId(), department.getName(),
                country.getId(), country.getName());
        });
    }
}
