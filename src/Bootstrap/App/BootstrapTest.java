package Bootstrap.App;




/**
 * BootStrapTest
 * 
 * Designed to test the behaviour of Bootstrap.java and serve as an example of its usage. 
 * 
 * Its a good idea to create test code for critical sections of code because it allows us to make sure
 * that the code continues to work as expected. 
 * 
 * Often things can break when we are developing in a team. 
 * 
 * TTD - This is test driven development and part of our course. 
 * 
 * 
 */
public abstract class BootstrapTest extends Bootstrap
	{
	public BootstrapTest(String[] CommandLineArguments) {
		super(CommandLineArguments);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] argumentsFromCommandLine)
		{
		System.out.println("Started BootstrapTest Main");
		}

	@Override
	public void OnInitialized() {
		// TODO Auto-generated method stub
		
		
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
