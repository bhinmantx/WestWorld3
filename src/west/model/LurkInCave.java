package west.model;

public class LurkInCave extends State<CaveTroll> {

	private volatile static LurkInCave singleton;
	 
	   private LurkInCave(){}
	 
	   // synchronized keyword has been removed from here
	   public static LurkInCave getSingleton(){
	     // needed because once there is singleton available no need to acquire
	     // monitor again & again as it is costly
	     if(singleton==null) {
	       synchronized(LurkInCave.class){
	          // this is needed if two threads are waiting at the monitor at the
	          // time when singleton was getting instantiated
	          if(singleton==null)
	          singleton = new LurkInCave();
	       }
	    }
	   return singleton;
	  }

	   public void enter(CaveTroll troll){
		   if(troll.getLocation() != Location.trollcave){
			   System.out.println(troll.getName() + "Going to lurk");
			   troll.changeLocation(Location.trollcave);
		   }
	   }

	   public void execute(CaveTroll troll){
		  		   
		  if(!troll.tooGreedy()) 
		  {
			  ////If he's tooGreedy he's going to steal
			  troll.changeState(PrepAndSteal.getSingleton());
		  }  
		  else
		  {
		   System.out.println(troll.getName() + " I am lurking");  
		  troll.increaseGreed();
		  }
		  
		   
	   }
	   
	   public void exit(CaveTroll troll){
		   System.out.println(troll.getName() + " Time to steal");
	   }
}
