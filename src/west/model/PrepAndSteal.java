package west.model;

public class PrepAndSteal extends State<CaveTroll> {

	private volatile static PrepAndSteal singleton;
	 
	   private PrepAndSteal(){}
	 
	   // synchronized keyword has been removed from here
	   public static PrepAndSteal getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(PrepAndSteal.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new PrepAndSteal();
	       }
	    }
	   return singleton;
	  }

	   public void enter(CaveTroll troll){
		   if(troll.getLocation() != Location.goldmine){
			   System.out.println(troll.getName() + " I am heading to the mine! ");
			   troll.changeLocation(Location.goldmine);
		   }
	   }

	   public void execute(CaveTroll troll){
		  ///he just lurks
		   //System.out.println(troll.getName() + " I am in the process of stealing");
		  
		  
		  if(troll.canSteal())
		  {
			  System.out.println(troll.getName() + " I can see that miner");
			  troll.isStealing();
			  troll.minerIsBeingRobbed();
			 // hasStolen = true;
		  }
		  else
		  {
			  System.out.println(troll.getName() + " I am waiting for that miner");
		  }
		   

		  
		  
		   
		   if(troll.wasMinerRobbed()){
		   
		   troll.changeState(LurkInCave.getSingleton());
		   }
	   }
	   
	   public void exit(CaveTroll troll){
		   System.out.println(troll.getName() + " greed satiated!");
		   troll.resetGreed();
		   troll.minerIsNotBeingRobbed();
		   troll.minerWasNotBeingRobbed();
	   }
	   
	   
	   /*
	   public void execute(CaveTroll troll){
		   //if miner is not fatigued start to dig for nuggets again.
		   if (!troll.fatigued())
		   {
			   System.out.println(troll.getName() + " What a God darn fantastic nap! Time to find more gold");

		    //  troll.changeState(EnterMineAndDigForNugget.getSingleton());
		   }

		   else 
		   {
		     //sleep
		     troll.decreaseFatigue();
		     System.out.println(troll.getName() + " ZZZZ...");

		   } 
	   }
	   
	   public void exit(CaveTroll troll){
		   System.out.println(troll.getName() + " Leavin' the house");
	   }
	   */
}
