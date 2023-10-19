package myfirstpackage;

public class MySecondClass
{
	private int pole1;
	private int pole2;
	public void set_pole1 (int to_set)
	{
		pole1 = to_set;
	}
	public int get_pole1 ()
	{
		return pole1;
	}
	public void set_pole2 (int to_set)
	{
		pole2 = to_set;
	}
	public int get_pole2 ()
	{
		return pole2;
	}
	public int summ()
	{
		return pole1 + pole2;
	}
	public MySecondClass ()
	{
		pole1 = 0;
		pole2 = 0;
	}
	public MySecondClass (int new_pole1, int new_pole2)
	{
		this();
		this.pole1 = new_pole1;
		this.pole2 = new_pole2;
	}
}