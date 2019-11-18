package Bootstrap.parts;

import Bootstrap.App.EApp;

/** AbstractBootstrap 
 * 
 * This class can't be used directly, as it is declared abstract. 
 * 
 * Extend another class from this class to use it. 
 * 
 * AbstractBootstrap takes care of initialization of the application inside
 * its constructor and manages key stages of initialization. 
 * 
 * **/
public abstract class AbstractBootstrap implements IAbstractBootstrap
	{
	/** Used to track the 'state' of our application, we will set this state throughout initization**/
	private EApp _appState = EApp.StateUnknown;
	/** will be set to true if our application encounters an exception **/
	private boolean threwException = false;
	/** will be set to true if our application encounters an error warning during startup **/
	private boolean threwErrors = false; 
	/** will increase in value if we encounter errors **/
	private int errorCount = 0; 
	/** will increase in value if we encounter exceptions **/
	private int exceptionCount = 0; 
	
	/** Create an instance by using the *new* keyword and passing in some command line arguments as data
	 * 
	 *  eg: MyClass = new MyClass(commandLineArgumentsArray);
	 *  
	 *  **/
	public AbstractBootstrap(String[] CommandLineArguments)
		{
		/* INITIAL PREPERATION AND CHECKING */
		System.out.println("Abstract Bootstrap Constructor Called: appState:" + getAppState());
		
		// lets check that we are in the correct state before continuing
		if (getAppState() != EApp.StateUnknown)
			{ // we are in a state other than the expected one, we should cancel, likely, things will not work out
			threwErrors = true;
			errorCount++;
			System.out.println("We are not in StateUnknown, have you tried to initialize already? - returning early!");
			this.OnAppErrorOrWarning(this.getClass().getName() + " is in an unexpected state!");
			return; // we exit and dont try to do anything
			}
		else if (getAppState() == EApp.StateUnknown)
			{ // if we are here, we are in StateUnknown, which we set as default state when we declared appState (line 18)
			System.out.println("Setting state to Initializing");
			setAppState(EApp.Initializing);
			}
		else
			{ // we won't ever hit here, but should we, I provide an else anyway to catch all other states
			System.out.println("We should never see this message! - check your logic for this set of if statements");
			threwErrors = true;
			errorCount++;
			setAppState(EApp.Exception);
			}
		
		System.out.println("\tI see: " + CommandLineArguments.length + " Command Line Arguments");
		
		/* ENTER FIRST TRY...CATCH...FINALLY SECTION - INITIALIZATION */
		try
			{
			System.out.println("Entering Try..catch - we should catch any 'Exceptions' if they happen");
			
			// lets call our Initialize method now
			this.InitializeApplication();					
			}
		catch (Exception anyException)
			{ // something we did encountered an exception
			threwException = true;
			exceptionCount++;
			System.out.println("We caught an exception...triggering our error handler");
			this.OnAppException(anyException,this);
			}
		finally
			{
			// if the above succeeded, the InitializeApplication will have changed this.appState			
			System.out.println("Checking application state:" + getAppState());
			// lets check for that, this way we can tell if we can call this.OnInitialized or not
			if (getAppState() == EApp.Initialized)
				{// yes, good, lets continue
				System.out.println(this.getClass().getName() + " initialized successfully");
				}
			else
				{// not so good, 
				System.out.println(this.getClass().getName() + " failed to initialize");
				// record the error happened
				threwErrors = true;
				errorCount++;
				// let dev decide what to do about it. 
				this.OnAppErrorOrWarning(this.getClass().getName() + " failed to initialize");
				}
			System.out.println("Leaving main try...catch...finally");
			System.out.println("\tThere were exceptions: " + threwException + " - " + exceptionCount);
			System.out.println("\tThere were errors    : " + threwErrors + " - " + errorCount);
			}
		
		/* EXITED FIRST TRY...CATCH...FINALLY SECTION  */
		
		
		// our Initialization stage is complete, final check of our state before continuing
		if ((threwException == false) && (getAppState() == EApp.Initialized) && (threwErrors == false))
			{// our perfect scenario, nothing went wrong
			System.out.println("No issues, setting state to isRunning");
			setAppState(EApp.IsRunning);
			}
		else if ((threwException == false) && (getAppState() == EApp.Initialized) && (threwErrors == true))
			{// not quite perfect scenario, we had some errors but nothing to serious, lets try and continue
			System.out.println("Some issues, but will continue to setting state to isRunning");
			setAppState(EApp.IsRunning);
			}
		else if (threwException == true)
			{// if we threw an exception, we shouldn't have reached this stage, but just in case, lets handle it			
			setAppState(EApp.Exception);
			this.threwErrors = true;
			this.errorCount++;
			System.out.println("\tThere are now errors    : " + threwErrors + " - " + errorCount);
			this.OnAppErrorOrWarning("Inconsistent application state reached, likely, logic broken!");
			}
		
		/* ENTER SECOND TRY...CATCH...FINALLY SECTION - OUR MAIN LOOP */
		
		
		boolean isShuttingDown = false; // Ill use this to indicate when we are 'normally shutting down'
		try // set up another try catch - this time for our main loop
			{
			System.out.println("Entering Try..catch - we should catch any 'Exceptions' if they happen");
			// this is our main loop, we keep it running until the app terminates or the
			// user decides to terminate or some exception happens.
			while ((getAppState() == EApp.IsRunning))
				{
				this.OnApplicationUpdate();
				} ;
			
			
			// when we reach this code, our appState must have changed from isRunning to something else
			switch (getAppState())
				{// normally, we expect to be shutting down as normal, so lets handle that first
				case ShuttingDown: // are we shutting down?
					{//yes,appears to be a normal shutdown message	
					isShuttingDown = true;
					System.out.println("Shutting down normally");
					// let the try cry..catch.. 'fall through' and we'll continue in the 'finally'
					break;
					}
				default:
					{ // all other cases, considering the 'logic' and 'intent' of our app, would be an error here
					System.out.println("Application appears to be terminating due to an unexpcted state: " + getAppState());
					
					errorCount++;
					threwErrors = true;
					this.OnAppErrorOrWarning("Application appears to be terminating due to an unexpcted state: " 
							+ getAppState());
					}
				}				
			}
		catch (Exception e)
			{ // something happened while app was running
			exceptionCount++;
			threwException = true;
			this.OnAppException(e);
			}
		finally
			{
			// if we have reached here, application is either terminating normally or because of some exception
			
			// which is it?
			if ((isShuttingDown == true) ||(getAppState() == EApp.ShuttingDown) && ((errorCount == 0) && (exceptionCount == 0)))
				{// all is good, shutting down normally, no errors, no exceptions
				setAppState( EApp.Shutdown);
				System.out.println(this.getClass().getName() + " shutdown normally");
				// call our Shutdown event
				this.ShutdownApplication();
				// exit with zero status (normal)
				System.exit(0);
				}
			else
				{// shutting down for some other reason
				System.out.println(this.getClass().getName() + " shutdown abnormally");				
				setAppState(EApp.Exception);
				// exit with a non zero status (abnormal)
				// call our Shutdown event
					this.ShutdownApplication();
				
				System.exit(getAppState().hashCode());
				}
			}
		
		/* EXITED SECOND TRY...CATCH...FINALLY SECTION */
		System.out.println("Errors:" + threwErrors + " - " + errorCount);
		System.out.println("Exceptions:" + threwException + " - " + exceptionCount);
		
		}
	
	@Override
	public void setAppState(EApp newState) 
		{
		EApp oldState = getAppState();
		if (this.OnAppStateChange(oldState, newState) == true)
			{
			_appState = newState;
			}
		else
			{
			System.out.println("State change from" + oldState + " to " + newState + " blocked!");
			}			
		}

	@Override
	public EApp getAppState() 
		{		
		return this._appState;
		}

	}
