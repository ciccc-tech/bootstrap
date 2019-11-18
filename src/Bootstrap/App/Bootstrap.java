package Bootstrap.App;

import Bootstrap.parts.AbstractBootstrap;

/**
 * @author Ivan Malone
 *
 * Bootstrap class. 
 * 
 * This class is not meant to be used directly, so it is declared as abstract.
 * 
 * We will build other applications out of this application by extending from it
 * 
 * So this application 'knows' that and will take care of some key functions for us
 * then hand over control to a child class. Since that is it purpose. 
 * 
 * To use it you must derive another class from it and implement required methods.  
 * 
 * To force the developer to implement additional code I should use the implements keyword
 * so I can import the IApp interface into this code. Then add the required methods. 
 * 
 * The interface only defines the methods and default behaviour, you, the developer, must fill
 * in the blanks. 
 * 
 */
public abstract class Bootstrap extends AbstractBootstrap
	{
	public Bootstrap(String[] CommandLineArguments) {
		super(CommandLineArguments);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] argumentsFromCommandLine)
		{
		System.out.println("Started Bootstrap Main");
		}
	}
