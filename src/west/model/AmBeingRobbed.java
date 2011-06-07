package west.model;

public class AmBeingRobbed extends State<Miner> {
	private volatile static AmBeingRobbed singleton;
	 
	   private AmBeingRobbed(){}
	 
	   // synchronized keyword has been removed from here
	   public static AmBeingRobbed getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(AmBeingRobbed.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new AmBeingRobbed();
	       }
	    }
	   return singleton;
	  }
	   
	  public void enter(Miner miner){
		  ///Oh no! A robbery!
		  System.out.println(miner.getName() + " Egads! A robbery!");
	  }
	  public void execute(Miner miner){
		  miner.setGoldCarried(0);
		  /////So, he's not being robbed anymore.
		  miner.minerIsNotBeingRobbed();
		  miner.minerWasBeingRobbed();
		  miner.exhaust();
		  System.out.println(miner.getName() + " I'm fleeing to my shack!");
		  miner.minerLeavesGoldmine();
		  miner.getFSM().changeState(GoHomeAndSleepTillRested.getSingleton());
	  }
	  public void exit(Miner miner){
		  System.out.println(miner.getName() + " Being Robbed is exhausting!");
	  }
}
