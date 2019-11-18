package Bootstrap.App;


/**
 * EApp contains some values I intend to use during Bootstrapping
 * 
 * 
 * StateUnknown - indicates unknown state
 * 		will be set as the default state when we declare a variable of this type
 * 
 * Initializing - indicates that we in an initializing state, but not yet initialized
 * 		will be set immediatly in the main method and we will begin initialization
 * 
 * Initialized	- indicates that our bootstrapper is initialized but we are not yet running
 *      will be set at by the instance constructor after creation from main
 *      
 * ShuttingDown - indicates that we are in a shutting down state, we should stop whatever we are doing
 * 		will be set when we can detect we are being 'shut down'. 
 * 
 * ShutDown		- indicates that we have correctly shut down and are about to call System.Exit(0)
 * 		we will set this when we have finished shutting down our application
 * 
 * Exception		- indicates that we are in some kind of Exception state
 * 		we will set this if/when we run into serious error states that effect our entire application
 * 
 * IsRunning	- indicates that we are in a running state
 * 		will be set inside the Bootstrap constructor when we are fully up and running
 * 
 */
public enum EApp 
	{
	StateUnknown,
	Initializing,
	Initialized,
	ShuttingDown,
	Shutdown,
	Exception,
	IsRunning
	}
