import Bootstrap.App.Bootstrap;

public class TestAppOne extends Bootstrap {

	public TestAppOne(String[] CommandLineArguments) {
		super(CommandLineArguments);
		// TODO Auto-generated constructor stub
		System.out.println("TestOne Contstructor");
	}
	
	public static void main(String[] args)
	{
	 TestAppOne aApp = new TestAppOne(args);
	}

	@Override
	public void OnInitialized() {
		// TODO Auto-generated method stub
		System.out.println("XXXXXX ON INIT XXXXX");
	}

	@Override
	public void OnShutdown() {
		// TODO Auto-generated method stub
		System.out.println("XXXXXX ON SHUTDOWN XXXXX");
	}

	@Override
	public void OnApplicationUpdate() {
		// TODO Auto-generated method stub
		System.out.println("SDFEFDSFDS");
	}
	
	
	

}
