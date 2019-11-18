package Bootstrap.parts;

import Bootstrap.App.EApp;

/** Provide some implementation methods **/
public interface IAbstractBootstrap 
	{
	
	/** Called every time our application changes running state **/
	public default boolean OnAppStateChange(EApp oldState, EApp newState)
		{
		System.out.println(this.getClass().getCanonicalName() + "is going from " + oldState + " to " + newState);
		return true;
		}
	
	public void setAppState(EApp newState);
	public EApp getAppState();
	
	/** Called when application is about to be initialized **/
	public default void InitializeApplication()
		{
		System.out.println(this.getClass().getCanonicalName() + " is about to be Initialized");
		this.setAppState(EApp.Initialized);
		this.OnInitialized();
		}
	
	/** Called when application has finished initializing **/
	public void OnInitialized();
	
	/** Called when application is about to be shut down **/
	public default void ShutdownApplication()
		{
		System.out.println(this.getClass().getCanonicalName() + " is about to be Shut Down");
		this.setAppState(EApp.ShuttingDown);
		}
	
	/** Called when application has finished shutting down **/
	public void OnShutdown();
	
	/** Called when the application experiences a major exception (error) **/
	public default void OnAppException(Exception e)
		{
		System.out.println(this.getClass().getCanonicalName() + " exception!");
		System.out.println("\tDetails: " + e);
		}
	// alternative version to provide a bit more context
	public default void OnAppException(Exception e, Object fromObject)
		{
		System.out.println(this.getClass().getCanonicalName() + " exception triggered by " + fromObject);
		System.out.println("\t" + fromObject.toString() + " - Details: " + e);
		}
	
	/** Called when the application experiences a minor error (warning) **/
	public default void OnAppErrorOrWarning(String message)
		{
		System.out.println(message);
		}
	
	/** Called in our applications main loop when its time to update the application **/
	public  void OnApplicationUpdate();
	
	
	}
