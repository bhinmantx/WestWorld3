package west.model;

public class CaveTroll extends BaseGameEntity {

	public static final int GreedThreshHold = 4;
	public static final int NuggetThreshold = 1;
	private StateMachine<CaveTroll> pStateMachine;
	private Location location;
	private int iGreed;
	//private int iLurkDays;
	public CaveTroll(int ID, String name){
		super(ID, name);
		location = Location.trollcave;
		iGreed = 0;
		//iLurkDays = 0;
		pStateMachine = new StateMachine<CaveTroll>(this);
		pStateMachine.setCurrentState(LurkInCave.getSingleton());
	}
	public void update(){
		//iThirst += 1;
		pStateMachine.update();
	}
	public StateMachine<CaveTroll> getFSM(){return pStateMachine;}
	public Location getLocation(){return location;}
	public void changeState(State<CaveTroll> newState){
		pStateMachine.changeState(newState);
	}
	
	public boolean tooGreedy() {
		if(iGreed < GreedThreshHold){return true;}
		return false;
	}

	public boolean canSteal(){
		return this.isMinerAtGoldmine();
		}
	public void isStealing(){this.minerIsBeingRobbed();}
	public void finishedStealing(){this.minerIsNotBeingRobbed();}
	public void changeLocation(Location loc){location = loc;}
	public void increaseGreed(){iGreed += 1;}
	public void resetGreed(){iGreed = 0;}
//	public boolean tooGreedy(){return iGreed >= GreedThreshHold;}
}
