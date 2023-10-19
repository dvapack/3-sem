class MySecondClass
{
	private int pole1;
	private int pole2;
	void set_pole1 (int to_set)
	{
		pole1 = to_set;
	}
	int get_pole1 ()
	{
		return pole1;
	}
	void set_pole2 (int to_set)
	{
		pole2 = to_set;
	}
	int get_pole2 ()
	{
		return pole2;
	}
	int summ()
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

class MyFirstClass 
{
	public static void main(String[] s) 
	{
		MySecondClass o = new MySecondClass();
		int i, j;
		for (i = 1; i <= 8; i++) 
		{
			for(j = 1; j <= 8; j++) 	
			{
				o.set_pole1(i);
				o.set_pole2(j);
				System.out.print(o.summ());
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
		


