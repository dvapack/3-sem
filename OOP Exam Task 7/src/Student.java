import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable
{
    private String name;
    private String surname;
    private String patronymic;
    private int number;
    private HashMap<String, Integer> marks;

    public Student()
    {
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPatronymic()
    {
        return patronymic;
    }

    public void setPatronymic(String patronymic)
    {
        this.patronymic = patronymic;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public HashMap<String, Integer> getMarks()
    {
        return marks;
    }

    public void setMarks(HashMap<String, Integer> marks)
    {
        this.marks = marks;
    }
}

