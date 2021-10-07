package striker.studing.jpa;

import org.junit.*;
import striker.studing.jpa.entity.Country;
import striker.studing.jpa.entity.Department;
import striker.studing.jpa.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JPATest {
    private static EntityManagerFactory factory;
    private EntityManager manager;

    @BeforeClass
    public static void init(){
        factory = Persistence.createEntityManagerFactory("UserAppJpa");
    }

    @Before
    public void initManager(){
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
    }

    @Test
    public void saveCountryTest(){
        final Country country = new Country();
        country.setName("Australia");
        manager.persist(country);
        manager.flush();
    }
    @Test
    public void findCountryTest(){
        Assert.assertEquals(1, manager.find(Country.class, 1).getId());
    }
    @Test
    public void findDepartmentTest(){
        Assert.assertEquals(4L, manager.find(Department.class, 4L).getCountry().getId());
    }
    @Test
    public void testReadAll(){
        manager.createQuery("SELECT d.name from Department d").getResultList().forEach(System.out::println);
    }
    @Test
    public void testReadAllUsers(){
        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(User.class);
        final Root<User> root = query.from(User.class);
        query.select(root).where(builder.gt(root.get("id"), 2));
        manager.createQuery(query).getResultList().forEach(System.out::println);
    }

    @After
    public void destroyManager(){
        if(manager.getTransaction().isActive()){
            manager.getTransaction().commit();
        }
        manager.close();
    }

    @AfterClass
    public static void destroy(){
        factory.close();
    }
}
