import Bootstrap.App.Bootstrap;

public class Application extends Bootstrap {
	
	public static void main(String[] test)
	{
		Application app = new Application(test);
	}

	public Application(String[] CommandLineArguments) {
		super(CommandLineArguments);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnInitialized() {
		// TODO Auto-generated method stub
		System.out.print("testtesttest");
	}

	@Override
	public void OnShutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnApplicationUpdate() {
		// TODO Auto-generated method stub
		
	}

}
