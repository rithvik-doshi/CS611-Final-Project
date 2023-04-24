package Model;

public class Person {
    protected String name;
    protected String password;
    protected String type;
    protected String id;

    public Person(String name, String password, String type, String id) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "Name: " + name + " Password: " + password + " Type: " + type + " ID: " + id;
    }

    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            return p.getId().equals(id);
        }
        return false;
    }
}
